package it.unibo.tnk23.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Point2DTest {

    @Test
    void testGetX() {
        // Create a point with x-coordinate 2.5
        Point2D point = new Point2D(2.5, 3.0);

        // Assert that the x-coordinate is 2.5
        Assertions.assertEquals(2.5, point.getX());
    }

    @Test
    void testGetY() {
        // Create a point with y-coordinate 3.0
        Point2D point = new Point2D(2.5, 3.0);

        // Assert that the y-coordinate is 3.0
        Assertions.assertEquals(3.0, point.getY());
    }

    @Test
    void testSum() {
        // Create a point with coordinates (2.0, 3.0)
        Point2D point = new Point2D(2.0, 3.0);

        // Create a vector with coordinates (1.0, 1.5)
        Vector2D vector = new Vector2D(1.0, 1.5);

        // Compute the sum of the point and the vector
        Point2D result = point.sum(vector);

        // Assert that the result is a new point with coordinates (3.0, 4.5)
        Assertions.assertEquals(3.0, result.getX());
        Assertions.assertEquals(4.5, result.getY());
    }

    @Test
    void testSub() {
        // Create a point with coordinates (2.0, 3.0)
        Point2D point = new Point2D(2.0, 3.0);

        // Create a vector with coordinates (1.0, 1.5)
        Vector2D vector = new Vector2D(1.0, 1.5);

        // Compute the difference between the point and the vector
        Point2D result = point.sub(vector);

        // Assert that the result is a new point with coordinates (1.0, 1.5)
        Assertions.assertEquals(1.0, result.getX());
        Assertions.assertEquals(1.5, result.getY());
    }

    @Test
    void testEqualsAndHashCode() {
        // Create two points with the same coordinates
        Point2D point1 = new Point2D(2.0, 3.0);
        Point2D point2 = new Point2D(2.0, 3.0);

        // Assert that the points are equal
        Assertions.assertEquals(point1, point2);
        Assertions.assertEquals(point1.hashCode(), point2.hashCode());
    }

    @Test
    void testNotEquals() {
        // Create two points with different coordinates
        Point2D point1 = new Point2D(2.0, 3.0);
        Point2D point2 = new Point2D(1.0, 1.5);

        // Assert that the points are not equal
        Assertions.assertNotEquals(point1, point2);
        Assertions.assertNotEquals(point1.hashCode(), point2.hashCode());
    }
}
