package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

/**
 * A component representing the firing behavior of enemies.
 * The EnemiesFireComponent handles the firing logics based on a fixed time interval for enemies GameObject.
 */
public class EnemiesFireComponent extends AbstractFireComponent{

    private final static int SHOOT_PERIOD = 2 * Configuration.FPS;

    /**
     * Constructs a new {@link EnemiesFireComponent} with te specified entity and world.
     * 
     * @param entity the GameObject associated with this fire component
     * @param world the World in which the enemy fire component exists.
     */
    public EnemiesFireComponent(GameObject entity, World world) {
        super(entity, world);
    }

    /**
     * {@inheritDoc}
     * Increases the current frame count and calls the superclass update method.
     */
    @Override
    public void update() {
        super.currentFarme++;
        super.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean canShoot() {
        return currentFarme >= SHOOT_PERIOD;
    }
    
}
