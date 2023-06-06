package it.unibo.tnk23.core.api;

import it.unibo.tnk23.game.events.api.WorldEventListener;

/**
 * The {@code GameLoop} interface represents the main game loop for TNK23.
 * It defines the methods for processing input, updating the game state, rendering the game, and obtaining the game engine instance.
 * The interface extends the {@link WorldEventListener} interface, allowing it to receive and handle world events.
 */
public interface GameLoop extends WorldEventListener{

    /**
     * Processes events for the game.
     * This method should handle all the events passed to the game loop.
     */
    void processInput();

    /**
     * Updates the game state.
     * This method should update the game state based on user input, AI behavior, physics, etc.
     */
    void update();

    /**
     * Renders the game.
     * This method should render the game view based on the current game state.
     */
    void render();

    /**
     * Retrieves the game engine instance.
     *
     * @return the game engine instance associated with the game loop.
     */
    GameEngine getGameEngine();

}
