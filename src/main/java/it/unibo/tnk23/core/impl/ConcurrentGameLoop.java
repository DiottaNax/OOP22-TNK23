package it.unibo.tnk23.core.impl;

import it.unibo.tnk23.core.api.GameEngine;
import it.unibo.tnk23.core.api.GameLoop;
import it.unibo.tnk23.core.api.GameLoopDecorator;

public class ConcurrentGameLoop extends GameLoopDecorator {
    private Thread gameLoopThread;

    public ConcurrentGameLoop(GameLoop toDecorate) {
        super(toDecorate);
        gameLoopThread = new Thread(new GameLoopThread());
    }

    public void runGameLoop() {
        this.gameLoopThread.start();
    }
    
    private class GameLoopThread implements Runnable{
        private GameEngine gameEngine;

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
