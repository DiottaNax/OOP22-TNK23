package it.unibo.tnk23.core.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.core.api.GameEngine;

public class SynchronizedGameLoop extends GameLoopImpl {

    private final long UPDATE_PERIOD;
    private long lag;
    private long currentTime;
    private long lastUpdateTime;
    
    public SynchronizedGameLoop(final GameEngine engine) {
        super(engine);
        UPDATE_PERIOD = Math.round(1000 / Configuration.FPS);
        this.lastUpdateTime = System.currentTimeMillis();
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

    public void waitForNextFrame(final long currentTime) {
        long remainingTime = System.currentTimeMillis() - currentTime;
        if (remainingTime < UPDATE_PERIOD) {
            try {
                Thread.sleep(UPDATE_PERIOD - remainingTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void render() {
        super.render();

        this.waitForNextFrame(currentTime);
    }
    
}
