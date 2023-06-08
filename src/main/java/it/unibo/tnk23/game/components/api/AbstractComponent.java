package it.unibo.tnk23.game.components.api;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

/**
 * An abstract base class implementing the {@link Component} interface.
 * It provides common funciotnality and fields for components.
 */
public abstract class AbstractComponent implements Component {

    /**
     * The GameObject associated with this component.
     */
    private final GameObject entity;
    /**
     *  The game World in which the component exists.
     */
    private final World world;

    /**
     * Constructs a new {@link AbstractComponent} with the specified entity and world.
     * 
     * @param entity the GameObject associated with this component.
     * @param world the game World in which the component resides.
     */
    @SuppressFBWarnings(
        value = {
            "EI2"
        },
            justification = "The AbstractComponent must store these parameters in order to use its methods."
    )
    public AbstractComponent(final GameObject entity, final World world) {
        this.entity = entity;
        this.world = world;
    }

    /**
     * Retrieves the GameObject associated with this instance. 
     * 
     * @return The GameObject associated with this instance.
     */
    @SuppressFBWarnings(
        value = {
            "EI"
        },
            justification = "The AbstractComponent must return the original entitity and not a copy of it."
    )
    public GameObject getEntity() {
        return this.entity;
    }

    /**
     * Retrieves the World instance associated with this instance.
     *
     * @return The World instance associated with this instance.
     */
    @SuppressFBWarnings(
        value = {
            "EI"
        },
            justification = "The AbstractComponent must return the original world and not a copy of it."
    )
    public World getWorld() {
        return this.world;
    }

}
