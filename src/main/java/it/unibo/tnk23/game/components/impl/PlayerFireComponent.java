package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

public class PlayerFireComponent extends AbstractFireComponent{

    public PlayerFireComponent(GameObject entity, World world) {
        super(entity, world);
    }

    @Override
    protected boolean canSpawn() {
        return lastBullet.isEmpty() ? true : false;
    }
    
}
