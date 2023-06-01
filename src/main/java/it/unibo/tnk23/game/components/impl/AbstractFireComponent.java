package it.unibo.tnk23.game.components.impl;

import java.util.Optional;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.impl.GameObjectFactoryImpl;
import it.unibo.tnk23.game.model.api.World;

public abstract class AbstractFireComponent extends AbstractComponent{
    
    protected Optional<GameObject> lastBullet = Optional.empty();

    public AbstractFireComponent(final GameObject entity, final World world) {
        super(entity, world);
    }

    @Override
    public void update() {
        if (canShoot()) {
            var pos = entity.getPosition();
            var dir = entity.getDirection();
            var edge = (double) entity.getType().getWidth(); /*mi basta usare getwidth perchè chi spara è quadrato*/
            var bulletPos = new Point2D(pos.getX() + edge / 2, pos.getY() + edge / 2);
            bulletPos = bulletPos.sum(dir.getVel().mul(edge * 2));
            lastBullet = Optional.of(new GameObjectFactoryImpl(world).getBullet(bulletPos));
            lastBullet.get().setPower(entity.getPower());
            lastBullet.get().setDirection(entity.getDirection());
            world.notifyEvent(new WorldEventImpl(entity.getPosition(), lastBullet.get(), WorldEventType.SHOOT_EVENT));
        }
        if(lastBullet.isPresent() && !world.getEntities().contains(lastBullet.get())) {
            lastBullet = Optional.empty();
        }  
    }
    
    protected abstract boolean canShoot();

}
