package it.unibo.tnk23.game.events.api;

/**
 * The WorldEventHandler interface represents a handler for processing world events.
 */
public interface WorldEventHandler {

    /**
     * Handles the given world event.
     *
     * @param we the world event to handle
     */
    void handle(WorldEvent we);
}

