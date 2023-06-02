package it.unibo.tnk23.view.impl;

import java.util.LinkedList;
import java.util.List;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.model.api.GameMap;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.TypeObject;
import it.unibo.tnk23.game.model.impl.GameMapImpl;
import it.unibo.tnk23.game.model.impl.GameObjectImpl;
import it.unibo.tnk23.game.model.impl.TypeObjectFactory;
import it.unibo.tnk23.game.model.impl.WorldImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class TitleMenu extends Scene {

    @FXML
    private Button startButton = new Button();

    @FXML
    private Button colorButton = new Button();

    private FxGameView view;

    private List<GameObject> players = new LinkedList<>();
    private GameMap map = new GameMapImpl(ClassLoader.getSystemResourceAsStream("it/unibo/maps/map1.txt"));


    public TitleMenu(FxGameView view, Parent root) {
        super(root);
        this.view = view;
        //this.getStylesheets().addAll(this.getClass().getResource("it/unibo/style/background.css").toExternalForm());
        players.add(new GameObjectImpl(TypeObjectFactory.getPlayerType(), new Point2D(getX(), getY())));
    }

    @FXML
    private void startGame() {
        var world = new WorldImpl(this.map);
        this.players.forEach(world::addPlayer);
        view.setGameScene(world);
    }

    @FXML
    private void goPickColor() {
        this.view.setScene(new ColorPickerMenu(view));
    }

    @FXML
    void initialize() {
        assert colorButton != null : "fx:id=\"colorButton\" was not injected: check your FXML file 'titlemenu.fxml'.";
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'titlemenu.fxml'.";

    }

}
