package it.unibo.tnk23.core.api;

import it.unibo.tnk23.game.model.api.GameState;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.view.api.GameView;

/**
 * The {@code GameEngine} interface represents the game engine responsible for managing the game state,
 * updating the game world, and coordinating with the game view for rendering and user interaction.
 */
public interface GameEngine {

    /**
     * Retrieves the game world associated with the game engine.
     *
     * @return the game world
     */
    World getWorld();

    /**
     * Retrieves the game state mainteining the information about the current state of the game.
     *
     * @return the game state
     */
    GameState getGameState();

    /**
     * Starts the game engine, initiating the game loop and updating the game world.
     */
    void startEngine();

    /**
     * Retrieves the game view associated with the game engine.
     *
     * @return the game view
     */
    GameView getGameView();
}

