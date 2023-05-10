package it.unibo.tnk23.core.api;

import it.unibo.tnk23.game.model.api.GameState;
import it.unibo.tnk23.game.world.api.World;

public interface GameEngine {
    
    World getWorld();

    GameState getGameState();

    void startEngine();
}
