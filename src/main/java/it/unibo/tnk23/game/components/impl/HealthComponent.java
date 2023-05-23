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
import it.unibo.tnk23.game.model.impl.TypeObjectFactory;
import it.unibo.tnk23.game.world.api.World;

public abstract class HealthComponent extends AbstractComponent implements NotifiableComponent {

    private int health;
    private Set<TypeObject> weaknesses = new HashSet<>();

    public HealthComponent(GameObject entity, World world) {
        super(entity, world);
        this.health = (int) entity.getType().getHealth();
    }

    @Override
    public void update() {
        if (health == 0) {
            world.notifyEvent(new WorldEventImpl(this.entity.getPosition(), this.entity, WorldEventType.DEATH_EVENT));
        }
    }

    @Override
    public <X> void receive(Message<X> x) {
        if (x instanceof GameObject) {
            GameObject obj = (GameObject) x;
            if (TypeObjectFactory.isBullet(obj.getType())) {
                var bulletCmp = obj.getComponent(BulletComponent.class);
                if (bulletCmp.isPresent() && weaknesses.contains(((BulletComponent) bulletCmp.get()).getSourceShooter())) {
                    health -= obj.getPower();
                }
            }
        }
    }
    
}
