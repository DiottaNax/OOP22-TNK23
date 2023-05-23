package it.unibo.tnk23.core.impl;

import it.unibo.tnk23.core.api.GameEngine;
import it.unibo.tnk23.game.model.api.GameState;
import it.unibo.tnk23.game.world.api.World;

public class GameEngineImpl implements GameEngine {

    private final World world;
    private final GameState state;
    private final ConcurrentGameLoop loop;

    public GameEngineImpl(){
        this.world = null;
        this.state = null;
        this.loop = new ConcurrentGameLoop(new GameLoopImpl(this));
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public GameState getGameState() {
        return this.state;
    }

    @Override
    public void startEngine() {
        this.loop.runGameLoop();
    }
    
}
