package it.unibo.tnk23.core.impl;

import it.unibo.tnk23.core.api.GameEngine;

public class SynchronizedGameLoop extends GameLoopImpl {

    private final long MAX_FPS = 120;
    private final long UPDATE_PERIOD;
    private long lag = 0;
    private long currentTime;
    private long lastUpdateTime = System.currentTimeMillis();
    

    public SynchronizedGameLoop(GameEngine engine) {
        super(engine);
        UPDATE_PERIOD = Math.round(1000 / MAX_FPS);
    }

    @Override
    public void update() {
        this.currentTime = System.currentTimeMillis();
        this.lag = this.lag + this.currentTime - this.lastUpdateTime;
        this.lastUpdateTime = System.currentTimeMillis();

        while (lag >= UPDATE_PERIOD) {
            super.update();
            this.lag = this.lag - UPDATE_PERIOD;
        }
    }

    public void waitForNextFrame(long currentTime) {
        long remainingTime = System.currentTimeMillis() - currentTime;
        if (remainingTime < UPDATE_PERIOD) {
            try {
                Thread.sleep(UPDATE_PERIOD - remainingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void render() {
        super.render();

        this.waitForNextFrame(currentTime);
    }
    
}
