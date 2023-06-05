package it.unibo.tnk23.view.impl;

import java.util.LinkedList;
import java.util.List;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import it.unibo.tnk23.game.model.api.GameMap;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.impl.GameMapImpl;
import it.unibo.tnk23.game.model.impl.WorldImpl;


public class TitleMenuController {

    @FXML
    private Button startButton = new Button();

    @FXML
    private Button colorButton = new Button();

    private FxGameView view;


    public TitleMenuController(FxGameView view) {
        this.view = view;
    }

    @FXML
    private void startGame() throws IOException {     
        this.view.setGameScene();
    }

    @FXML
    private void goPickColor() {
        this.view.setColorPickerScene();
    }

    @FXML
    void initialize() {
        assert colorButton != null : "fx:id=\"colorButton\" was not injected: check your FXML file 'titlemenu.fxml'.";
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'titlemenu.fxml'.";

    }

}
