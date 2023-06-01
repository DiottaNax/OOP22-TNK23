package it.unibo.tnk23.view.api;

import java.io.IOException;

import it.unibo.tnk23.view.impl.FxGameView;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public interface SceneFactory {
    
    Scene getMenuScene(FxGameView view) throws IOException;

    Scene getGameScene(Pane gamePane, GameView gameView);

    Scene getGameOverScene();
}
