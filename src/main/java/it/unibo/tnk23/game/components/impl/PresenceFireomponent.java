package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.world.api.World;

public class PresenceFireomponent extends AbstractFireComponent{

    public PresenceFireomponent(GameObject entity, World world) {
        super(entity, world);
    }

    @Override
    protected boolean canSpawn() {
        if(bullet.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
}
