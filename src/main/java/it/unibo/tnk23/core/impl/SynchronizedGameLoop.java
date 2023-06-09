package it.unibo.tnk23.core.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.core.api.GameLoop;
import it.unibo.tnk23.core.api.GameLoopDecorator;

/**
 * The {@code SynchronizedGameLoop} class extends the {@link GameLoopImpl} class and provides a synchronized game loop 
 * implementation.
 * It ensures that the game updates and rendering occur at a fixed frame rate defined by the configuration.
 * 
 * @author Federico Diotallevi
 */
public class SynchronizedGameLoop extends GameLoopDecorator {

    private static final long UPDATE_PERIOD = Math.round(1000.0 / Configuration.FPS);
    private long lag;
    private long currentTime;
    private long lastUpdateTime;

    /**
     * Constructs a {@code SynchronizedGameLoop} instance with the given game engine.
     *
     * @param engine the game engine associated with the game loop.
     */
    public SynchronizedGameLoop(final GameLoop toDecorate) {
        super(toDecorate);
        this.lastUpdateTime = System.currentTimeMillis();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.currentTime = System.currentTimeMillis();
        this.lag = this.lag + this.currentTime - this.lastUpdateTime;
        this.lastUpdateTime = this.currentTime;

        while (lag >= UPDATE_PERIOD) {
            super.update();
            this.lag = this.lag - UPDATE_PERIOD;
        }
    }

    /**
     * Waits for the remaining time until the next frame based on the current time.
     *
     * @param currentTime the current time in milliseconds.
     */
    private void waitForNextFrame(final long currentTime) {
        final long remainingTime = System.currentTimeMillis() - currentTime;
        if (remainingTime < UPDATE_PERIOD) {
            try {
                Thread.sleep(UPDATE_PERIOD - remainingTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        super.render();

        this.waitForNextFrame(currentTime);
    }

}
