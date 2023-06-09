package it.unibo.tnk23.game.model.impl;

import java.util.Objects;

import it.unibo.tnk23.game.model.api.GameObjectType;

/**
 * The TypeObjectImpl class implements the TypeObject interface and represents
 * the characteristics of a game object type.
 */
public final class GameObjectTypeImpl implements GameObjectType {

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
    public GameObjectTypeImpl(final long width, final long height, final double speed, final long health) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(width, height, speed, health);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } 
        if (obj == null || getClass() != obj.getClass()) {
             return false;
        }
        final GameObjectTypeImpl other = (GameObjectTypeImpl) obj;
        return width == other.width
                && height == other.height
                && Double.compare(speed, other.speed) == 0
                && health == other.health;
    }

}
