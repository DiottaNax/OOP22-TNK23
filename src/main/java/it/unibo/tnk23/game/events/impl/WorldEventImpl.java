package it.unibo.tnk23.game.events.impl;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.events.api.WorldEvent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.impl.GameObjectImpl;

/**
 * Implementation of {@link WorldEvent} interface representing an event that occurs in the game world.
 */
public class WorldEventImpl implements WorldEvent {

    private final Point2D position;
    private final GameObject actor;
    private final WorldEventType type;

    /**
     * Constructs a new {@link WorldEventImpl} with the specified position, actor, and event type.
     * 
     * @param position the position associated with the event
     * @param actor the {@link GameObject} associated with the event
     * @param type the {@link WorldEventType} representing the type of the event
     */
    public WorldEventImpl(final Point2D position, final GameObject actor, final WorldEventType type) {
        this.position = new Point2D(position.getX(), position.getY());
        this.actor = new GameObjectImpl(actor.getType(), actor.getPosition());
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getPosition() {
        return new Point2D(this.position.getX(), this.position.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getEventActor() {
        GameObject actorCopy = new GameObjectImpl(this.actor.getType(), this.actor.getPosition());
        return actorCopy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorldEventType getType() {
        return type;
    }

}
