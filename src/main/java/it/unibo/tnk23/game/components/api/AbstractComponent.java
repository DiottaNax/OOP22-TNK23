package it.unibo.tnk23.game.components.api;

import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.world.api.World;

public abstract class AbstractComponent implements Component {

    protected GameObject entity;
    protected World world;

    public AbstractComponent(GameObject entity, World world) {
        this.world = world;
        this.entity = entity;
    }
    
}
