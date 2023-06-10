package it.unibo.tnk23.common;

import it.unibo.tnk23.common.shape.Rect2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Rect2DTest {

    @Test
    public void testGetWidth_ShouldReturnWidth() {
        // Prepare test data
        double width = 10;
        double height = 20;
        Point2D pos = new Point2D(0, 0);
        Rect2D rectangle = new Rect2D(width, height, pos);

        // Perform the test
        double result = rectangle.getWidth();

        // Verify the result
        assertEquals(width, result);
    }

    @Test
    public void testGetHeight_ShouldReturnHeight() {
        // Prepare test data
        double width = 10;
        double height = 20;
        Point2D pos = new Point2D(0, 0);
        Rect2D rectangle = new Rect2D(width, height, pos);

        // Perform the test
        double result = rectangle.getHeight();

        // Verify the result
        assertEquals(height, result);
    }

    @Test
    public void testGetPos_ShouldReturnPosition() {
        // Prepare test data
        double width = 10;
        double height = 20;
        Point2D pos = new Point2D(5, 10);
        Rect2D rectangle = new Rect2D(width, height, pos);

        // Perform the test
        Point2D result = rectangle.getPos();

        // Verify the result
        assertEquals(pos, result);
    }

    @Test
    public void testSetPos_ShouldUpdatePosition() {
        // Prepare test data
        double width = 10;
        double height = 20;
        Point2D pos = new Point2D(5, 10);
        Rect2D rectangle = new Rect2D(width, height, pos);
        Point2D newPos = new Point2D(15, 25);

        // Perform the test
        rectangle.setPos(newPos);
        Point2D result = rectangle.getPos();

        // Verify the result
        assertEquals(newPos, result);
    }

    @Test
    public void testIsColliding_ShouldReturnTrueIfIntersecting() {
        // Prepare test data
        double width = 10;
        double height = 10;
        Point2D pos1 = new Point2D(5, 5);
        Rect2D rectangle1 = new Rect2D(width, height, pos1);
        Point2D pos2 = new Point2D(8, 8);
        Rect2D rectangle2 = new Rect2D(width, height, pos2);

        // Perform the test
        boolean result = rectangle1.isColliding(rectangle2);

        // Verify the result
        assertTrue(result);
    }

    @Test
    public void testIsColliding_ShouldReturnFalseIfNotIntersecting() {
        // Prepare test data
        double width = 10;
        double height = 10;
        Point2D pos1 = new Point2D(5, 5);
        Rect2D rectangle1 = new Rect2D(width, height, pos1);
        Point2D pos2 = new Point2D(20, 20);
        Rect2D rectangle2 = new Rect2D(width, height, pos2);

        // Perform the test
        boolean result = rectangle1.isColliding(rectangle2);

        // Verify the result
        assertFalse(result);
    }
}
