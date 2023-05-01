package it.unibo.tnk23.core.api;

public abstract class GameLoopDecorator implements GameLoop{
    protected final GameLoop toDecorate;

    public GameLoopDecorator(GameLoop toDecorate) {
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

}
