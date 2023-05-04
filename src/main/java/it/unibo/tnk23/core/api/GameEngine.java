package it.unibo.tnk23.core.api;

import it.unibo.tnk23.model.world.api.World;
import it.unibo.tnk23.model.api.GameState;

public interface GameEngine {
    
    World getWorld();

    GameState getGameState();

    void startEngine();
}
