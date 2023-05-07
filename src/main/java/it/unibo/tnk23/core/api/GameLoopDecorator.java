package it.unibo.tnk23.core.api;

import it.unibo.tnk23.game.events.api.WorldEvent;
import it.unibo.tnk23.game.model.api.GameObject;

public abstract class GameLoopDecorator implements GameLoop{
    protected final GameLoop toDecorate;

    public GameLoopDecorator(final GameLoop toDecorate) {
        this.toDecorate = toDecorate;
    }

    @Override
    public void processInput() {
        this.toDecorate.processInput();
    }

    @Override
    public void update() {
        this.toDecorate.update();
    }

    @Override
    public void render() {
        this.toDecorate.render();
    }

    @Override
    public GameEngine getGameEngine() {
        return toDecorate.getGameEngine();
    }

    @Override
    public void notifyEvent(WorldEvent e) {
        toDecorate.notifyEvent(e);
    }

}
