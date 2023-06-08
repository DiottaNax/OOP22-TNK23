package it.unibo.tnk23.common;

import java.util.Objects;

/**
 * Generic class representing a pair of values.
 *
 * @param <X> the type of the first value
 * @param <Y> the type of the second value
 */
public class Pair<X, Y> {
    private final X x;
    private final Y y;

    /**
     * Constructs a new Pair object with the given values.
     *
     * @param x the first value
     * @param y the second value
     */
    public Pair(final X x, final Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves the first value of the pair.
     *
     * @return the first value
     */
    public X getX() {
        return this.x;
    }

    /**
     * Retrieves the second value of the pair.
     *
     * @return the second value
     */
    public Y getY() {
        return this.y;
    }

    /**
     * Checks if this Pair is equal to another object.
     * Two Pairs are considered equal if their corresponding values are equal.
     *
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Pair<?, ?> other = (Pair<?, ?>) obj;
        return Objects.equals(x, other.x)
                && Objects.equals(y, other.y);
    }

    /**
     * Computes the hash code for this Pair.
     * The hash code is based on the hash codes of the two values.
     *
     * @return the computed hash code
     */
    @Override
    public int hashCode() {
        int result = 17;
        final int prime = 31;
        result = prime * result + Objects.hashCode(x);
        result = prime * result + Objects.hashCode(y);
        return result;
    }

    /**
     * Returns a string representation of the Pair in the format "(x, y)".
     *
     * @return the string representation of the Pair
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
