package it.unibo.tnk23.game.components.impl;

import java.util.Optional;

import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.world.api.World;

public abstract class AbstractFireComponent extends AbstractComponent implements NotifiableComponent{
    
    protected Optional<GameObject> bullet;

    public AbstractFireComponent(GameObject entity, World world) {
        super(entity, world);   
    }

    @Override
    public <X> void receive(Message<X> x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'receive'");
    }

    @Override
    public void update() {
        if(canSpawn(null)) {

        }
    }
    
    protected abstract <X> boolean canSpawn(X consumer);

}
