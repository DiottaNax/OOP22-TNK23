package it.unibo.tnk23.common;

import java.util.Objects;

public class Point2D {
    private double x;
    private double y;
    
    public Point2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(final double y) {
        this.y = y;
    }

    public Point2D sum(final Vector2D v) {
        return new Point2D(this.x + v.getX(), this.y + v.getY());
    }
    
    public Point2D sub(final Vector2D v) {
        return this.sum(new Vector2D(-v.getX(), -v.getY()));
    }

    @Override
    public String toString() {
        return "Point2D [x=" + this.x + ", y=" + this.y + "]";
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 13 * result + Objects.hashCode(this.x);
        result = 13 * result + Objects.hashCode(this.y);
        return result;
    }

    @Override
    public boolean equals(Object p) {
        if (p != null && p.getClass().equals(this.getClass())) {
            var p2d = (Point2D) p;
            if (Double.compare(p2d.getX(), -0.0) == 0) {
                p2d.setX(0);
            } else if (Double.compare(p2d.getY(), -0.0) == 0) {
                p2d.setY(0);
            }

            return Double.compare(y, p2d.getY()) == 0 && Double.compare(x, p2d.getX()) == 0;
        }
        
        return false;
    }

}
