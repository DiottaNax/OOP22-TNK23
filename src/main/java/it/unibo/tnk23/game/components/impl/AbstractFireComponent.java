package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

/**
 * An abstract base class for fire components.
 * Fire components represent a component that handle shooting behavior.
 * It provide common functionality and variables for fire components.
 */
public abstract class AbstractFireComponent extends AbstractComponent {

    /**
     * The current frame for shooting.
     */
    private int currentFrame = 0;

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
        this.currentFrame++;
        if (canShoot()) {
            currentFrame = 0;
            this.getWorld().notifyEvent(
                    new WorldEventImpl(this.getEntity().getPosition(), this.getEntity(), WorldEventType.SHOOT_EVENT));
        }
    }

    /**
     * Retrieves the current frame number.
     *
     * @return The current frame number.
     */
    public int getCurrentFrame() {
        return this.currentFrame;
    }

    /**
     * Checks if the entity can shoot.
     * 
     * @return {@code true} if the entity can shoot, {@code false} otherwise.
     */
    protected abstract boolean canShoot();

}
