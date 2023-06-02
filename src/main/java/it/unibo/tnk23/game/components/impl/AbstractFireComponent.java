package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.impl.GameObjectFactoryImpl;
import it.unibo.tnk23.game.model.impl.TypeObjectFactory;
import it.unibo.tnk23.game.model.api.World;

public abstract class AbstractFireComponent extends AbstractComponent{
    
    protected int currentFarme = 0;

    public AbstractFireComponent(final GameObject entity, final World world) {
        super(entity, world);
    }

    @Override
    public void update() {
        if (canShoot()) {
            currentFarme = 0;
            var pos = entity.getPosition();
            var edge = (double) entity.getType().getWidth(); /*mi basta usare getwidth perchè chi spara è quadrato*/
            var bulletEdge = TypeObjectFactory.getBulletType().getWidth() * Configuration.SCALE_FACTOR;
            var bulletPos = new Point2D(pos.getX() + edge / 2 - bulletEdge, pos.getY() + edge / 2 - bulletEdge);
            bulletPos = bulletPos.sum(Directions.fromAngle((int) entity.getRotation()).getVel().mul(edge * 1.15));
            var bullet = new GameObjectFactoryImpl(world).getBullet(bulletPos);
            bullet.setPower(entity.getPower());
            bullet.setDirection(Directions.fromAngle((int) entity.getRotation()));
            world.notifyEvent(new WorldEventImpl(entity.getPosition(), bullet, WorldEventType.SPAWN_EVENT));
        } 
    }
    
    protected abstract boolean canShoot();

}
