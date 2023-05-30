package it.unibo.tnk23.view.api;

import java.util.List;

import it.unibo.tnk23.core.api.GameEngine;
import it.unibo.tnk23.game.model.api.GameMap;
import it.unibo.tnk23.game.model.api.GameObject;

public interface GameView {

    void renderView();

    void setMenuScene();

    void setGameScene(final List<GameObject> players, final GameMap gameMap);

    void setGameOverScene();

    GameEngine getGameEngine();

}
