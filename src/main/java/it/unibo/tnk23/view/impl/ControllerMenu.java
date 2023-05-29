package it.unibo.tnk23.view.impl;

import it.unibo.tnk23.view.api.GameView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerMenu extends Scene {

    private Button startButton = new Button();
    private Button colorButton = new Button();
    private FxGameView view;

    public ControllerMenu(FxGameView view) {
        super(new AnchorPane());
        this.view = view;
    }

    private void startGame() {
        view.setGameScene();
    }

    private void goPickColor() {
        this.view.setScene(new ColorPickerMenu(view));   
    }

}
