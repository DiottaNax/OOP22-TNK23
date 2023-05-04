package it.unibo.tnk23.core.api;

public interface GameLoop {

    void processInput();

    void update();

    void render();

    GameEngine getGameEngine();

}
