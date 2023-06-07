package it.unibo.tnk23.view.api;

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
