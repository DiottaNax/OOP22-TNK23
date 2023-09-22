package it.unibo.tnk23.core.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import it.unibo.tnk23.core.api.GameEngine;
import it.unibo.tnk23.core.api.GameLoop;
import it.unibo.tnk23.game.events.api.GameEvent;
import it.unibo.tnk23.game.events.api.GameEventHandler;
import it.unibo.tnk23.game.events.impl.WorldEventHandlerImpl;
import it.unibo.tnk23.game.model.api.World;

/**
 * The {@code GameLoopImpl} class implements the {@link GameLoop} interface to provide the main game loop functionality for TNK23.
 * It updates the game state, renders the game view, and handles world events.
 * 
 * @author Federico Diotallevi
 */
public class GameLoopImpl implements GameLoop {
    private final GameEngine engine;
    private final World wrld;
    private final GameEventHandler eventHandler;
    private final List<GameEvent> eventQueue = Collections.synchronizedList(new LinkedList<>());

    /**
     * Constructs a {@code GameLoopImpl} instance with the given game engine.
     * Initializes the world, event handler, and event queue.
     *
     * @param engine the game engine associated with the game loop.
     */
    public GameLoopImpl(final GameEngine engine) {
        this.engine = engine;
        this.wrld = engine.getWorld();
        this.eventHandler = new WorldEventHandlerImpl(wrld);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameEngine getGameEngine() {
        return this.engine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processInput() {
        final var toProcess = new HashSet<>(eventQueue);
        toProcess.forEach(eventHandler::handle);
        this.eventQueue.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.engine.getGameState().update();
        this.wrld.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.engine.getGameView().renderView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvent(final GameEvent e) {
        this.eventQueue.add(e);
        this.engine.getGameView().notifyEvent(e);
    }

}
