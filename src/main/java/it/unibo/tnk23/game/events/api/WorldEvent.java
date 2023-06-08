package it.unibo.tnk23.game.events.api;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.model.api.GameObject;

/**
 * The WorldEvent interface represents an event that occurs within the game world.
 */
public interface WorldEvent {

    /**
     * Recives the position associated with the event.
     *
     * @return the position of the event
     */
    Point2D getPosition();

    /**
     * Recives the game object that triggered the event.
     *
     * @return the game object associated with the event
     */
    GameObject getEventActor();

    /**
     * Recives the type of the event.
     *
     * @return the type of the event
     */
    WorldEventType getType();
}
