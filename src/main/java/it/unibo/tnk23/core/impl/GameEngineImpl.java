package it.unibo.tnk23.core.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.core.api.GameEngine;
import it.unibo.tnk23.game.model.api.GameState;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.game.model.impl.GameStateImpl;
import it.unibo.tnk23.view.api.GameView;

/**
 * A simple implementation of the GameEngine interface.
 */
public class GameEngineImpl implements GameEngine {

    private final World world;
    private final GameState state;
    private final ConcurrentGameLoop loop;
    private final GameView view;

    /**
     * Constructs a GameEngineImpl with the specified world and game view.
     *
     * @param world the game world
     * @param view the game view
     */
    @SuppressFBWarnings(
        value = {
            "EI2"
        },
        justification = "The GameEngine must store the original World and GameView to run and update TNK23."
    )
    public GameEngineImpl(final World world, final GameView view) {
        this.world = world;
        this.state = new GameStateImpl(world);
        this.loop = new ConcurrentGameLoop(new SynchronizedGameLoop(new GameLoopImpl(this)));
        this.world.setWorldEventListener(loop);
        this.view = view;
    }

    /**
     * Retrieves the game world associated with the game engine.
     *
     * @return the game world
     */
    @SuppressFBWarnings(
        value = {
            "EI"
        },
        justification = "The world returned is supposed to reflect changes made by other classes."
    )
    @Override
    public World getWorld() {
        return this.world;
    }

    /**
     * Retrieves the game state representing the current state of the game.
     *
     * @return the game state
     */
    @Override
    public GameState getGameState() {
        return this.state;
    }

    /**
     * Starts the game engine by running the game loop and initiating the first round.
     */
    @Override
    public void startEngine() {
        this.loop.runGameLoop();
        this.state.getRound().startRound();
    }

    /**
     * Retrieves the game view associated with the game engine.
     *
     * @return the game view
     */
    @SuppressFBWarnings(
        value = {
            "EI"
        },
        justification = "The GameEngine must return the original GameView in order to make other classes change scenes."
    )
    @Override
    public GameView getGameView() {
        return view;
    }
}
