package it.unibo.tnk23.game.events.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.events.api.WorldEvent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.model.api.GameObject;

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
    @SuppressFBWarnings(value = { "EI2" }, justification =
    "WorldEventImpl must store the original actor in order to assign the event to that specific actor."
    )
    public WorldEventImpl(final Point2D position, final GameObject actor, final WorldEventType type) {
        this.position = new Point2D(position.getX(), position.getY());
        this.actor = actor;
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
    @SuppressFBWarnings (
        value = {
            "EI"
        },
            justification = "this method needs to return the original actor in order to use its methods"
    )
    @Override
    public GameObject getEventActor() {
        return this.actor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WorldEventType getType() {
        return type;
    }

}
