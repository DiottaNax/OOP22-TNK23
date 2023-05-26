package it.unibo.tnk23.core.api;

import it.unibo.tnk23.game.model.api.GameState;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.view.api.GameView;

public interface GameEngine {
    
    World getWorld();

    GameState getGameState();

    void startEngine();

    GameView getGameView();
}
