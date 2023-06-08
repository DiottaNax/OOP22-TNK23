package it.unibo.tnk23.view.impl;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


/**
 * The TitleMenuController class is responsible for handling the title menu scene and related actions.
 * It provides functionality to start the game or navigate to the color picker scene.
 */
public class TitleMenuController {

    @FXML
    private Button startButton = new Button();

    @FXML
    private Button colorButton = new Button();

    private FxGameView view;


    /**
     * Constructs a new TitleMenuController object with the specified FxGameView.
     * 
     * @param view The FxGameView object to associate with the controller.
     */
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
    private void startGame() throws IOException {
        this.view.setGameScene();
    }

    /**
     * Handles the action event when the button for the color choice is clicked.
     * Switches to the color picker scene to choose player colors.
     */
    @FXML
    private void goPickColor() {
        this.view.setColorPickerScene();
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     * It performs assertions to check if the FXML-injected fields are not null.
     */
    @FXML
    void initialize() {
        assert colorButton != null : "fx:id=\"colorButton\" was not injected: check your FXML file 'titlemenu.fxml'.";
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'titlemenu.fxml'.";
    }

}
