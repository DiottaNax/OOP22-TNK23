package it.unibo.tnk23.game.model.impl;

/**
 * The TypeObjectImpl class implements the TypeObject interface and represents
 * the characteristics of a game object type.
 */
public final class GameObjectInfo {

    private final long width;
    private final long height;
    private final double speed;
    private final long health;
    private final String spriteName;

    /**
     * Creates a new TypeObjectImpl instance with the specified width, height, speed, and health.
     *
     * @param width  the width of the game object type
     * @param height the height of the game object type
     * @param speed  the speed of the game object type
     * @param health the health of the game object type
     */
    public GameObjectInfo(final long width, final long height, final double speed, final long health,
            final String spriteName) {
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.health = health;
        this.spriteName = spriteName;
    }

    public long getWidth() {
        return this.width;
    }

    public long getHeight() {
        return this.height;
    }

    public double getSpeed() {
        return this.speed;
    }

    public long getHealth() {
        return this.health;
    }

    public String getSpriteName() {
        return this.spriteName;
    }
}
