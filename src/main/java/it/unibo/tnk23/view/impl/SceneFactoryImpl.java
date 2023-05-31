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
        Parent root;
        try {
            root = new FXMLLoader().load(ClassLoader.getSystemResourceAsStream("it/unibo/style/titlemenu.fxml"));
        } catch (IOException e) {
            root = new AnchorPane();
            var label = new Label(e.getMessage());
            label.setScaleX(0);
            ((AnchorPane) root).getChildren().add(label);
        }
        return new TitleMenu(view, root);
    }

    @Override
    public Scene getGameScene(Pane gamePane, GameView gameView) {
        return new GameScene(
                new BorderPane(
                        new SubScene(gamePane, Configuration.GAME_SCENE_DIMENSION, Configuration.GAME_SCENE_DIMENSION)),
                gameView);
    }

    @Override
    public Scene getGameOverScene() {
        return new Scene(null);
    }

    
}
