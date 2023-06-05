package it.unibo.tnk23.view.api;

import java.io.IOException;

import it.unibo.tnk23.core.api.GameEngine;
import it.unibo.tnk23.game.model.api.World;

public interface GameView {

    void renderView();

    void setMenuScene();

    void setColorPickerScene();

    void setGameScene();

    void setGameOverScene();

    GameEngine getGameEngine();

    void setWorld(World world);

}
