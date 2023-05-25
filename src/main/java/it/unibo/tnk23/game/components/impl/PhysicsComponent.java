package it.unibo.tnk23.game.components.impl;


import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.world.api.World;

public class PhysicsComponent extends AbstractComponent implements NotifiableComponent  {

    private double speed;

    public PhysicsComponent(GameObject entity, World world) {
        super(entity, world);
        var type = entity.getType();
        var speed = (Configuration.TILE_SIZE / Configuration.FPS) * type.getSpeed();
    }

    @Override
    public void update() {
        var pos = entity.getPosition();
        
        if (pos.getX() <= 0
                || pos.getX() >= Configuration.GAME_SCENE_DIMENSION - this.entity.getType().getWidth()
                || pos.getY() <= 0 
                || pos.getY() >= Configuration.GAME_SCENE_DIMENSION - this.entity.getType().getHeight()) {
            this.entity.setDirection(null);
        }
        entity.setPosition(entity.getPosition().sum(entity.getDirection().getVel().mul(this.speed)));
    }
    
    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(final double speed) {
        this.speed = speed;
    }
   
    @Override
    public <X> void receive(Message<X> x) {
        if (x instanceof GameObject) {
            if(!entity.getDirection().equals(Directions.NONE)) {
                entity.setPosition(entity.getPosition().sum(entity.getDirection().getVel().mul(-this.speed)));
            }
        }
    }
}
