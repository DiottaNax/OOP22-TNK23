package it.unibo.tnk23.view.api;

import it.unibo.tnk23.core.api.GameEngine;
import it.unibo.tnk23.game.model.api.World;

public interface GameView {

    /**
     * Renders the game view.
     */
    void renderView();

    /**
     * Sets the menu scene.
     */
    void setMenuScene();

    /**
     * Sets the color picker scene.
     */
    void setColorPickerScene();

    /**
     * Sets the game scene.
     */
    void setGameScene();

    /**
     * Sets the game over scene.
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
