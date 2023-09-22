package it.unibo.tnk23.view.api;

import it.unibo.tnk23.core.api.GameEngine;
import it.unibo.tnk23.game.events.api.GameEventListener;
import it.unibo.tnk23.game.model.api.World;

/**
 * The {@code GameView} interface represents a view component of the game.
 * It defines the methods for rendering the game view and set different game scenes.
 */
public interface GameView extends GameEventListener {

    /**
     * Renders the game view.
     */
    void renderView();

    /**
     * Sets the menu scene.
     * The menu should let the player decide game settings and start the game.
     */
    void setMenuScene();

    /**
     * Sets the game scene.
     *  This scene should display the main gameplay area where the game takes place.
     */
    void setGameScene();

    /**
     * Sets the game over scene.
     * The game over scene should let the player decide whether to play another time or exit the game.
     */
    void setGameOverScene();

    /**
     * Returns the game engine associated with the view.
     *
     * @return The game engine.
     */
    GameEngine getGameEngine();

    /**
     * Sets the game world.
     *
     * @param world The game world to set.
     */
    void setWorld(World world);
}
