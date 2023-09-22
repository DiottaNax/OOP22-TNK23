package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.common.Stopwatch;
import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.events.api.GameEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

/**
 * The TemporaryHealthComponent class represents a component that tracks the remaining lifetime
 * of a visual-decoration (game object with temporary health) such as an explosion or a visual-cooldown effect.
 * When the lifetime reaches zero, the decoration dies and the death event is reported to the game-world.
 */
public class TemporaryHealthComponent extends AbstractComponent {

    private final long lifeTime;
    private final Stopwatch stopwatch;

    /**
     * Constructs a TemporaryHealthComponent with the specified game object, game world, and lifetime.
     *
     * @param entity    the game object associated with this component
     * @param world     the game world in which the component operates
     * @param lifeTime  the lifetime of the game object in milliseconds
     */
    public TemporaryHealthComponent(final GameObject entity, final World world, final long lifeTime) {
        super(entity, world);
        this.lifeTime = lifeTime;
        this.stopwatch = new Stopwatch();
        stopwatch.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (this.stopwatch.getElapsedTime() >= lifeTime) {
            this.getWorld().notifyEvent(
                    new WorldEventImpl(this.getEntity().getPosition(), this.getEntity(), GameEventType.DEATH_EVENT));
        }
    }
}
