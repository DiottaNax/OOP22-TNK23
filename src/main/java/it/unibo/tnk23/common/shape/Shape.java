package it.unibo.tnk23.common.shape;

import it.unibo.tnk23.common.Point2D;

import it.unibo.tnk23.common.shapes.Rectangle;

public interface Shape {
    
    Vector2D getCenter();

    void setCenter(Vector2D pos);

    boolean isColliding(Shape shape);

    boolean isColliding(Rectangle rectangle);
    

}
