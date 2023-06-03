package it.unibo.tnk23.game.events.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.game.components.impl.BulletComponent;
import it.unibo.tnk23.game.events.api.WorldEvent;
import it.unibo.tnk23.game.events.api.WorldEventHandler;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.game.model.impl.GameObjectFactoryImpl;

public class WorldEventHandlerImpl implements WorldEventHandler {

    private final World world;

    public WorldEventHandlerImpl(final World world) {
        this.world = world;
    }

    @Override
    public void handle(WorldEvent we) {
        WorldEventType type = we.getType();
        switch (type) {
            case DEATH_EVENT:
                world.getEntities().remove(we.getEventActor());
                break;

            case SHOOT_EVENT:
                var actor = we.getEventActor();
                var pos = actor.getPosition();
                var actorEdge = actor.getType().getWidth() * Configuration.SCALE_FACTOR; /*mi basta usare getwidth perchè chi spara è quadrato*/
                var bulletPos = pos;
                bulletPos = bulletPos.sum(Directions.fromAngle((int) actor.getRotation()).getVel().mul(actorEdge));
                var bullet = new GameObjectFactoryImpl(world).getBullet(bulletPos);
                bullet.setPower(actor.getPower());
                bullet.setDirection(Directions.fromAngle((int) actor.getRotation()));
                bullet.notifyComponents(we::getEventActor, BulletComponent.class);
                world.getEntities().add(bullet);
                break;

            case SPAWN_EVENT:
                world.getEntities().add(we.getEventActor());
                break;

            default:
                break;
        }
    }
    
}
