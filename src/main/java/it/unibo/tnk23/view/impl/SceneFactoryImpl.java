package it.unibo.tnk23.view.impl;

import java.io.IOException;
import java.util.logging.Logger;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.view.api.GameView;
import it.unibo.tnk23.view.api.SceneFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class SceneFactoryImpl implements SceneFactory{

    @Override
    public Scene getMenuScene(FxGameView view) {
        try {
            var loader = new FXMLLoader(ClassLoader.getSystemResource("it/unibo/style/titlemenu.fxml"));
            loader.setController(new TitleMenuController(view));
            return new Scene(loader.load());
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Scene getGameScene(Pane gamePane, GameView gameView) {
        return new Scene(
                new BorderPane(
                        new SubScene(gamePane, Configuration.GAME_SCENE_DIMENSION, Configuration.GAME_SCENE_DIMENSION)));
    }

    @Override
    public Scene getGameOverScene() {
        return new Scene(null);
    }

    
}
