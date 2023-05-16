package it.unibo.tnk23.common;

public class Rect2D implements Shape {

    private final double width;
    private final double height;
    private Point2D center;

    public Rect2D(final double width, final double height, final Point2D position) {
        this.width = width;
        this.height = height;
        this.center = new Point2D(position.getX() + (width / 2), position.getY() - (height / 2));
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }
 
    @Override
    public Point2D getCenter() {
        return this.center;
    }

    
    @Override
    public void setCenter(final Point2D newCenter) {
        this.center = newCenter;
    }

    @Override
    public boolean isColliding(final Shape shape) {
        if(shape instanceof Rect2D) {
            return this.intersects((Rect2D)shape);
        }
        return false;
    }
    
    private boolean intersects(final Rect2D rectangle) {
        return Math.abs(this.center.getX() - rectangle.getCenter().getX()) <= (this.width + rectangle.getWidth()) / 2 
            && Math.abs(this.center.getY() - rectangle.getCenter().getY()) <= (this.height + rectangle.getHeight()) / 2;
    }
    
}
