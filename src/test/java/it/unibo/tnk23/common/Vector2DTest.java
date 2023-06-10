package it.unibo.tnk23.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class Vector2DTest {

    @Test
    public void testConstructorAndGetters() {
        // Test the constructor and getters

        double x = 1.0;
        double y = 2.0;

        Vector2D vector = new Vector2D(x, y);

        // Assert that the getX and getY methods return the expected values
        assertTrue(Double.compare(x, vector.getX()) == 0);
        assertTrue(Double.compare(y, vector.getY()) == 0);
    }

    @Test
    public void testSetters() {
        // Test the setters

        double x = 1.0;
        double y = 2.0;

        Vector2D vector = new Vector2D(0, 0);

        // Set the x and y values using the setters
        vector.setX(x);
        vector.setY(y);

        // Assert that the getX and getY methods return the expected values
        assertTrue(Double.compare(x, vector.getX()) == 0);
        assertTrue(Double.compare(y, vector.getY()) == 0);
    }

    @Test
    public void testSum() {
        // Test the sum method

        Vector2D vector1 = new Vector2D(1.0, 2.0);
        Vector2D vector2 = new Vector2D(3.0, 4.0);

        // Compute the sum of vector1 and vector2
        Vector2D result = vector1.sum(vector2);

        // Assert that the sum has the expected x and y values
        assertTrue(Double.compare(4.0, result.getX()) == 0);
        assertTrue(Double.compare(6.0, result.getY()) == 0);
    }

    @Test
    public void testModule() {
        // Test the module method

        Vector2D vector = new Vector2D(3.0, 4.0);

        // Compute the module of the vector
        double result = vector.module();

        // Assert that the module has the expected value
        assertTrue(Double.compare(5.0, result) == 0);
    }

    @Test
    public void testMul() {
        // Test the mul method

        Vector2D vector = new Vector2D(2.0, 3.0);

        // Multiply the vector by a scalar value
        Vector2D result = vector.mul(2.5);

        // Assert that the resulting vector has the expected x and y values
        assertTrue(Double.compare(5.0, result.getX()) == 0);
        assertTrue(Double.compare(7.5, result.getY()) == 0);
    }

    @Test
    public void testToString() {
        // Test the toString method

        Vector2D vector = new Vector2D(1.0, 2.0);

        // Get the string representation of the vector
        String result = vector.toString();

        // Assert that the result matches the expected string representation
        assertEquals("Vector2D [1.0, 2.0]", result);
    }

    @Test
    public void testHashCode() {
        // Test the hashCode method

        Vector2D vector1 = new Vector2D(1.0, 2.0);
        Vector2D vector2 = new Vector2D(1.0, 2.0);

        // Assert that the hash codes of vector1 and vector2 are equal
        assertEquals(vector1.hashCode(), vector2.hashCode());
    }

    @Test
    public void testEquals() {
        // Test the equals method

        Vector2D vector1 = new Vector2D(1.0, 2.0);
        Vector2D vector2 = new Vector2D(1.0, 2.0);
        Vector2D vector3 = new Vector2D(3.0, 4.0);

        // Assert that vector1 is equal to vector2 and not equal to vector3
        assertTrue(vector1.equals(vector2));
        assertFalse(vector1.equals(vector3));
    }
}

