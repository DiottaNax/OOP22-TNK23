package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

/**
 * The TemporaryHealthComponent class represents a component that tracks the remaining lifetime of a visual-decoration (game object with temporary health) such as an explosion or a visual-cooldown effect.
 * When the lifetime reaches zero, the decoration dies and the death event is reported to the game-world.
 */
public class TemporaryHealthComponent extends AbstractComponent {

    private final int lifeTime;
    private int currentFrame;

    /**
     * Constructs a TemporaryHealthComponent with the specified game object, game world, and lifetime.
     *
     * @param entity    the game object associated with this component
     * @param world     the game world in which the component operates
     * @param lifeTime  the lifetime of the game object in frames
     */
    public TemporaryHealthComponent(GameObject entity, World world, int lifeTime) {
        super(entity, world);
        this.lifeTime = lifeTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (currentFrame >= lifeTime) {
            world.notifyEvent(new WorldEventImpl(this.entity.getPosition(), this.entity, WorldEventType.DEATH_EVENT));
        }
        else {
            currentFrame++;
        }
    }
}
