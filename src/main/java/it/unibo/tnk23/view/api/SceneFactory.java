package it.unibo.tnk23.view.api;

import javafx.scene.Parent;
import javafx.scene.Scene;

public interface SceneFactory {
    
    Scene getMenuScene();

    Scene getGameScene(Parent root);

    Scene getGameOverScene();
}
