package it.unibo.tnk23.view.api;

import it.unibo.tnk23.view.impl.FxGameView;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public interface SceneFactory {
    
    Scene getMenuScene(FxGameView view);

    Scene getGameScene(Pane gamePane, GameView gameView);

    Scene getGameOverScene();
}
