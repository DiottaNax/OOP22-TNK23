package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

public class TemporaryHealthComponent extends AbstractComponent {

    private final int lifeTime;
    private int currentFrame;

    public TemporaryHealthComponent(GameObject entity, World world, int lifeTime) {
        super(entity, world);
        this.lifeTime = lifeTime;
    }

    @Override
    public void update() {
        if (currentFrame >= lifeTime) {
            world.notifyEvent(new WorldEventImpl(this.entity.getPosition(), this.entity, WorldEventType.DEATH_EVENT));
        }
        else {
            currentFrame++;
        }
    }
    
}
