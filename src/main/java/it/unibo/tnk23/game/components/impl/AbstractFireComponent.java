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
    
    protected Optional<GameObject> bullet;
    protected long currentTime;
    protected long lastTime;
    private boolean mRecived;

    public AbstractFireComponent(GameObject entity, World world) {
        super(entity, world);
    }

    @Override
    public <X> void receive(Message<X> message) {
        mRecived = message.getMessage().toString().isEmpty() ? false : true;
    }

    @Override
    public void update() {
        TypeObject bulletType = TypeObjectFactory.getBulletType();
        if(mRecived) {
            if(canSpawn()) {
                bullet = Optional.of(new GameObjectImpl(bulletType, entity.getPosition(), null));
                world.notifyEvent(new WorldEventImpl(entity.getPosition(), bullet.get(), WorldEventType.SHOOT_EVENT));
                lastTime = System.currentTimeMillis();
                currentTime = System.currentTimeMillis();
            }
            if(world.getEntities().contains(bullet.get())) {
                bullet = Optional.empty();
                currentTime = System.currentTimeMillis() - lastTime;
            }
        }
        
    }
    
    protected abstract boolean canSpawn();

}
