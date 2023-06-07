package it.unibo.tnk23.view.api;

import java.io.IOException;

import it.unibo.tnk23.view.impl.FxGameView;
import it.unibo.tnk23.view.impl.PlayerInfoControllerImpl;
import it.unibo.tnk23.view.impl.RoundInfoControllerImpl;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public interface SceneFactory {

    /**
     * Creates the menu scene.
     *
     * @param view The game view.
     * @return The created menu scene.
     * @throws IOException If an I/O error occurs while creating the scene.
     */
    Scene getMenuScene(FxGameView view) throws IOException;

    /**
     * Creates the color picker scene.
     *
     * @param view The game view.
     * @return The created color picker scene.
     * @throws IOException If an I/O error occurs while creating the scene.
     */
    Scene getColorPickerScene(FxGameView view) throws IOException;

    /**
     * Creates the game scene.
     *
     * @param gamePane         The pane for the game view.
     * @param playerController The player info controller.
     * @param roundController  The round info controller.
     * @return The created game scene.
     * @throws IOException If an I/O error occurs while creating the scene.
     */
    Scene getGameScene(Pane gamePane, PlayerInfoControllerImpl playerController,
            RoundInfoControllerImpl roundController) throws IOException;

    /**
     * Creates the game over scene.
     *
     * @param view The game view.
     * @return The created game over scene.
     * @throws IOException If an I/O error occurs while creating the scene.
     */
    Scene getGameOverScene(FxGameView view) throws IOException;
}

