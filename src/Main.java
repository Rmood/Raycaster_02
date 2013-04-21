/*
   Main.java
 
   Created on marts  7, 2010 (Morten Rhiger <mir@ruc.dk>)
*/

import java.util.ArrayList;
import java.awt.Color;


public class Main {
    int window_width;
    int window_height;
    Display display;
    Scene scene;
    Camera camera;
    String[] args;

     float trace(Ray ray, Scene scene) {

        Hit hit = scene.intersect(ray);
        if (hit == null) return 0;

        float colorCode = 0;
        if (hit.hitSphere.reflective) {
            ray.rayIntensity -= 0.3;
            Ray newRay = new Ray(hit.point.add((hit.normal.multiply(0.000001))),
                    ray.direction.subtract(hit.normal.multiply(2 * ray.direction.dot(hit.normal))));
            return trace(newRay, scene) * ray.rayIntensity;
        }
        colorCode = colorCode(ray);
        return colorCode;
    }

    float colorCode(Ray ray) {
        ArrayList<Ray> rays = new ArrayList<Ray>(3);
        float retValue = 0;
        Hit firstHit = scene.intersect(ray);
        for (Light light : scene.lights) {
            Vector3 lightV = firstHit.point.subtract(light.point).unitize();
            Ray newRay = new Ray(firstHit.point.add(firstHit.normal.multiply(0.00001)), lightV);

            Hit tempHit = scene.intersect(newRay);

            if (tempHit == null) {
                float cosValue =  (float) lightV.dot(firstHit.normal) * calculateIntensity(firstHit.point.add(firstHit.normal));

                if (cosValue < 0)
                    retValue = 0;
                else
                    retValue = cosValue;

            } else retValue = 0;
        }
        return retValue;
    }

    public void setup() {
        window_width = 400;
        window_height = 400;

        double camera_width = 0.15;
        double camera_height = camera_width * (window_height / (double) window_width);

        // Setup scene

        scene = new Scene();
        scene.add(new Sphere(new Point3(0, 0, 0), 1, false));
        scene.add(new Sphere(new Point3(1, 0, 1), 0.4, false));
        scene.add(new Sphere(new Point3(0.4, 1, -0.6), 0.2, false));

        scene.addLight(new Light(new Point3(1, 1, 1), (float) 1));
        //scene.addLight(new Light(new Point3(4, 0, 0), (float) 0.5));

        Vector3 light = new Vector3(-1, 0, 0).unitize();

        // Setup display

        display = new Display(window_width, window_height);
        camera = new Camera(new Point3(5, 0, 0), camera_width, camera_height);
        camera.setWindowDimensions(window_width, window_height);

    }

    public void render() {

        // Render

        for (int y = 0; y < window_height; y++) {
            for (int x = 0; x < window_width; x++) {
                Ray ray = camera.getRay(x, y);

                Hit hit = scene.intersect(ray);


                if (hit == null) {
                    // The ray doesn't intersect with an object
                    display.plot(x, y, Color.BLACK);
                /*} else if (!hit.hitSphere.reflective) {
                    float colorCode = colorCode(ray);
                    Color myColor = new Color(colorCode, colorCode, colorCode);
                    display.plot(x, y, myColor);
                */
                    // The ray intersects with an object

                    // This is where to
                    //
                    // 1. use the vectors 'light' and 'hit.normal' to compute
                    //    shading, and
                    //
                    // 2. use the vector 'light' and the point 'hit.point' to
                    //    detect shadows.


                    //display.plot(x, y, Color.WHITE); // For now, draw only silhouette

                } else {
                    float colorCode = trace(ray, scene);
                    Color myColor = new Color(colorCode, colorCode, colorCode);
                    display.plot(x, y, myColor);
                }

            }
            display.refresh();
        }
        if (args.length == 1)
            display.save(args[0]);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setup();
        main.args = args;
        main.render();
    }

    float calculateIntensity(Point3 point) {
        float intensity = 0;
        for (int i = 0; i < scene.lights.size(); i++) {
            float tempDistance = point.subtract(scene.lights.get(i).point).length();

            intensity +=  (tempDistance * scene.lights.get(i).intensity)/ (tempDistance) ;
        }
        return fitIntensity(intensity);
    }

    public float fitIntensity(float intensity){
        if (intensity > 1)
            return 1;
        System.out.println(intensity);
        return intensity;
    }
}
