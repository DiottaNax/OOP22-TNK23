package it.unibo.tnk23.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Vector2D} class.
 */
class Vector2DTest {

    /**
     * Tests the constructor and getters of the {@link Vector2D} class.
     */
    @Test
    void testConstructorAndGetters() {
        // Test the constructor and getters

        final double x = 1.0;
        final double y = 2.0;

        final Vector2D vector = new Vector2D(x, y);

        // Assert that the getX and getY methods return the expected values
        assertTrue(Double.compare(x, vector.getX()) == 0);  //NOPMD
        // suppressed as it has to be assertTrue
        assertTrue(Double.compare(y, vector.getY()) == 0);  //NOPMD
        // suppressed as it has to be assertTrue
    }

    /**
     * Tests the setters of the {@link Vector2D} class.
     */
    @Test
    void testSetters() {
        // Test the setters

        final double x = 1.0;
        final double y = 2.0;

        final Vector2D vector = new Vector2D(0, 0);

        // Set the x and y values using the setters
        vector.setX(x);
        vector.setY(y);

        // Assert that the getX and getY methods return the expected values
        assertTrue(Double.compare(x, vector.getX()) == 0);  //NOPMD
        // suppressed as it has to be assertTrue
        assertTrue(Double.compare(y, vector.getY()) == 0);  //NOPMD
        // suppressed as it has to be assertTrue
    }

    /**
     * Tests the sum method of the {@link Vector2D} class.
     */
    @Test
    void testSum() {
        final double x = 4.0;        // Test the sum method
        final double y = 6.0;
        final Vector2D vector1 = new Vector2D(1.0, 2.0);
        final Vector2D vector2 = new Vector2D(3.0, 4.0);

        // Compute the sum of vector1 and vector2
        final Vector2D result = vector1.sum(vector2);

        // Assert that the sum has the expected x and y values
        assertTrue(Double.compare(x, result.getX()) == 0);    //NOPMD
        // suppressed as it has to be assertTrue
        assertTrue(Double.compare(y, result.getY()) == 0);    //NOPMD
        // suppressed as it has to be assertTrue
    }

    /**
     * Tests the module method of the {@link Vector2D} class.
     */
    @Test
    void testModule() {
        final double x = 5.0;
        // Test the module method

        final Vector2D vector = new Vector2D(3.0, 4.0);

        // Compute the module of the vector
        final double result = vector.module();

        // Assert that the module has the expected value
        assertTrue(Double.compare(x, result) == 0);   //NOPMD
        // suppressed as it has to be assertTrue
    }

    /**
     * Tests the mul method of the {@link Vector2D} class.
     */
    @Test
    void testMul() {
        final double x = 5.0;
        final double y = 7.5;
        // Test the mul method

        final Vector2D vector = new Vector2D(2.0, 3.0);

        // Multiply the vector by a scalar value
        final Vector2D result = vector.mul(2.5);

        // Assert that the resulting vector has the expected x and y values
        assertTrue(Double.compare(x, result.getX()) == 0);    //NOPMD
        // suppressed as it has to be assertTrue
        assertTrue(Double.compare(y, result.getY()) == 0);    //NOPMD
        // suppressed as it has to be assertTrue
    }

    /**
     * Tests the hashCode method of the {@link Vector2D} class.
     */
    @Test
    void testHashCode() {
        // Test the hashCode method

        final Vector2D vector1 = new Vector2D(1.0, 2.0);
        final Vector2D vector2 = new Vector2D(1.0, 2.0);

        // Assert that the hash codes of vector1 and vector2 are equal
        assertEquals(vector1.hashCode(), vector2.hashCode());   //NOPMD
        // suppressed as it has to be assertTrue
    }

    /**
     * Tests the equals method of the {@link Vector2D} class.
     */
    @Test
    void testEquals() {
        // Test the equals method

        final Vector2D vector1 = new Vector2D(1.0, 2.0);
        final Vector2D vector2 = new Vector2D(1.0, 2.0);
        final Vector2D vector3 = new Vector2D(3.0, 4.0);

        // Assert that vector1 is equal to vector2 and not equal to vector3
        assertTrue(vector1.equals(vector2));    //NOPMD
        // suppressed as it has to be assertTrue
        assertFalse(vector1.equals(vector3));   //NOPMD
        // suppressed as it has to be assertTrue
    }
}


