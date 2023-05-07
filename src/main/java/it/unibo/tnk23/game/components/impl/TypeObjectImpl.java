package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.components.api.TypeObject;

public final class TypeObjectImpl implements TypeObject {

    private final long width;
    private final long height;
    private final double speed;
    private final long health;
    
    public TypeObjectImpl(long width, long height, double speed, long health) {
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.health = health;
    }

    @Override
    public long getWidth() {
        return this.width;
    }

    @Override
    public long getheight() {
        return this.height;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public long getHealth() {
        return this.health;
    }

    @Override
    public String toString() {
        return "TypeObjectImpl [width=" + width + ", height=" + height + ", speed=" + speed + ", health=" + health
                + "]";
    }
    
}
