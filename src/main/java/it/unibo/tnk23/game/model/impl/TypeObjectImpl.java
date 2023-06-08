package it.unibo.tnk23.game.model.impl;

import it.unibo.tnk23.game.model.api.TypeObject;

/**
 * The TypeObjectImpl class implements the TypeObject interface and represents
 * the characteristics of a game object type.
 */
public final class TypeObjectImpl implements TypeObject {

    private final long width;
    private final long height;
    private final double speed;
    private final long health;
    
    /**
     * Creates a new TypeObjectImpl instance with the specified width, height, speed, and health.
     *
     * @param width  the width of the game object type
     * @param height the height of the game object type
     * @param speed  the speed of the game object type
     * @param health the health of the game object type
     */
    public TypeObjectImpl(final long width, final long height, final double speed, final long health) {
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.health = health;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getHealth() {
        return this.health;
    }

    /**
     * Returns a string representation of the TypeObjectImpl.
     *
     * @return a string representation of the TypeObjectImpl
     */
    @Override
    public String toString() {
        return "TypeObjectImpl [width=" + width + ", height=" + height + ", speed=" + speed + ", health=" + health
                + "]";
    }
    
}