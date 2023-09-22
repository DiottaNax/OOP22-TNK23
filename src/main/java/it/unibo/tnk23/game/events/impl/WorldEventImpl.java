package it.unibo.tnk23.game.events.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.events.api.GameEvent;
import it.unibo.tnk23.game.events.api.GameEventType;
import it.unibo.tnk23.game.model.api.GameObject;

/**
 * Implementation of {@link GameEvent} interface representing an event that occurs in the game world.
 */
public class WorldEventImpl implements GameEvent {

    private final Point2D position;
    private final GameObject actor;
    private final GameEventType type;

    /**
     * Constructs a new {@link WorldEventImpl} with the specified position, actor, and event type.
     * 
     * @param position the position associated with the event
     * @param actor the {@link GameObject} associated with the event
     * @param type the {@link GameEventType} representing the type of the event
     */
    @SuppressFBWarnings(value = { "EI2" }, justification =
    "WorldEventImpl must store the original actor in order to assign the event to that specific actor."
    )
    public WorldEventImpl(final Point2D position, final GameObject actor, final GameEventType type) {
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
    public GameEventType getType() {
        return type;
    }

}
