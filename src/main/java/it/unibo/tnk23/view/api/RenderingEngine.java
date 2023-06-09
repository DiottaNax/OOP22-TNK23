package it.unibo.tnk23.view.api;

/**
 * The RenderingEngine interface provides a contract for rendering the game.
 *
 * @param <X> the type of the game pane used for rendering
 */
public interface RenderingEngine<X> {

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
