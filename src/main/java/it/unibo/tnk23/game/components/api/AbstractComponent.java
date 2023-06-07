package it.unibo.tnk23.game.components.api;

import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

/**
 * An abstract base class implementing the {@link Component} interface.
 * It provides common funciotnality and fields for components.
 */
public abstract class AbstractComponent implements Component {
    
    /**
     * The GameObject associated with this component
     */
    protected GameObject entity;
    /**
     *  The game World in which the component exists.
     */
    protected World world;
    
    /**
     * Constructs a new {@link AbstractComponent} with the specified entity and world
     * 
     * @param entity the GameObject associated with this component.
     * @param world the game World in which the component resides.
     */
    public AbstractComponent(final GameObject entity, final World world) {
        this.entity = entity;
        this.world = world;
    }
}
