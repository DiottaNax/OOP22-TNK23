package it.unibo.tnk23.core.impl;

import it.unibo.tnk23.core.api.GameEngine;
import it.unibo.tnk23.game.model.api.GameState;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.game.model.impl.GameStateImpl;
import it.unibo.tnk23.view.api.GameView;

public class GameEngineImpl implements GameEngine {

    private final World world;
    private final GameState state;
    private final ConcurrentGameLoop loop;
    private final GameView view;

    public GameEngineImpl(final World world, final GameView view){
        this.world = world;
        this.state = new GameStateImpl(world);
        this.loop = new ConcurrentGameLoop(new SynchronizedGameLoop(this));
        this.view = view;
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

    @Override
    public GameView getGameView() {
        return view;
    }
    
}
