package it.unibo.tnk23.game.events.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
    @SuppressFBWarnings(value = { "EI2" }, justification =
    "WorldEventHandlerImpl must store the original world because we have to handle the events of that world."
    )
    public WorldEventHandlerImpl(final World world) {
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final WorldEvent we) {
        final WorldEventType type = we.getType();
        final var actor = we.getEventActor();
        switch (type) {
            case DEATH_EVENT:
                /*
                 * Remove the entity from the world.
                 */
                this.world.removeEntity(actor);
                break;

            case SHOOT_EVENT:
                /*
                 * Create a bullet entity and add it to the world.
                 */
                final var pos = actor.getPosition();
                /*
                 * I just use getwidth because the shooter is square.
                 */
                final var actorEdge = actor.getType().getWidth() * Configuration.SCALE_FACTOR;
                var bulletPos = pos;
                /*
                 * I need just a bit more than the size of the tile size.
                 */
                final double rateCalculationBulletPos =  0.6;
                bulletPos = bulletPos.sum(Directions.fromAngle((int) actor.getRotation()).getVel()
                        .mul(actorEdge * rateCalculationBulletPos));
                final var bullet = new GameObjectFactoryImpl(this.world).getBullet(bulletPos);
                bullet.setPower(actor.getPower());
                bullet.setDirection(Directions.fromAngle((int) actor.getRotation()));
                bullet.notifyComponents(we::getEventActor, BulletComponent.class);
                this.world.addEntity(bullet);
                break;

            case SPAWN_EVENT:
                /*
                 * Set the actor's position and add it to the world
                 */
                actor.setPosition(we.getPosition());
                this.world.addEntity(actor);
                break;

            default:
                /*
                 * No action needed for the other event types.
                 */
                break;
        }
    }

}
