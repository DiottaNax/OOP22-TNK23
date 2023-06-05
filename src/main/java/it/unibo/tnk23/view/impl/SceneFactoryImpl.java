package it.unibo.tnk23.view.impl;

import java.io.IOException;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.view.api.SceneFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SubScene;
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
    public Scene getGameScene(Pane gamePane, PlayerInfoControllerImpl playerController,
            RoundInfoControllerImpl roundController) throws IOException {

        return new GameScene(
                new BorderPane(
                        new SubScene(gamePane, Configuration.GAME_SCENE_DIMENSION, Configuration.GAME_SCENE_DIMENSION)),
                playerController, roundController);
    }

    @Override
    public Scene getGameOverScene() {
        return new Scene(null);
    }

    
}
