package it.unibo.tnk23.view.impl;

import java.io.IOException;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


/**
 * The TitleMenuController class is responsible for handling the title menu scene and related actions.
 * It provides functionality to start the game or navigate to the color picker scene.
 */
public class TitleMenuController {

    @FXML
    private final Button startButton = new Button();

    @FXML
    private final Button colorButton = new Button();

    private final FxGameView view;


    /**
     * Constructs a new TitleMenuController object with the specified FxGameView.
     * 
     * @param view The FxGameView object to associate with the controller.
     */
    @SuppressFBWarnings(
        value = {
            "EI2"
        },
            justification = "The TitleMenuController must store these parameters in order to use its methods."
    )
    public TitleMenuController(final FxGameView view) {
        this.view = view;
    }

    /**
     * Handles the action event when the start button is clicked.
     * Switches to the game scene to start the game.
     * 
     * @throws IOException if an I/O error occurs while loading the game scene.
     */
    @FXML
    public void startGame() throws IOException {
        this.view.setGameScene();
    }

    /**
     * Handles the action event when the button for the color choice is clicked.
     * Switches to the color picker scene to choose player colors.
     */
    @FXML
    public void goPickColor() {
        this.view.setColorPickerScene();
    }

}
