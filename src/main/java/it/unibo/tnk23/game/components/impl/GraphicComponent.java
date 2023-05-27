package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.game.model.api.GameObject;

public class GraphicComponent implements Component {
    
    private String spritename;
    private double width;
    private double height;
    
    public GraphicComponent(GameObject entity, String spriteName) {
        this.spritename = spriteName;
        this.width = entity.getType().getWidth();
        this.height = entity.getType().getHeight();
    }
    
    @Override
    public void update() {
    }
}
