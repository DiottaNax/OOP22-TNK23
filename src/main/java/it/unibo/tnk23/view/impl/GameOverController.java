package it.unibo.tnk23.view.impl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


/**
 * The GameOverController class is responsible for handling the game over screen and related actions.
 * It provides functionality to restart the game or exit the application.
 */
public class GameOverController {

    @FXML
    private Button restartButton = new Button();

    @FXML
    private Button exiButton = new Button();

    private FxGameView view;


    /**
     * Constructs a new GameOverController object with the specified FxGameView.
     * 
     * @param view The FxGameView object to associate with the controller.
     */
    public GameOverController(FxGameView view) {
        this.view = view;
    }

    /**
     * Handles the action event when the restart button is clicked.
     * Sets a new world-object and then switches to the menu scene to restart the game.
     */
    @FXML
    private void restartGame() {
        this.view.setDefaultWorld();
        this.view.setMenuScene();
    }
    
    /**
     * Handles the action event when the exit button is clicked.
     * Exits the application by terminating the JVM.
     */
    @FXML
    private void exit() {     
        System.exit(0);
    }

}
