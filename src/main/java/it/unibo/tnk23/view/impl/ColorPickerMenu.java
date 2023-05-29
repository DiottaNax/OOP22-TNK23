package it.unibo.tnk23.view.impl;

import java.net.URL;
import java.util.ResourceBundle;

import it.unibo.tnk23.view.api.GameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ColorPickerMenu extends Scene implements Initializable {

    private GameView view;

    private SceneFactoryImpl sceneFactory;

    private Label label;
    private Button confirmButton;
    private ChoiceBox<String> choiceBox;
    
    private String[] colors = { "Pink", "Red", "Orange", "Yellow", "Green", "Turquoise", "Blue", "Purple" };
    private String myColor;    


    public ColorPickerMenu(GameView view) {
        super(new AnchorPane());
        this.view = view;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll(colors);
        choiceBox.setOnAction(this::getColor);
    }

    public void getColor(ActionEvent event) {
        myColor = choiceBox.getValue();
    }

    public void confirm(ActionEvent event) {
        LateralScenesControllerImpl sceneController = new LateralScenesControllerImpl();
        String url = "resources/sprites/" + myColor + "Player.gif";
        sceneController.setPlayerColor(url);
        view.setMenuScene();
    }
    
}
