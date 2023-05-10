package it.unibo.tnk23.common;

public interface Shape {

    double getWidth();

    double getHeight();

    Point2D getCenter();

    void setCenter(Point2D newCenter);

    boolean isColliding(Shape shape);
    
    
}
