package it.unibo.tnk23.view.impl;

import java.io.IOException;
import java.util.function.Consumer;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.common.Configuration;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

/**
 * The GameScene class represents a custom scene for the game interface.
 * It extends the JavaFX Scene class and provides additional functionality for displaying player and round information.
 */
public class GameScene extends Scene {

    private final BorderPane root;
    private double height;
    private double width;

    /**
     * Constructs a GameScene object with the specified parameters.
     * 
     * @param root The root BorderPane of the scene.
     * @param playerController The implementation of the PlayerInfoController for displaying player information.
     * @param roundController The implementation of the RoundInfoController for displaying round information.
     * @throws IOException If an error occurs during the loading of FXML files.
     */
    @SuppressFBWarnings(
        value = {
            "EI2"
        }, 
            justification = "GameScene must store the root on which it should base to set the side scenes."
    )
    public GameScene(final BorderPane root, final PlayerInfoControllerImpl playerController,
            final RoundInfoControllerImpl roundController) throws IOException {

        super(root);
        this.root = root;
        AnchorPane playerInfoRoot;
        AnchorPane roundInfoRoot;
        final var loader1 = new FXMLLoader();
        loader1.setController(playerController);
        final var loader2 = new FXMLLoader();
        loader2.setController(roundController);
        playerInfoRoot = loader1.load(ClassLoader.getSystemResourceAsStream("it/unibo/style/playerInfo.fxml"));
        roundInfoRoot = loader2.load(ClassLoader.getSystemResourceAsStream("it/unibo/style/roundInfo.fxml"));
        this.setDimension();
        final var playerInfoMenu = new SubScene(playerInfoRoot, this.width, this.height);
        final var roundInfoMenu = new SubScene(roundInfoRoot, this.width, this.height);
        this.getSetterPlayerInfoMenu().accept(playerInfoMenu);
        this.getSetterRoundInfoMenu().accept(roundInfoMenu);
    }

    private Consumer<Node> getSetterPlayerInfoMenu() {
        final var dim = Screen.getPrimary().getBounds();
        return dim.getWidth() > dim.getHeight() ? root::setLeft : root::setBottom;
    }

    private Consumer<Node> getSetterRoundInfoMenu() {
        final var dim = Screen.getPrimary().getBounds();
        return dim.getWidth() > dim.getHeight() ? root::setRight : root::setTop;
    }

    private void setDimension() {
        final var dim = Screen.getPrimary().getBounds();
        if (dim.getHeight() > dim.getWidth()) {
            this.height = (dim.getHeight() - Configuration.GAME_SCENE_DIMENSION) / 2;
            this.width = dim.getWidth();
        } else {
            this.height = dim.getHeight();
            this.width = (dim.getWidth() - Configuration.GAME_SCENE_DIMENSION) / 2;
        }
    }

}
