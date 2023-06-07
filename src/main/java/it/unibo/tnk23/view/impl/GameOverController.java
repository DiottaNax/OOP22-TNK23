package it.unibo.tnk23.view.impl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class GameOverController {

    @FXML
    private Button restartButton = new Button();

    @FXML
    private Button exiButton = new Button();

    private FxGameView view;


    public GameOverController(FxGameView view) {
        this.view = view;
    }

    @FXML
    private void restartGame() {
        this.view.setMenuScene();
    }
    
    @FXML
    private void exit() {     
        System.exit(0);
    }

}
