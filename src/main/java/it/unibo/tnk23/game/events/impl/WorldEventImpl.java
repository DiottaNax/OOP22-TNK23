package it.unibo.tnk23.game.events.impl;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.events.api.WorldEvent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.model.api.GameObject;

/**
 * Implementation of {@link WorldEvent} interface representing an event that occurs in the game world.
 */
public class WorldEventImpl implements WorldEvent {

    private Point2D position;
    private GameObject actor;
    private WorldEventType type;

    /**
     * Constructs a new {@link WorldEventImpl} with the specified position, actor, and event type.
     * 
     * @param position the position associated with the event
     * @param actor the {@link GameObject} associated with the event
     * @param type the {@link WorldEventType} representing the type of the event
     */
    public WorldEventImpl(final Point2D position, final GameObject actor, final WorldEventType type) {
        this.position = position;
        this.actor = actor;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getPosition() {
        return position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getEventActor() {
        return actor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorldEventType getType() {
        return type;
    }

}
