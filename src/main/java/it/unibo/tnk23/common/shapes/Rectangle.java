package it.unibo.tnk23.common.shapes;


import it.unibo.tnk23.common.Shape;
import it.unibo.tnk23.common.Vector2D;

public class Rectangle implements Shape {

    private Vector2D center;
    private double width;
    private double height;

    public Rectangle(final Vector2D center, final double width, final double height) {
        this.center = center;
        this.width = width;
        this.height = height;
    }

    @Override
    public Vector2D getCenter() {
        return this.center;
    }

    @Override
    public void setCenter(Vector2D center) {
        this.center = center;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(final double width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(final double height) {
        this.height = height;
    }

    @Override
    public boolean isColliding(final Shape shape) {
       return shape.isColliding(this);
    }

    @Override
    public boolean isColliding(Rectangle rectangle) {
        return Math.abs(this.center.getX() - rectangle.getCenter().getX()) < (this.width + rectangle.getWidth()) / 2
                &&
                Math.abs(this.center.getY() - rectangle.getCenter().getY()) < (this.height + rectangle.getHeight()) / 2;
    }


}


