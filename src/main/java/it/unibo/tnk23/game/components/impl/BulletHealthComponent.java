package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

public class BulletHealthComponent extends AbstractHealthComponent {

    private boolean isTouchable;

    public BulletHealthComponent(GameObject entity, World world) {
        super(entity, world);
        isTouchable = true;
    }

    @Override
    protected boolean isTouchable() {
        return isTouchable;
    }
    
}
