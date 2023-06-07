package it.unibo.tnk23.core.impl;

import it.unibo.tnk23.core.api.GameEngine;
import it.unibo.tnk23.core.api.GameLoop;
import it.unibo.tnk23.core.api.GameLoopDecorator;

/**
 * The {@code ConcurrentGameLoop} class is a decorator for the {@link GameLoop} interface
 * that runs the game loop in a separate thread.
 */
public class ConcurrentGameLoop extends GameLoopDecorator {
    private final Thread gameLoopThread;

    /**
     * Constructs a {@code ConcurrentGameLoop} instance with the specified game loop to decorate.
     *
     * @param toDecorate the game loop to decorate.
     */
    public ConcurrentGameLoop(final GameLoop toDecorate) {
        super(toDecorate);
        gameLoopThread = new Thread(new GameLoopThread());
    }

    /**
     * Starts running the game loop in a separate thread.
     */
    public void runGameLoop() {
        this.gameLoopThread.start();
    }
    
    /**
     * The {@code GameLoopThread} class is responsible for running the game loop in a separate thread.
     * It implements the {@link Runnable} interface and performs the game loop logic, including processing input,
     * updating the game state, and rendering the game view.
     */
    private class GameLoopThread implements Runnable{
        private final GameEngine gameEngine;

        /**
         * Constructs a new {@code GameLoopThread} instance with the specified game engine.
         *
         * @param gameEngine the game engine to use for updating and rendering the game state
         */
        public GameLoopThread() {
            this.gameEngine = getGameEngine();
        }
        
        /**
         * Runs the game loop logic in a continuous loop until the game is over.
         * It calls the processInput, update, and render methods of the game loop's decorated object.
         * Once the game is over, it ends the loop.
         */
        @Override
        public void run() {
            while (!gameEngine.getGameState().isGameOver()) {
                processInput();

                update();

                render();
            }
        }
    }
}
