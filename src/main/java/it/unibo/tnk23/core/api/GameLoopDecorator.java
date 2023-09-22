package it.unibo.tnk23.core.api;

import it.unibo.tnk23.game.events.api.GameEvent;

/**
 * The {@code GameLoopDecorator} class is an abstract class that implements the {@link GameLoop} interface.
 * It serves as a base class for implementing decorators for the game loop.
 * Decorators can extend this class to add additional behavior or modify the behavior of the decorated game loop.
 */
public abstract class GameLoopDecorator implements GameLoop {
    /**
     * The {@link GameLoop} to decorate.
     */
    private final GameLoop toDecorate;

    /**
     * Constructs a new {@code GameLoopDecorator} instance with the specified game loop to decorate.
     *
     * @param toDecorate the game loop instance to decorate
     */
    public GameLoopDecorator(final GameLoop toDecorate) {
        this.toDecorate = toDecorate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processInput() {
        this.toDecorate.processInput();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.toDecorate.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.toDecorate.render();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final GameEngine getGameEngine() {
        return toDecorate.getGameEngine();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void notifyEvent(final GameEvent e) {
        toDecorate.notifyEvent(e);
    }

}
