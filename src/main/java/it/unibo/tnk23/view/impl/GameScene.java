package it.unibo.tnk23.view.impl;

import java.io.IOException;
import java.util.function.Consumer;

import it.unibo.tnk23.common.Configuration;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

public class GameScene extends Scene{

    private BorderPane root;
    private double height;
    private double width;

    public GameScene(final BorderPane root, final PlayerInfoControllerImpl playerController,
            final RoundInfoControllerImpl roundController) throws IOException {
        
        super(root);
        this.root = root;
        AnchorPane playerInfoRoot;
        AnchorPane roundInfoRoot;
        var loader1 = new FXMLLoader();
        loader1.setController(playerController);
        var loader2 = new FXMLLoader();
        loader2.setController(roundController);
        playerInfoRoot = loader1.load(ClassLoader.getSystemResourceAsStream("it/unibo/style/playerInfo.fxml"));
        roundInfoRoot = loader2.load(ClassLoader.getSystemResourceAsStream("it/unibo/style/roundInfo.fxml"));
        this.setDimension();
        var playerInfoMenu = new SubScene(playerInfoRoot, this.width, this.height);
        var roundInfoMenu = new SubScene(roundInfoRoot, this.width, this.height);
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

    private void setDimension() {
        var dim = Screen.getPrimary().getBounds();
        if (dim.getHeight() > dim.getWidth()) {
            this.height = (dim.getHeight() - Configuration.GAME_SCENE_DIMENSION) / 2;
            this.width = dim.getWidth();
        } else {
            this.height = dim.getHeight();
            this.width = (dim.getWidth() - Configuration.GAME_SCENE_DIMENSION) / 2;
        }
    }
    
}
