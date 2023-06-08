package it.unibo.tnk23.game.events.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.game.components.impl.BulletComponent;
import it.unibo.tnk23.game.events.api.WorldEvent;
import it.unibo.tnk23.game.events.api.WorldEventHandler;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.game.model.impl.GameObjectFactoryImpl;

/**
 * Implementation of the {@link WorldEventHandler} interface that handles various events.
 * and performs corresponding actions in the world
 */
public class WorldEventHandlerImpl implements WorldEventHandler {

    private final World world;

    /**
     * Constructs a new {@link WorldEventHandlerImpl} with the specified world.
     * 
     * @param world the {@link World} istance to handle events in.
     */
    public WorldEventHandlerImpl(final World world) {
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final WorldEvent we) {
        WorldEventType type = we.getType();
        var actor = we.getEventActor();
        switch (type) {
            case DEATH_EVENT:
                /*
                 * Remove the entity from the world.
                 */
                world.removeEntity(actor);
                break;

            case SHOOT_EVENT:
                /*
                 * Create a bullet entity and add it to the world.
                 */
                var pos = actor.getPosition();
                /*
                 * I just use getwidth because the shooter is square.
                 */
                var actorEdge = actor.getType().getWidth() * Configuration.SCALE_FACTOR;
                var bulletPos = pos;
                /*
                 * I need just a bit more than the size of the tile size.
                 */
                final double rateCalculationBulletPos =  0.6;
                bulletPos = bulletPos.sum(Directions.fromAngle((int) actor.getRotation()).getVel()
                        .mul(actorEdge * rateCalculationBulletPos));
                var bullet = new GameObjectFactoryImpl(world).getBullet(bulletPos);
                bullet.setPower(actor.getPower());
                bullet.setDirection(Directions.fromAngle((int) actor.getRotation()));
                bullet.notifyComponents(we::getEventActor, BulletComponent.class);
                world.addEntity(bullet);
                break;

            case SPAWN_EVENT:
                /*
                 * Set the actor's position and add it to the world
                 */
                actor.setPosition(we.getPosition());
                world.addEntity(actor);
                break;

            default:
                /*
                 * No action needed for the other event types.
                 */
                break;
        }
    }

}
