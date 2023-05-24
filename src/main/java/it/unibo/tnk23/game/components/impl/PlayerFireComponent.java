package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.world.api.World;

public class PlayerFireComponent extends AbstractFireComponent implements NotifiableComponent{

    private boolean canShoot = false;
    
    public PlayerFireComponent(GameObject entity, World world) {
        super(entity, world);
    }

    @Override
    public <X> void receive(Message<X> x) {
        if(x.getMessage() instanceof Boolean) {
            canShoot = (Boolean) x.getMessage();
        }
    }

    @Override
    protected boolean canShoot() {
        return lastBullet.isEmpty() && canShoot;
    }
    
}
