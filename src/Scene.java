/*
   Main.java
 
   Created on marts 12, 2010 (Morten Rhiger <mir@ruc.dk>)
*/

import java.util.ArrayList;

/**
 * Objects of this class represents scenes to be rendered.  A scene
 * is simply a list of shapes.
 */

public class Scene {
    /**
     * The list of shapes in this scene.
     */
    private ArrayList<Sphere> shapes;

    public ArrayList<Light> lights;

    /**
     * Constructs a new scene.
     */
    public Scene() {
        shapes = new ArrayList<Sphere>();
        lights = new ArrayList<Light>(3);
    }

    /**
     * Adds a shape to this scene.
     */
    public void add(Sphere sphere) {
        shapes.add(sphere);
    }

    public void addLight(Light light) {
        lights.add(light);
    }

    /**
     * Intersect a ray with the scene.  Returns the closest hit (or
     * null if no shape intersects with the ray).
     */
    public Hit intersect(Ray ray) {
        Hit best = null;
        for (Sphere sphere : shapes) {
            Hit hit = sphere.intersect(ray);
            if (best == null)
                best = hit;
            else if (hit != null && hit.distance < best.distance)
                best = hit;
        }
        return best;
    }


}

