package it.unibo.tnk23.view.impl;

import java.util.function.Consumer;

import it.unibo.tnk23.common.Configuration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

public class GameScene extends Scene {

    private BorderPane root;
    private double height;
    private double width;

    public GameScene(BorderPane root) {
        super(root);
        this.root = root;
        this.setDimention();
        SubScene playerInfoMenu = new SubScene(/*FXML */null, this.width, this.height);
        SubScene roundInfoMenu = new SubScene(/*FXML */null, this.width, this.height);
        this.getSetterPlayerInfoMenu().accept(playerInfoMenu);
        this.getSetterRoundInfoMenu().accept(roundInfoMenu);
    }
    
    private Consumer<Node> getSetterPlayerInfoMenu() {
        var dim = Screen.getPrimary().getBounds();
        return dim.getWidth() > dim.getHeight() ? root::setLeft : root::setBottom;
    }
    
    private Consumer<Node> getSetterRoundInfoMenu() {
        var dim = Screen.getPrimary().getBounds();
        return dim.getWidth() > dim.getHeight() ? root::setRight : root::setTop;
    }

    private void setDimention() {
        var dim = Screen.getPrimary().getBounds();
        if (dim.getHeight() > dim.getWidth()) {
            this.height = (dim.getHeight() - Configuration.GAME_SCENE_DIMENSION) / 2;
            this.width = dim.getWidth();
        } else {
            this.height = dim.getHeight();
            this.width = (dim.getWidth()- Configuration.GAME_SCENE_DIMENSION) / 2;
        }
    }
    
}
