package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.game.model.api.GameObject;

public class GraphicComponent implements Component {

    private String spritename;
    private double width;
    private double height;

    public GraphicComponent(GameObject entity, String spriteName) {
        this.spritename = spriteName;
        this.width = entity.getType().getWidth() * Configuration.SCALE_FACTOR;
        this.height = entity.getType().getHeight() * Configuration.SCALE_FACTOR;
    }

    @Override
    public void update() {
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setheight(double height) {
        this.height = height;
    }

    public String getSpriteName() {
        return this.spritename;
    }

    public void setSpriteName( String spriteName) {
        this.spritename = spriteName;
    }

}

