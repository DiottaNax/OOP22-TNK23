package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.game.model.impl.TypeObjectFactory;

/**
 * Represents a physics component for game entities.
 * Handles the movement and collision detection for entities.
 */
public class PhysicsComponent extends AbstractComponent implements NotifiableComponent {

    private double speed;

    /**
     * Constructs a PhysicsComponent for the given entity and world.
     *
     * @param entity the entity to which this component belongs
     * @param world the game world
     */
    public PhysicsComponent(GameObject entity, World world) {
        super(entity, world);
        var type = entity.getType();
        this.speed = ((double) Configuration.TILE_SIZE / Configuration.FPS) * type.getSpeed();
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void update() {
        var nextPos = entity.getPosition().sum(entity.getDirection().getVel().mul(this.speed));

        if (nextPos.getX() >= Configuration.DISPLACEMENT
                && nextPos.getX() <= Configuration.GAME_SCENE_DIMENSION - this.entity.getType().getWidth() - Configuration.DISPLACEMENT
                && nextPos.getY() >= Configuration.DISPLACEMENT
                && nextPos.getY() <= Configuration.GAME_SCENE_DIMENSION - this.entity.getType().getHeight() - Configuration.DISPLACEMENT) {

            this.entity.setPosition(nextPos);
        } else if (TypeObjectFactory.isBullet(entity.getType())) {
            this.world.notifyEvent(new WorldEventImpl(entity.getPosition(), entity, WorldEventType.DEATH_EVENT));
        }
        var rotation = entity.getDirection().getVel().getX() * (90) + entity.getDirection().getVel().getY() * 180;
        if (rotation != 0) {
            this.entity.setRotation(rotation != -180 ? rotation : 0);
        }
    }

    /**
     * Gets the speed of the entity.
     *
     * @return the speed value
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Sets the speed of the entity.
     *
     * @param speed the speed value to set
     */
    public void setSpeed(final double speed) {
        this.speed = speed;
    }

    /**
     {@inheritDoc}
     */
    @Override
    public <X> void receive(Message<X> x) {
        if (x.getMessage() instanceof GameObject) {
            if (!entity.getDirection().equals(Directions.NONE)) {
                entity.setPosition(entity.getPosition().sum(entity.getDirection().getVel().mul(-this.speed)));
            }
        }
    }
}
