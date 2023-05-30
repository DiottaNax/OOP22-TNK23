package it.unibo.tnk23.view.impl;

import java.net.URL;
import java.util.ResourceBundle;

import it.unibo.tnk23.view.api.GameView;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ColorPickerMenu extends Scene implements Initializable {

    private GameView view;

    private Label label;
    private Button confirmButton;
    private ChoiceBox<String> choiceBoxPlayerOne;
    private ChoiceBox<String> choiceBoxPlayerTwo;
    
    private String[] colors = { "Pink", "Red", "Orange", "Yellow", "Green", "Turquoise", "Blue", "Purple" };
    private String myColorPlayerOne = "Pink";
    private String myColorPlayerTwo = "Turquoise";   


    public ColorPickerMenu(GameView view) {
        super(new AnchorPane());
        this.view = view;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBoxPlayerOne.getItems().addAll(colors);
        choiceBoxPlayerOne.setOnAction(this::getColor);
        choiceBoxPlayerTwo.getItems().addAll(colors);
        choiceBoxPlayerTwo.setOnAction(this::getColor);
    }

    public void getColor(ActionEvent event) {
        myColorPlayerOne = choiceBoxPlayerOne.getValue();
        myColorPlayerTwo = choiceBoxPlayerTwo.getValue();
    }

    public void confirm(ActionEvent event) {
        String url = "resources/it/unibo/sprites/" + myColorPlayerOne + "Player.gif";
        view.setMenuScene();
    }
    
}
