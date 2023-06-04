package it.unibo.tnk23.view.api;

import java.io.IOException;

import it.unibo.tnk23.view.impl.FxGameView;
import it.unibo.tnk23.view.impl.PlayerInfoControllerImpl;
import it.unibo.tnk23.view.impl.RoundInfoControllerImpl;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public interface SceneFactory {
    
    Scene getMenuScene(FxGameView view) throws IOException;

    Scene getGameScene(Pane gamePane, PlayerInfoControllerImpl playerController, RoundInfoControllerImpl roundController) throws IOException;

    Scene getGameOverScene();
}
