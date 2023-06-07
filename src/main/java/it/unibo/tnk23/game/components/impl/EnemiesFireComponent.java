package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

public class EnemiesFireComponent extends AbstractFireComponent{

    private final static int SHOOT_PERIOD = 3 * Configuration.FPS;

    public EnemiesFireComponent(GameObject entity, World world) {
        super(entity, world);
    }

    @Override
    public void update() {
        super.currentFarme++;
        super.update();
    }

    @Override
    protected boolean canShoot() {
        return currentFarme >= SHOOT_PERIOD;
    }
    
}
