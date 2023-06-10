package it.unibo.tnk23.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Point2D} class.
 */
class Point2DTest {

    /**
     * Tests the {@link Point2D#getX()} method.
     */
    @Test
    void testGetX() {
        // Create a point with x-coordinate 2.5
        final Point2D point = new Point2D(2.5, 3.0);

        // Assert that the x-coordinate is 2.5
        Assertions.assertEquals(2.5, point.getX());
    }

    /**
     * Tests the {@link Point2D#getY()} method.
     */
    @Test
    void testGetY() {
        // Create a point with y-coordinate 3.0
        final Point2D point = new Point2D(2.5, 3.0);

        // Assert that the y-coordinate is 3.0
        Assertions.assertEquals(3.0, point.getY());
    }

    /**
     * Tests the {@link Point2D#sum(Vector2D)} method.
     */
    @Test
    void testSum() {
        // Create a point with coordinates (2.0, 3.0)
        final Point2D point = new Point2D(2.0, 3.0);

        // Create a vector with coordinates (1.0, 1.5)
        final Vector2D vector = new Vector2D(1.0, 1.5);

        // Compute the sum of the point and the vector
        final Point2D result = point.sum(vector);

        // Assert that the result is a new point with coordinates (3.0, 4.5)
        Assertions.assertEquals(3.0, result.getX());
        Assertions.assertEquals(4.5, result.getY());
    }

    /**
     * Tests the {@link Point2D#sub(Vector2D)} method.
     */
    @Test
    void testSub() {
        // Create a point with coordinates (2.0, 3.0)
        final Point2D point = new Point2D(2.0, 3.0);

        // Create a vector with coordinates (1.0, 1.5)
        final Vector2D vector = new Vector2D(1.0, 1.5);

        // Compute the difference between the point and the vector
        final Point2D result = point.sub(vector);

        // Assert that the result is a new point with coordinates (1.0, 1.5)
        Assertions.assertEquals(1.0, result.getX());
        Assertions.assertEquals(1.5, result.getY());
    }

    /**
     * Tests the equality and hash code of two points with the same coordinates.
     */
    @Test
    void testEqualsAndHashCode() {
        // Create two points with the same coordinates
        final Point2D point1 = new Point2D(2.0, 3.0);
        final Point2D point2 = new Point2D(2.0, 3.0);

        // Assert that the points are equal
        Assertions.assertEquals(point1, point2);
        Assertions.assertEquals(point1.hashCode(), point2.hashCode());
    }

    /**
     * Tests the inequality of two points with different coordinates.
     */
    @Test
    void testNotEquals() {
        // Create two points with different coordinates
        final Point2D point1 = new Point2D(2.0, 3.0);
        final Point2D point2 = new Point2D(1.0, 1.5);

        // Assert that the points are not equal
        Assertions.assertNotEquals(point1, point2);
        Assertions.assertNotEquals(point1.hashCode(), point2.hashCode());
    }
}

