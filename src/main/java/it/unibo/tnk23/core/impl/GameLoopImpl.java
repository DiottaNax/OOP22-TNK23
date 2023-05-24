package it.unibo.tnk23.core.impl;

import java.util.LinkedList;
import java.util.Queue;

import it.unibo.tnk23.core.api.GameEngine;
import it.unibo.tnk23.core.api.GameLoop;
import it.unibo.tnk23.game.events.api.WorldEvent;
import it.unibo.tnk23.game.events.api.WorldEventHandler;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.world.api.World;

public class GameLoopImpl implements GameLoop {
    private final GameEngine engine;
    private final World wrld;
    private final WorldEventHandler eventHandler;
    private Queue<WorldEvent> eventQueue = new LinkedList<>();
    
    public GameLoopImpl(final GameEngine engine) {
        this.engine = engine;
        this.wrld = engine.getWorld();
        this.eventHandler = null;
    }

    @Override
    public GameEngine getGameEngine() {
        return this.engine;
    }

    @Override
    public void processInput() {
        this.eventQueue.forEach(eventHandler::handle);
        this.eventQueue.clear();
    }

    @Override
    public void update() {
        this.engine.getGameState().update();
        this.wrld.getEntities().stream().parallel().forEach(GameObject::update);
    }

    @Override
    public void render() {
        this.engine.getGameView().renderView();
    }

    @Override
    public void notifyEvent(WorldEvent e) {
        this.eventQueue.add(e);
    }
    
}
