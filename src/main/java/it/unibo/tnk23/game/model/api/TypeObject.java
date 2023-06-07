package it.unibo.tnk23.game.model.api;

/**
 * The TypeObject interface represents the characteristics of a game object type.
 */
public interface TypeObject {

    /**
     * Retrieves the width of the game object type.
     *
     * @return the width of the game object type
     */
    long getWidth();

    /**
     * Retrieves the height of the game object type.
     *
     * @return the height of the game object type
     */
    long getHeight();

    /**
     * Retrieves the speed of the game object type.
     *
     * @return the speed of the game object type
     */
    double getSpeed();

    /**
     * Retrieves the health of the game object type.
     *
     * @return the health of the game object type
     */
    long getHealth();
}

