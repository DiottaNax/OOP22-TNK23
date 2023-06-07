package it.unibo.tnk23.view.impl;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.impl.GraphicComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.game.model.impl.GameMapImpl;
import it.unibo.tnk23.game.model.impl.GameObjectFactoryImpl;
import it.unibo.tnk23.game.model.impl.WorldImpl;
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

    private List<GameObject> players = new LinkedList<>();
    private GameView view;
    private World world = new WorldImpl(new GameMapImpl(ClassLoader.getSystemResourceAsStream("it/unibo/maps/map1.txt")));

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
    private int multiplayer;


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
        this.setPlayerSprite();
        view.setMenuScene();
    }

    public void setPlayerSprite() {
        String spriteOne = myColorPlayerOne.toLowerCase() + "Player";
        players.add(new GameObjectFactoryImpl(this.world).getPlayer(new Point2D(7 * Configuration.TILE_SIZE, Configuration.TILE_SIZE * (Configuration.GRID_SIZE - 1))));
        players.get(0).addComponent(new GraphicComponent(players.get(0), spriteOne));
        
        if (multiplayer > 0) {
            String spriteTwo = myColorPlayerTwo.toLowerCase() + "Player";
            players.add(new GameObjectFactoryImpl(this.world).getPlayer(new Point2D(12 * Configuration.TILE_SIZE, Configuration.TILE_SIZE * (Configuration.GRID_SIZE - 1))));
            players.get(1).addComponent(new GraphicComponent(players.get(1), spriteTwo));
        }
        
        this.players.forEach(this.world::addPlayer);
        view.setWorld(this.world);
    }
    
}
