package it.unibo.tnk23.view.impl;

import it.unibo.tnk23.view.api.GameView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ControllerMenu extends Scene {

    Button startButton;
    private SceneFactoryImpl sceneFactory = new SceneFactoryImpl();
    private Scene scene;
    private GameView view;

    public ControllerMenu(GameView view) {
        super(new AnchorPane());
        this.scene = sceneFactory.getMenuScene();
        this.view = view;
        this.startButton.setOnAction(e -> view.setGameScene());
    }

}
