package it.unibo.tnk23.view;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.BorderPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.logging.Logger;
import it.unibo.tnk23.view.impl.GameScene;
import it.unibo.tnk23.view.impl.PlayerInfoControllerImpl;
import it.unibo.tnk23.view.impl.RoundInfoControllerImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.logging.Level;


/**
 * The GameSceneTest class is responsible for testing the functionality of the GameScene class.
 */
class GameSceneTest {

    private static final Logger LOGGER = Logger.getLogger("GameMapImplLogger");
    /**
     * Initialize the JavaFX toolkit for test execution.
     */
    @BeforeAll
    static void setup() {
        new JFXPanel();
    }

    /**
     * Test case for GameScene creation.
     * It verifies that the GameScene object is created correctly and the necessary properties are set.
     */
    @Test
    void testGameSceneCreation() {
        try {
            // Create a BorderPane object for the test
            final BorderPane root = new BorderPane();

            // Create an instance of GameScene
            final GameScene gameScene = new GameScene(root, new PlayerInfoControllerImpl(null),
                    new RoundInfoControllerImpl(null));

            // Verify that the reference to the root BorderPane has been set correctly
            assertEquals(root, gameScene.getRoot());

            // Verify that the dimensions have been set correctly
            assertNotNull(gameScene.getHeight());
            assertNotNull(gameScene.getWidth());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading or closing the map file", e);
        }
    }
}
