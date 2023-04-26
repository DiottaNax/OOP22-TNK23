package it.unibo.tnk23.core.api;

import it.unibo.tnk23.model.api.GameState;
import it.unibo.tnk23.model.api.World;

public interface GameEngine {
    
    World getWorld();

    GameState getGameState();

    void startEngine();
}
