package it.unibo.tnk23.common;

import java.util.Objects;

/**
 * Represents a point in a two-dimensional space.
 */
public class Point2D {
    private double x;
    private double y;

    /**
     * Constructs a new Point2D object with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the point.
     *
     * @return the x-coordinate
     */
    public final double getX() {
        return this.x;
    }

    /**
     * Sets the x-coordinate of the point.
     *
     * @param x the new x-coordinate
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * Returns the y-coordinate of the point.
     *
     * @return the y-coordinate
     */
    public final double getY() {
        return this.y;
    }

    /**
     * Sets the y-coordinate of the point.
     *
     * @param y the new y-coordinate
     */
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * Computes the sum of this point and a vector.
     *
     * @param v the vector to add
     * @return a new Point2D representing the sum of this point and the vector
     */
    public Point2D sum(final Vector2D v) {
        return new Point2D(this.x + v.getX(), this.y + v.getY());
    }

    /**
     * Computes the sum of each component of this point with a double.
     * @param d
     * @return a new Point2D representing the sum of this point and a double
     */
    public Point2D sum(final double d) {
        return new Point2D(this.x + d, this.y + d);
    }

    /**
     * Computes the difference between this point and a vector.
     *
     * @param v the vector to subtract
     * @return a new Point2D representing the difference between this point and the vector
     */
    public Point2D sub(final Vector2D v) {
        return this.sum(new Vector2D(-v.getX(), -v.getY()));
    }

    /**
     * Returns a string representation of the Point2D object.
     *
     * @return a string representation of the Point2D object
     */
    @Override
    public String toString() {
        return "Point2D [x=" + this.x + ", y=" + this.y + "]";
    }

    /**
     * Computes the hash code for the Point2D object.
     *
     * @return the computed hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Checks if this Point2D object is equal to another object.
     * Two Point2D objects are considered equal if their x and y coordinates are equal.
     *
     * @param p the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(final Object p) {
        final double negativeZero = -0.0;
        if (p != null && p.getClass().equals(this.getClass())) {
            final var p2d = (Point2D) p;
            if (Double.compare(p2d.getX(), negativeZero) == 0) {
                p2d.setX(0);
            } else if (Double.compare(p2d.getY(), negativeZero) == 0) {
                p2d.setY(0);
            }

            return Double.compare(y, p2d.getY()) == 0 && Double.compare(x, p2d.getX()) == 0;
        }

        return false;
    }

    /**
     * Returns a copy of the point.
     * 
     * @return a copy of the point
     */
    public Point2D copy() {
        return new Point2D(this.x, this.y);
    }
}
