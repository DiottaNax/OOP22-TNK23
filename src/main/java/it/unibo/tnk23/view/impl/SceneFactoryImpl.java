package it.unibo.tnk23.view.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.view.api.SceneFactory;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class SceneFactoryImpl implements SceneFactory{

    @Override
    public Scene getMenuScene() {
        return new Scene(null);
    }

    @Override
    public Scene getGameScene(Pane gamePane) {
        return new GameScene(new BorderPane(
                new SubScene(gamePane, Configuration.GAME_SCENE_DIMENSION, Configuration.GAME_SCENE_DIMENSION)));
    }

    @Override
    public Scene getGameOverScene() {
        return new Scene(null);
    }

    
}
