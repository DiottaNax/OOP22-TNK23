package it.unibo.tnk23.game.events.api;

/**
 * The WorldEventListener interface represents a listener for world events.
 */
public interface WorldEventListener {

    /**
     * Notifies the world about a world event.
     *
     * @param we the world event to notify about
     */
    void notifyEvent(WorldEvent we);
}
