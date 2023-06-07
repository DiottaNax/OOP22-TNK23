package it.unibo.tnk23.game.model.api;

/**
 * An interface representing a spawn mechanism in the game.
 * The Spawn interface provides methods to start and update the spawning process.
 */
public interface Spawn {
    
    /**
     * Starts the spawning process.
     * This method is called to intiate the spawning of new game objects.
     */
    void startSpawn();

    /**
     * Updates the spawning process.
     * This method is called to update the spawning logic.
     */
    void update();
}
