package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.components.api.Component;
/**
 * Represents a graphic component for game entities.
 * Handles the rendering and sprite management for entities.
 */
public class GraphicComponent implements Component {

    private String spritename;

    /**
     * Constructs a GraphicComponent for the given entity and sprite name.
     *
     * @param spriteName the name of the sprite associated with the entity
     */
    public GraphicComponent(String spriteName) {
        this.spritename = spriteName;
    }

    /**
     {@inheritDoc}
     */
    @Override
    public void update() {
    }

    /**
     * Gets the name of the sprite associated with the entity.
     *
     * @return the sprite name
     */
    public String getSpriteName() {
        return this.spritename;
    }

    /**
     * Sets the name of the sprite associated with the entity.
     *
     * @param spriteName the sprite name to set
     */
    public void setSpriteName(String spriteName) {
        this.spritename = spriteName;
    }

}
