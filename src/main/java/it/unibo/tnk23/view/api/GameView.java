package it.unibo.tnk23.view.api;

import it.unibo.tnk23.core.api.GameEngine;

public interface GameView {

    void renderView();

    void setMenuScene();

    void setGameScene();

    void setGameOverScene();

    GameEngine getGameEngine();

}
