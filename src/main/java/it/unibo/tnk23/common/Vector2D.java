package it.unibo.tnk23.common;

import java.util.Objects;

/**
 * Represents a two-dimensional vector.
 */
public class Vector2D {

    private double x;
    private double y;

    /**
     * Constructs a new Vector2D object with the specified coordinates.
     *
     * @param x the x-component of the vector
     * @param y the y-component of the vector
     */
    public Vector2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-component of the vector.
     *
     * @return the x-component
     */
    public double getX() {
        return this.x;
    }

    /**
     * Sets the x-component of the vector.
     *
     * @param x the new x-component
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * Returns the y-component of the vector.
     *
     * @return the y-component
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the y-component of the vector.
     *
     * @param y the new y-component
     */
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * Computes the sum of this vector and another vector.
     *
     * @param v the vector to add
     * @return a new Vector2D representing the sum of this vector and the other vector
     */
    public Vector2D sum(final Vector2D v) {
        return new Vector2D(this.x + v.getX(), this.y + v.getY());
    }

    /**
     * Computes the magnitude (length) of the vector.
     *
     * @return the magnitude of the vector
     */
    public double module() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Computes the scalar multiplication of the vector with a constant value.
     *
     * @param mul the scalar value to multiply the vector by
     * @return a new Vector2D representing the scalar multiplication of the vector
     */
    public Vector2D mul(final double mul) {
        return new Vector2D(this.x * mul, this.y * mul);
    }

    /**
     * Returns a string representation of the Vector2D object.
     *
     * @return a string representation of the Vector2D object
     */
    @Override
    public String toString() {
        return "Vector2D [" + this.x + ", " + this.y + "]";
    }

    /**
     * Computes the hash code for the Vector2D object.
     *
     * @return the computed hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Checks if this Vector2D object is equal to another object.
     * Two Vector2D objects are considered equal if their x and y components are equal.
     *
     * @param v the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(final Object v) {
        final double negativeZero = -0.0;
        if (v != null && v.getClass().equals(this.getClass())) {
            final var v2d = (Vector2D) v;
            if (Double.compare(v2d.getX(), negativeZero) == 0) {
                v2d.setX(0);
            } else if (Double.compare(v2d.getY(), negativeZero) == 0) {
                v2d.setY(0);
            }

            return Double.compare(this.y, v2d.getY()) == 0 && Double.compare(this.x, v2d.getX()) == 0;
        }

        return false;
    }
}
