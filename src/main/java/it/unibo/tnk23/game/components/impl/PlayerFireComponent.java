package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

/**
 * A component representing the firing bheavior of a player.
 * Implements the {@link NotifiableComponent} interface for receiving messages.
 * The PlayerFireComponent handles the firing logic based on a fixed time interval for player GameObject.
 */
public class PlayerFireComponent extends AbstractFireComponent implements NotifiableComponent {

    private boolean canShoot = false;
    private int currentFarme;
    private static final int SHOOT_PERIOD = 1 * Configuration.FPS;

    /**
     * Constructs a new {@link PlayerFireComponent} with te specified entity and world.
     * 
     * @param entity the GameObject associated with this fire component
     * @param world the World in which the enemy fire component exists.
     */
    public PlayerFireComponent(final GameObject entity, final World world) {
        super(entity, world);
    }

    /**
     * {@inheritDoc}
     * Check if the sender of the message tell to the component the value of variable {@code canShoot}
     */
    @Override
    public <X> void receive(final Message<X> x) {
        if (x.getMessage() instanceof Boolean) {
            canShoot = (Boolean) x.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     * Increases the current frame count and calls the superclass update method.
     */
    @Override
    public void update() {
        currentFarme = this.getCurrentFrame();
        super.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean canShoot() {
        return this.currentFarme >= SHOOT_PERIOD && canShoot;
    }

}
