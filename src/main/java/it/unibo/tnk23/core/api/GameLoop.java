package it.unibo.tnk23.core.api;

import it.unibo.tnk23.game.events.api.WorldEvent;
import it.unibo.tnk23.game.events.api.WorldEventListener;

public interface GameLoop extends WorldEventListener{

    void processInput();

    void update();

    void render();

    GameEngine getGameEngine();

}
