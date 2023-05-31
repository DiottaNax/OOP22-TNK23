package it.unibo.tnk23.common.shape;

import it.unibo.tnk23.common.Point2D;

public interface Shape {

    double getWidth();

    double getHeight();

    Point2D getPos();

    void setPos(Point2D newCenter);

    boolean isColliding(Shape shape);
    
}
