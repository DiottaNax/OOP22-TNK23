package it.unibo.tnk23.common;

import it.unibo.tnk23.common.shape.Rect2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Rect2DTest {

    /**
     * Test case for the getWidth() method.
     * It verifies that the method returns the correct width of the rectangle.
     */
    @Test
    void testGetWidth() {
        // Prepare test data
        final double width = 10;
        final double height = 20;
        final Point2D pos = new Point2D(0, 0);
        final Rect2D rectangle = new Rect2D(width, height, pos);

        // Perform the test
        final double result = rectangle.getWidth();

        // Verify the result
        assertEquals(width, result);
    }

    /**
     * Test case for the getHeight() method.
     * It verifies that the method returns the correct height of the rectangle.
     */
    @Test
    void testGetHeight() {
        // Prepare test data
        final double width = 10;
        final double height = 20;
        final Point2D pos = new Point2D(0, 0);
        final Rect2D rectangle = new Rect2D(width, height, pos);

        // Perform the test
        final double result = rectangle.getHeight();

        // Verify the result
        assertEquals(height, result);
    }

    /**
     * Test case for the getPos() method.
     * It verifies that the method returns the correct position of the rectangle.
     */
    @Test
    void testGetPos() {
        // Prepare test data
        final double width = 10;
        final double height = 20;
        final Point2D pos = new Point2D(5, 10);
        final Rect2D rectangle = new Rect2D(width, height, pos);

        // Perform the test
        final Point2D result = rectangle.getPos();

        // Verify the result
        assertEquals(pos, result);
    }

    /**
     * Test case for the setPos() method.
     * It verifies that the method updates the position of the rectangle correctly.
     */
    @Test
    void testSetPos() {
        // Prepare test data
        final double width = 10;
        final double height = 20;
        final Point2D pos = new Point2D(5, 10);
        final Rect2D rectangle = new Rect2D(width, height, pos);
        final Point2D newPos = new Point2D(15, 25);

        // Perform the test
        rectangle.setPos(newPos);
        final Point2D result = rectangle.getPos();

        // Verify the result
        assertEquals(newPos, result);
    }

    /**
     * Test case for the isColliding() method.
     * It verifies that the method returns true if the rectangle is intersecting with another rectangle.
     */
    @Test
    void testIsColliding() {
        // Prepare test data
        final double width = 10;
        final double height = 10;
        final Point2D pos1 = new Point2D(5, 5);
        final Rect2D rectangle1 = new Rect2D(width, height, pos1);
        final Point2D pos2 = new Point2D(8, 8);
        final Rect2D rectangle2 = new Rect2D(width, height, pos2);

        // Perform the test
        final boolean result = rectangle1.isColliding(rectangle2);

        // Verify the result
        assertTrue(result);
    }

    /**
     * Test case for the isColliding() method.
     * It verifies that the method returns false if the rectangle is not intersecting with another rectangle.
     */
    @Test
    void testIsNotCollidingF() {
        // Prepare test data
        final double width = 10;
        final double height = 10;
        final Point2D pos1 = new Point2D(5, 5);
        final Rect2D rectangle1 = new Rect2D(width, height, pos1);
        final Point2D pos2 = new Point2D(20, 20);
        final Rect2D rectangle2 = new Rect2D(width, height, pos2);

        // Perform the test
        final boolean result = rectangle1.isColliding(rectangle2);

        // Verify the result
        assertFalse(result);
    }

}
