package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.game.model.api.GameObject;

public class GraphicComponent implements Component {

    private String spritename;

    public GraphicComponent(GameObject entity, String spriteName) {
        this.spritename = spriteName;
    }

    @Override
    public void update() {
    }

    public String getSpriteName() {
        return this.spritename;
    }

    public void setSpriteName( String spriteName) {
        this.spritename = spriteName;
    }

}

