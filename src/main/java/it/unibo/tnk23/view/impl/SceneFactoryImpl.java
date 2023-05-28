package it.unibo.tnk23.view.impl;

import it.unibo.tnk23.view.api.SceneFactory;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class SceneFactoryImpl implements SceneFactory{

    @Override
    public Scene getMenuScene() {
        return new Scene(null);
    }

    @Override
    public Scene getGameScene(Pane gamePane) {
        return new GameScene(new BorderPane(gamePane));
    }

    @Override
    public Scene getGameOverScene() {
        return new Scene(null);
    }

    
}
