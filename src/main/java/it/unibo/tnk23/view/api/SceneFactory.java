package it.unibo.tnk23.view.api;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public interface SceneFactory {
    
    Scene getMenuScene();

    Scene getGameScene(Pane gamePane, GameView gameView);

    Scene getGameOverScene();
}
