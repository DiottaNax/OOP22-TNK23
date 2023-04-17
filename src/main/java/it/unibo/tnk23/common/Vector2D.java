package it.unibo.tnk23.common;

public class Vector2D {
    
    private double x;
    private double y;

    public Vector2D(double x, double y) {
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

    public Vector2D sum(Vector2D v){
        return new Vector2D(this.x + v.getX(), this.y + v.getY());
    }

    public double module(){
        return (double) Math.sqrt(this.x * x + this.y * y);
    }

    public Vector2D mul(double mul){
        return new Vector2D(this.x * mul, this.y * mul);
    }

    @Override
    public String toString(){
        return "Vector2D [" + this.x + ", " + this.y + "]";
    }
    
}
