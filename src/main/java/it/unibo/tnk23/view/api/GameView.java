package it.unibo.tnk23.view.api;

import it.unibo.tnk23.core.api.GameEngine;
import it.unibo.tnk23.game.model.api.World;

public interface GameView {

    void renderView();

    void setMenuScene();

    void setGameScene(World world);

    void setGameOverScene();

    GameEngine getGameEngine();

}
