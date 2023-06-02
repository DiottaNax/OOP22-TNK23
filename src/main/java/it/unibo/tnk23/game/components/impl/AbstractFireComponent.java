package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

public abstract class AbstractFireComponent extends AbstractComponent{
    
    protected int currentFarme = 0;

    public AbstractFireComponent(final GameObject entity, final World world) {
        super(entity, world);
    }

    @Override
    public void update() {
        if (canShoot()) {
            currentFarme = 0;
            world.notifyEvent(new WorldEventImpl(entity.getPosition(), entity, WorldEventType.SHOOT_EVENT));
        }
    }
    
    protected abstract boolean canShoot();

}
