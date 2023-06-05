package it.unibo.tnk23.view.impl;

import java.net.URL;
import java.util.ResourceBundle;

import it.unibo.tnk23.view.api.GameView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;

public class ColorPickerController extends Scene implements Initializable {

    private GameView view;

    @FXML
    private Label labelOne;

    @FXML
    private Label labelTwo;

    @FXML
    private Label labelMultiplayer;

    @FXML
    private Button confirmButton;

    @FXML
    private ChoiceBox<String> choiceBoxPlayerOne;

    @FXML
    private ChoiceBox<String> choiceBoxPlayerTwo;

    @FXML
    private ToolBar toolBar;

    @FXML
    private Slider slider;
    
    private String[] colors = { "Pink", "Red", "Orange", "Yellow", "Green", "Cyan", "Blue", "Purple" };
    private String myColorPlayerOne = "Pink";
    private String myColorPlayerTwo = "Cyan";
    int multiplayer; 


    public ColorPickerController(GameView view) {
        super(new AnchorPane());
        this.view = view;
        multiplayer = 0;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                multiplayer = (int) slider.getValue();
                if (multiplayer > 0) {
                    labelTwo.setDisable(false);
                    choiceBoxPlayerTwo.setDisable(false);
                }
                else {
                    labelTwo.setDisable(true);
                    choiceBoxPlayerTwo.setDisable(true);
                }
            }
        });

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
        String urlOne = "resources/it/unibo/sprites/" + myColorPlayerOne + "Player.gif";
        String urlTwo = "resources/it/unibo/sprites/" + myColorPlayerTwo + "Player.gif";
        view.setMenuScene();
    }
    
}
