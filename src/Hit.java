/*
   Hit.java
 
   Created on marts  7, 2010 (Morten Rhiger <mir@ruc.dk>)
*/

/**
 * Objects of this class represent the intersection of a ray with an
 * object.
 */
public class Hit {
    /**
     * The location (in the scene) of the intersection point
     * <p/>
     * This fields is public, to support convenient access to it.
     * However, it should not be modified from outside this class.
     */
    public Point3 point;

    /**
     * The distance from the origin of the ray to the point of
     * intersection.
     * <p/>
     * This fields is public, to support convenient access to it.
     * However, it should not be modified from outside this class.
     */
    public double distance;

    /**
     * The normal to the surface at the point of intersection.
     * <p/>
     * This fields is public, to support convenient access to it.
     * However, it should not be modified from outside this class.
     */
    public Vector3 normal;
    public Sphere hitSphere;

    /**
     * Construct a new hit.
     */
    public Hit(Point3 point, double distance, Vector3 normal, Sphere hitSphere) {
        this.point = point;
        this.distance = distance;
        this.normal = normal;
        this.hitSphere = hitSphere;

    }
}
