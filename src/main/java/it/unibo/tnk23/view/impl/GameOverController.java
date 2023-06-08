package it.unibo.tnk23.view.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


/**
 * The GameOverController class is responsible for handling the game over screen and related actions.
 * It provides functionality to restart the game or exit the application.
 */
public class GameOverController {

    @FXML
    private final Button restartButton = new Button();

    @FXML
    private final Button exitButton = new Button();

    private final FxGameView view;


    /**
     * Constructs a new GameOverController object with the specified FxGameView.
     * 
     * @param view The FxGameView object to associate with the controller.
     */
    @SuppressFBWarnings(
        value = {
            "EI2"
        },
            justification = "The GameOverController must store this parameter in order to use its methods."
    )
    public GameOverController(final FxGameView view) {
        this.view = view;
    }

    /**
     * Handles the action event when the restart button is clicked.
     * Sets a new world-object and then switches to the menu scene to restart the game.
     */
    @FXML
    public void restartGame() {
        this.view.setMenuScene();
    }

    /**
     * Handles the action event when the exit button is clicked.
     * Exits the application by terminating the JVM.
     */
    @FXML
    public void exit() { 
        System.exit(0);
    }

}
