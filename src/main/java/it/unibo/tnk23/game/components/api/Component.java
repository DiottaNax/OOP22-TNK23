package it.unibo.tnk23.game.components.api;

/**
 * An interface that rapresent a game component.
 * A component provide specific functionalities that can be attached to game objects.
 */
public interface Component {
    
    /**
     * Updates the component's state or carries out any required operations.
     * Is typically invoked once per farme.
     */
    void update();

}
