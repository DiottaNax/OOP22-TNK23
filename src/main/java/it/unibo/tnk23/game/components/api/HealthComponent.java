package it.unibo.tnk23.game.components.api;

import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.world.api.World;

public abstract class HealthComponent extends AbstractComponent implements NotifiableComponent {

    private long health;

    public HealthComponent(GameObject entity, World world) {
        super(entity, world);
        this.health = entity.getType().getHealth();
    }

    protected abstract void performWhenHit(GameObject entity);

    @Override
    public void update() {
        if (health == 0) {
            world.notifyEvent(new WorldEventImpl(this.entity.getPosition(), this.entity, WorldEventType.DEATH_EVENT));
        }
    }

    @Override
    public <X> void receive(Message<X> x) {
        if (x instanceof GameObject) {
            performWhenHit((GameObject)x);
        }
    }
    
}
