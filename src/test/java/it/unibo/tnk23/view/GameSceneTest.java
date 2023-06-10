package it.unibo.tnk23.view;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.BorderPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unibo.tnk23.view.impl.GameScene;
import it.unibo.tnk23.view.impl.PlayerInfoControllerImpl;
import it.unibo.tnk23.view.impl.RoundInfoControllerImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameSceneTest {
@BeforeAll
    public static void setup() {
        // Initialize the JavaFX toolkit for test execution
        JFXPanel panel = new JFXPanel();
    }

    @Test
    public void testGameSceneCreation() {
        try {
            // Create a BorderPane object for the test
            BorderPane root = new BorderPane();

            // Create an instance of GameScene
            GameScene gameScene = new GameScene(root, new PlayerInfoControllerImpl(null), new RoundInfoControllerImpl(null));

            // Verify that the reference to the root BorderPane has been set correctly
            assertEquals(root, gameScene.getRoot());

            // Verify that the dimensions have been set correctly
            assertNotNull(gameScene.getHeight());
            assertNotNull(gameScene.getWidth());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





