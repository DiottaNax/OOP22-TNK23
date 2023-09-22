package it.unibo.tnk23.view.api;

import it.unibo.tnk23.game.events.api.GameEventHandler;
import it.unibo.tnk23.game.events.api.GameEventListener;

/**
 * The RenderingEngine interface provides a contract for rendering the game.
 *
 * @param <X> the type of the game pane used for rendering
 */
public interface RenderingEngine<X> extends GameEventHandler {

    /**
     * Retrieves the game pane for rendering.
     *
     * @return the game pane of type X
     */
    X getGamePane();

    /**
     * Renders the game.
     */
    void render();
}
