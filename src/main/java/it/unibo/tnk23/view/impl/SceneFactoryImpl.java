package it.unibo.tnk23.view.impl;

import java.io.IOException;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.view.api.SceneFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Scale;

/**
 * The {@code SceneFactoryImpl} class implements the {@link SceneFactory} interface and provides methods
 * for creating different scenes used in the game.
 */
public class SceneFactoryImpl implements SceneFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene getMenuScene(final FxGameView view) throws IOException {
        final var loader = new FXMLLoader(ClassLoader.getSystemResource("it/unibo/style/titlemenu.fxml"));
        loader.setController(new TitleMenuController(view));
        final Parent root = loader.load();
        final double size = (Configuration.GAME_SCENE_DIMENSION * 0.5) / root.prefHeight(0);
        root.getTransforms().add(new Scale(size, size));
        return new Scene(root, Configuration.GAME_SCENE_DIMENSION * 0.5, Configuration.GAME_SCENE_DIMENSION * 0.5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene getColorPickerScene(final FxGameView view) throws IOException {
        final var loader = new FXMLLoader(ClassLoader.getSystemResource("it/unibo/style/colorpicker.fxml"));
        loader.setController(new ColorPickerController(view));
        final Parent root = loader.load();
        final double size = (Configuration.GAME_SCENE_DIMENSION * 0.5) / root.prefHeight(0);
        root.getTransforms().add(new Scale(size, size));
        return new Scene(root, Configuration.GAME_SCENE_DIMENSION * 0.5, Configuration.GAME_SCENE_DIMENSION * 0.5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene getGameScene(final Parent gamePane, final PlayerInfoControllerImpl playerController,
            final RoundInfoControllerImpl roundController) throws IOException {

        final var root = new BorderPane(new SubScene(gamePane, Configuration.GAME_SCENE_DIMENSION,
                Configuration.GAME_SCENE_DIMENSION));
        root.setStyle("-fx-background-color: #000000;");
        return new GameScene(root, playerController, roundController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene getGameOverScene(final FxGameView view) throws IOException {
        final var loader = new FXMLLoader(ClassLoader.getSystemResource("it/unibo/style/gameover.fxml"));
        loader.setController(new GameOverController(view));
        final Parent root = loader.load();
        final double size = (Configuration.GAME_SCENE_DIMENSION * 0.5) / root.prefHeight(0);
        root.getTransforms().add(new Scale(size, size));
        return new Scene(root, Configuration.GAME_SCENE_DIMENSION * 0.5, Configuration.GAME_SCENE_DIMENSION * 0.5);
    }

}
