package it.unibo.tnk23.game.components.impl;

import java.util.Optional;

import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.world.api.World;

public abstract class AbstractFireComponent extends AbstractComponent implements NotifiableComponent{
    
    protected Optional<GameObject> bullet;
    protected long currentTime;
    protected long lastTime;

    public AbstractFireComponent(GameObject entity, World world) {
        super(entity, world);   
    }

    @Override
    public <X> void receive(Message<X> message) {
        message.getMessage();
        //TODO 
    }

    @Override
    public void update() {
        if(canSpawn()) { //DEVO CHIAMARE UNO SPAWN EVENT

            //bullet = new GameObjectImpl(bullet, entity., dir);
            lastTime = System.currentTimeMillis();
            currentTime = System.currentTimeMillis();
        }
        if(world.getEntities().contains(bullet)) {
            bullet = Optional.empty();
            currentTime = System.currentTimeMillis() - lastTime;
        }
        
    }
    
    protected abstract boolean canSpawn();

}
