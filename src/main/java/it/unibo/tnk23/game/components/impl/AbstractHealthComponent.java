package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

/**
 * This abstract class represents a health component for game objects. It extends the
 * AbstractComponent class and implements the NotifiableComponent interface.
 */
public abstract class AbstractHealthComponent extends AbstractComponent implements NotifiableComponent {

    private int health;

    /**
     * Constructs an AbstractHealthComponent with the specified parameters GameObject and World.
     *
     * @param entity the GameObject associated with this component
     * @param world  the World object in which the component exists
     */
    public AbstractHealthComponent(final GameObject entity, final World world) {
        super(entity, world);
        this.health = (int) entity.getType().getHealth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (health <= 0) {
            this.getWorld().notifyEvent(
                    new WorldEventImpl(this.getEntity().getPosition(), this.getEntity(), WorldEventType.DEATH_EVENT));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <X> void receive(final Message<X> x) {
        if (isTouchable() && x.getMessage() instanceof GameObject) {
            final GameObject obj = (GameObject) x.getMessage();
            health -= obj.getPower();
        }
    }

    /**
     * Returns the current health value of the component.
     *
     * @return the current health value
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Sets the parameter as the current health value of the component.
     *
     *  @param health the new value to set
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * Checks if the component is touchable.
     *
     * @return true if the component is touchable, false otherwise
     */
    protected abstract boolean isTouchable();
}
