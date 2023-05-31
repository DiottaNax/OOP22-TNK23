package it.unibo.tnk23.common.shape;

import it.unibo.tnk23.common.Point2D;

public class Rect2D implements Shape {

    private final double width;
    private final double height;
    private Point2D pos;

    public Rect2D(final double width, final double height, final Point2D pos) {
        this.width = width;
        this.height = height;
        this.pos = pos;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }
 
    @Override
    public Point2D getPos() {
        return this.pos;
    }

    
    @Override
    public void setPos(final Point2D newPos) {
        this.pos = newPos;
    }

    @Override
    public boolean isColliding(final Shape shape) {
        if(shape instanceof Rect2D) {
            return this.intersects((Rect2D)shape);
        }
        return false;
    }
    
    private boolean intersects(final Rect2D rectangle) {
        return Math.abs(this.pos.getX() - rectangle.getPos().getX()) <= (this.width + rectangle.getWidth()) / 2 
            && Math.abs(this.pos.getY() - rectangle.getPos().getY()) <= (this.height + rectangle.getHeight()) / 2;
    }
    
}
