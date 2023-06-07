package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

/**
 * This class represents a health component specifically for bullets. It extends the
 * AbstractHealthComponent class and provides the implementation for the isTouchable method.
 */
public class BulletHealthComponent extends AbstractHealthComponent {

    private boolean isTouchable;

    /**
     * Constructs a BulletHealthComponent using the specified parameters GameObject and World.
     *
     * @param entity the GameObject associated with this component
     * @param world  the World object in which the component exists
     */
    public BulletHealthComponent(GameObject entity, World world) {
        super(entity, world);
        isTouchable = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isTouchable() {
        return isTouchable;
    }

}

