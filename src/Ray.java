/*
   Ray.java
 
   Created on marts  7, 2010 (Morten Rhiger <mir@ruc.dk>)
*/

/**
 * Objects of this class are directed half lines in 3 dimensions.
 * They are represented by a point in space (a Point3) and a
 * direction (a unit Vector3).
 */

public class Ray {

    /**
     * The origin of this ray.
     * <p/>
     * This fields is public, to support convinient access to it.
     * However, it should not be modified from outside this class.
     */
    public Point3 origin;
    public float rayIntensity = 1;

    /**
     * The direction of this ray.  This is a unit vector (i.e., it has
     * length 1.)
     * <p/>
     * This fields is public, to support convenient access to it.
     * However, it should not be modified from outside this class.
     */
    public Vector3 direction;

    /**
     * Constructs a new ray.  The direction need not be a unit
     * vector.
     */
    public Ray(Point3 origin, Vector3 direction) {
        this.origin = origin;
        this.direction = direction.unitize();
    }
}
