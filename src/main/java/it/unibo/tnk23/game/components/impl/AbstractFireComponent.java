package it.unibo.tnk23.game.components.impl;

import java.util.Optional;


import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.TypeObject;
import it.unibo.tnk23.game.model.impl.GameObjectImpl;
import it.unibo.tnk23.game.model.impl.TypeObjectFactory;
import it.unibo.tnk23.game.world.api.World;

public abstract class AbstractFireComponent extends AbstractComponent implements NotifiableComponent{
    
    protected Optional<GameObject> lastBullet;
    private boolean canShoot = false;

    public AbstractFireComponent(final GameObject entity, final World world) {
        super(entity, world);
    }

    @Override
    public <X> void receive(final Message<X> message) {
        if(message.getMessage() instanceof Boolean) {
            canShoot = (Boolean) message.getMessage();
        }
    }

    @Override
    public void update() {
        TypeObject bulletType = TypeObjectFactory.getBulletType();
            if(canShoot && canSpawn()) {
                lastBullet = Optional.of(new GameObjectImpl(bulletType, entity.getPosition()));
                lastBullet.get().setPower(entity.getPower());
                world.notifyEvent(new WorldEventImpl(entity.getPosition(), lastBullet.get(), WorldEventType.SHOOT_EVENT));
            }
            if(world.getEntities().contains(lastBullet.get())) {
                lastBullet = Optional.empty();
            }
        
    }
    
    protected abstract boolean canSpawn();

}
