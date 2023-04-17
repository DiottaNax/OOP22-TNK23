package it.unibo.tnk23.common;

public class Point2D {
    private double x;
    private double y;
    
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point2D sum(Vector2D v) {
        return new Point2D(this.x + v.getX(), this.y + v.getY());
    }
    
    public Point2D sub(Vector2D v) {
        return this.sum(new Vector2D(-v.getX(), -v.getY()));
    }


    @Override
    public String toString() {
        return "Point2D [x=" + this.x + ", y=" + this.y + "]";
    }

}
