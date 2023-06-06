package it.unibo.tnk23.common;

import java.util.Objects;

public class Vector2D {
    
    private double x;
    private double y;

    public Vector2D(final double x, final double y) {
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

    public Vector2D sum(final Vector2D v){
        return new Vector2D(this.x + v.getX(), this.y + v.getY());
    }

    public double module(){
        return (double) Math.sqrt(this.x * x + this.y * y);
    }

    public Vector2D mul(final double mul){
        return new Vector2D(this.x * mul, this.y * mul);
    }

    @Override
    public String toString() {
        return "Vector2D [" + this.x + ", " + this.y + "]";
    }
    
    @Override
    public int hashCode() {
        int result = 37;
        result = 17 * result + Objects.hashCode(this.x);
        result = 17 * result + Objects.hashCode(this.y);
        return result;
    }

    @Override
    public boolean equals(Object v) {
        if (v != null && v.getClass().equals(this.getClass())) {
            var v2d = (Vector2D) v;
            if (Double.compare(v2d.getX(), -0.0) == 0) {
                v2d.setX(0);
            } else if (Double.compare(v2d.getY(), -0.0) == 0) {
                v2d.setY(0);
            }

            return Double.compare(y, v2d.getY()) == 0 && Double.compare(x, v2d.getX()) == 0;
        }
        
        return false;
    }
    
}
