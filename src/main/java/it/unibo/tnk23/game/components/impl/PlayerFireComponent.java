package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

public class PlayerFireComponent extends AbstractFireComponent implements NotifiableComponent{

    private boolean canShoot = false;
    private final static int SHOOT_PERIOD = 2 * Configuration.FPS;
    
    public PlayerFireComponent(GameObject entity, World world) {
        super(entity, world);
    }

    @Override
    public <X> void receive(Message<X> x) {
        if (x.getMessage() instanceof Boolean) {
            canShoot = (Boolean) x.getMessage();
        }
    }
    
    @Override
    public void update() {
        super.currentFarme++;
        super.update();
    }

    @Override
    protected boolean canShoot() {
        return currentFarme >= SHOOT_PERIOD && canShoot;
    }
    
}
