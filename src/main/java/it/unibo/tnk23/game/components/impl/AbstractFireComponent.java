package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

/**
 * An abstract base class for fire components.
 * Fire components represent a component that handel shooting behavior.
 * It provide common functionality and variables for fire components.
 */
public abstract class AbstractFireComponent extends AbstractComponent{
    
    /**
     * The current frame for shooting.
     */
    protected int currentFarme = 0;

    /**
     * Constructs a new {@link AbstractFireComponent} whith the specified entity and wolrd.
     * 
     * @param entity the GameObject that owns this component.
     * @param world the World in which the component exists.
     */
    public AbstractFireComponent(final GameObject entity, final World world) {
        super(entity, world);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (canShoot()) {
            currentFarme = 0;
            world.notifyEvent(new WorldEventImpl(entity.getPosition(), entity, WorldEventType.SHOOT_EVENT));
        }
    }
    
    /**
     * Checks if the entity can shoot
     * 
     * @return {@code true} if the entity can shoot, {@code false} otherwise.
     */
    protected abstract boolean canShoot();

}
