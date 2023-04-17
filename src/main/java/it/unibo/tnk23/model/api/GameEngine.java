package it.unibo.tnk23.model.api;

public interface GameEngine {
    
    World getWorld();

    GameState getGameState();

    void startEngine();
}
