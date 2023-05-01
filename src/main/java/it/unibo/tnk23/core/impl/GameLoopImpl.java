package it.unibo.tnk23.core.impl;

import it.unibo.tnk23.core.api.GameEngine;
import it.unibo.tnk23.core.api.GameLoop;
import it.unibo.tnk23.model.api.GameObject;
import it.unibo.tnk23.model.api.World;

public class GameLoopImpl implements GameLoop {
    private GameEngine engine;
    private World wrld;
    
    public GameLoopImpl(GameEngine engine) {
        this.engine = engine;
        this.wrld = engine.getWorld();
    }

    @Override
    public GameEngine getGameEngine() {
        return this.engine;
    }

    @Override
    public void processInput() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processInput'");
    }

    @Override
    public void update() {
        this.wrld.getEntities().stream().parallel().forEach(GameObject::update);
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }
    
}
