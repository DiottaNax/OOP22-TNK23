package it.unibo.tnk23.common.shape;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.common.Point2D;

/**
 * Represents a two-dimensional rectangle in space.
 */
public class Rect2D implements Shape {

    private final double width;
    private final double height;
    private Point2D pos;

    /**
     * Creates a new Rect2D object with the specified width, height, and position.
     *
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     * @param pos    the position of the rectangle
     */
    @SuppressFBWarnings (
        value = {
            "EI"
        },
            justification = "This constructor needs the position that is passed"
    )
    public Rect2D(final double width, final double height, final Point2D pos) {
        this.width = width;
        this.height = height;
        this.pos = pos;
    }

    /**
     * Creates a new Rect2D object with the same width and height in the specified position.
     * This constructor is meant to easily create a square.
     * 
     * @param edge the width and height of the rectangle
     * @param pos  the position of the rectangle
     */
    public Rect2D(final double edge, final Point2D pos) {
        this(edge, edge, pos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the position of the rectangle.
     *
     * @return the position of the rectangle
     */
    @Override
    public Point2D getPos() {
        return new Point2D(pos.getX(), pos.getY()); 
    }

    /**
     * Sets the position of the rectangle.
     *
     * @param newPos the new position of the rectangle
     */
    @Override
    public void setPos(final Point2D newPos) {
        this.pos = new Point2D(newPos.getX(), newPos.getY()); // Store a defensive copy of the newPos object
    }

    /**
     {@inheritDoc}
     */
    @Override
    public boolean isColliding(final Shape shape) {
        if (shape instanceof Rect2D) {
            return this.intersects((Rect2D) shape);
        }
        return false;
    }

    private boolean intersects(final Rect2D rectangle) {
        return Math.abs(this.pos.getX() - rectangle.getPos().getX()) <= (this.width + rectangle.getWidth()) / 2
                && Math.abs(this.pos.getY() - rectangle.getPos().getY()) <= (this.height + rectangle.getHeight()) / 2;
    }

}
