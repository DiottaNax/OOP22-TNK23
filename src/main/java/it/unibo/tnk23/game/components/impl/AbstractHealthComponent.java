package it.unibo.tnk23.game.components.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.TypeObject;
import it.unibo.tnk23.game.model.api.World;

public abstract class AbstractHealthComponent extends AbstractComponent implements NotifiableComponent {

    protected int health;
    protected Set<TypeObject> weaknesses = new HashSet<>();

    public AbstractHealthComponent(GameObject entity, World world) {
        super(entity, world);
        this.health = (int) entity.getType().getHealth();
    }

    @Override
    public void update() {
        if (health <= 0) {
            world.notifyEvent(new WorldEventImpl(this.entity.getPosition(), this.entity, WorldEventType.DEATH_EVENT));
        }
    }

    @Override
    public <X> void receive(Message<X> x) {
        if ((isTouchable()) && (x.getMessage() instanceof GameObject)) {
            GameObject obj = (GameObject) x.getMessage();
            health -= obj.getPower();
        }
    }

    public int getHealth() {
        return this.health;
    }
    
    protected abstract boolean isTouchable();
}
