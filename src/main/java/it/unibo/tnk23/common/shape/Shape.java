package it.unibo.tnk23.common.shape;

import it.unibo.tnk23.common.Point2D;

/**
 * Represents a two-dimensional shape.
 */
public interface Shape {

    /**
     * Returns the width of the shape.
     *
     * @return the width of the shape
     */
    double getWidth();

    /**
     * Returns the height of the shape.
     *
     * @return the height of the shape
     */
    double getHeight();

    /**
     * Returns the position of the shape.
     *
     * @return the position of the shape
     */
    Point2D getPos();

    /**
     * Sets the position of the shape.
     *
     * @param newPos the new position of the shape
     */
    void setPos(Point2D newPos);

    /**
     * Checks if the shape is colliding with the given shape.
     *
     * @param shape the shape to check collision with
     * @return true if the shape is colliding with the given shape, false otherwise
     */
    boolean isColliding(Shape shape);

}
