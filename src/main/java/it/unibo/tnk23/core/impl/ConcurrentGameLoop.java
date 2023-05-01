package it.unibo.tnk23.core.impl;

import it.unibo.tnk23.core.api.GameEngine;
import it.unibo.tnk23.core.api.GameLoop;
import it.unibo.tnk23.core.api.GameLoopDecorator;

public class ConcurrentGameLoop extends GameLoopDecorator {
    private final Thread gameLoopThread;

    public ConcurrentGameLoop(final GameLoop toDecorate) {
        super(toDecorate);
        gameLoopThread = new Thread(new GameLoopThread());
    }

    public void runGameLoop() {
        this.gameLoopThread.start();
    }
    
    private class GameLoopThread implements Runnable{
        private final GameEngine gameEngine;

        public GameLoopThread() {
            this.gameEngine = getGameEngine();
        }
        
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
