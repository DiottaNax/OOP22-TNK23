package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.common.shape.Rect2D;
import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.components.api.Bonus;
import it.unibo.tnk23.game.events.api.GameEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

public class BonusComponent extends AbstractComponent {

    private final Bonus bonus;
    private final Rect2D hitbox;

    public BonusComponent(final GameObject entity, final World world, final Bonus bonus) {
        super(entity, world);
        this.bonus = bonus;
        this.hitbox = new Rect2D(entity.getType().getHeight(), entity.getPosition());
    }

    private void performAndDie(final GameObject o) {
        bonus.perform(o);
        this.getWorld().notifyEvent(new WorldEventImpl(o.getPosition(), this.getEntity(), GameEventType.DEATH_EVENT));
    }

    @Override
    public void update() {
        this.getWorld().getPlayers().stream().filter(p -> p.getComponent(CollisionComponent.class).isPresent())
                .map(p -> p.getComponent(CollisionComponent.class).get()).filter(c -> c.isCollidingWith(hitbox))
                .findAny().ifPresent(c -> performAndDie(c.getEntity()));
    }
}
