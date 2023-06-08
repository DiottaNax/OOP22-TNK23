package it.unibo.tnk23.view.impl;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
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

 /**
 * It's the controller of the scene that offers to set the multiplayer mode
 * and allows players to choose their tank-colors,
 * confirm their selection, and then automatically sets up the player sprites in the game.
 */
public class ColorPickerController extends Scene implements Initializable {

    final private List<GameObject> players = new LinkedList<>();
    final private GameView view;
    final private World world = new WorldImpl(new GameMapImpl(ClassLoader.getSystemResourceAsStream("it/unibo/maps/map1.txt")));

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
    final private String[] colors = { "Pink", "Red", "Orange", "Yellow", "Green", "Cyan", "Blue", "Purple" };
    private String myColorPlayerOne = "Pink";
    private String myColorPlayerTwo = "Cyan";
    private int multiplayer;


    /**
     * Constructs a new ColorPickerController object with the specified GameView.
     * 
     * @param view The GameView object to associate with the controller.
     */
    public ColorPickerController(final FxGameView view) {
        super(new AnchorPane());
        this.view = view;
        multiplayer = 0;
    }

    /**
     * Initializes the ColorPickerController's choice box after its root element has been processed.
     * 
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(final ObservableValue<? extends Number> observable,
                    final Number oldValue, final Number newValue) {
                multiplayer = (int) slider.getValue();
                if (multiplayer > 0) {
                    labelTwo.setDisable(false);
                    choiceBoxPlayerTwo.setDisable(false);
                } else {
                    labelTwo.setDisable(true);
                    choiceBoxPlayerTwo.setDisable(true);
                }
            }
        });

        choiceBoxPlayerOne.getItems().addAll(colors);
        choiceBoxPlayerOne.setOnAction(this::setColor);

        choiceBoxPlayerTwo.getItems().addAll(colors);
        choiceBoxPlayerTwo.setOnAction(this::setColor);
    }

    /**
     * Handles the action event when the color is selected from the choice box.
     * 
     * @param event The ActionEvent representing the color selection event.
     */
    public void setColor(final ActionEvent event) {
        myColorPlayerOne = choiceBoxPlayerOne.getValue();
        myColorPlayerTwo = choiceBoxPlayerTwo.getValue();
    }

    /**
     * Handles the action event when the confirm button is clicked.
     * Sets up the player sprites and switches back to the Title-Menu Scene.
     * 
     * @param event The ActionEvent representing the confirm button click event.
     */
    public void confirm(final ActionEvent event) {
        this.setPlayerSprite();
        view.setMenuScene();
    }

    /**
     * Sets up the player sprites based on the selected colors and adds them to the game world.
     */
    public void setPlayerSprite() {
        final String spriteOne = myColorPlayerOne.toLowerCase(Locale.ENGLISH) + "Player";
        final int num1 = 7;
        players.add(new GameObjectFactoryImpl(this.world).getPlayer(
                new Point2D(num1 * Configuration.TILE_SIZE, Configuration.TILE_SIZE * (Configuration.GRID_SIZE - 1))));
        players.get(0).addComponent(new GraphicComponent(spriteOne));
        if (multiplayer > 0) {
            final int num2 = 12;
            String spriteTwo = myColorPlayerTwo.toLowerCase(Locale.ENGLISH) + "Player";
            players.add(new GameObjectFactoryImpl(this.world).getPlayer(new Point2D(num2 * Configuration.TILE_SIZE,
                    Configuration.TILE_SIZE * (Configuration.GRID_SIZE - 1))));
            players.get(1).addComponent(new GraphicComponent(spriteTwo));
        }
        this.players.forEach(this.world::addPlayer);
        view.setWorld(this.world);
    }
}
