package it.unibo.tnk23.view.impl;

import java.io.IOException;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.core.api.GameEngine;
import it.unibo.tnk23.core.impl.GameEngineImpl;
import it.unibo.tnk23.game.components.impl.GraphicComponent;
import it.unibo.tnk23.game.components.impl.InputComponent;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.game.model.impl.GameMapImpl;
import it.unibo.tnk23.game.model.impl.GameObjectFactoryImpl;
import it.unibo.tnk23.game.model.impl.WorldImpl;
import it.unibo.tnk23.input.impl.KeyEventHandler;
import it.unibo.tnk23.input.impl.PlayerOneKeyboardController;
import it.unibo.tnk23.input.impl.PlayerTwoKeyboardController;
import it.unibo.tnk23.view.api.GameView;
import it.unibo.tnk23.view.api.SceneFactory;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The JavaFX implementation of the {@link GameView} interface.
 * It provides the functionality for rendering the game view and managing different scenes in the game.
 */
public final class FxGameView implements GameView {

    private final Stage stage;
    private final SceneFactory sceneFactory;

    private GameEngine gameEngine;
    private FxRenderingEngine renderingEngine;
    private PlayerInfoControllerImpl playerController;
    private RoundInfoControllerImpl roundController;
    private World world;

    /**
     * Constructs a new instance of {@link FxGameView}.
     *
     * @param stage the JavaFX stage for the game view
     */
    @SuppressFBWarnings(
        value = {
            "EI2"
        },
            justification = "The FxGameView must store this parameter in order to use its methods."
    )
    public FxGameView(final Stage stage) {
        this.stage = stage;
        this.sceneFactory = new SceneFactoryImpl();

        this.stage.setOnCloseRequest(e -> this.exitGame());

        this.setDefaultWorld();
        this.stage.setResizable(false);

        this.setMenuScene();
        this.stage.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderView() {
        if (gameEngine.getGameState().isGameOver()) {
            Platform.runLater(this::setGameOverScene);
        } else {
            Platform.runLater(() -> {
                renderingEngine.render();
                playerController.updateGraphics();
                roundController.updateGraphics();
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameScene() {
        final var keyEventHandler = new KeyEventHandler();
        final var inputControllerOne = new PlayerOneKeyboardController();
        keyEventHandler.addInputController(inputControllerOne);

        // Setting key event handler to the stage.
        this.stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEventHandler::onKeyPressed);
        this.stage.addEventHandler(KeyEvent.KEY_RELEASED, keyEventHandler::onKeyReleased);

        // Setting players' keyboard input controllers.
        world.getPlayer(1).ifPresent(p -> p.addComponent(new InputComponent(p, inputControllerOne)));
        world.getPlayer(2).ifPresent(p -> {
            final var inputControllerTwo = new PlayerTwoKeyboardController();
            keyEventHandler.addInputController(inputControllerTwo);
            p.addComponent(new InputComponent(p, inputControllerTwo));
        });

        // Setting and starting game engines.
        this.gameEngine = new GameEngineImpl(world, this);
        this.renderingEngine = new FxRenderingEngine(world);
        this.gameEngine.startEngine();
        this.playerController = new PlayerInfoControllerImpl(world);
        this.roundController = new RoundInfoControllerImpl(this.gameEngine.getGameState().getRound());

        try {
            this.stage.setScene(this.sceneFactory.getGameScene(this.renderingEngine.getGamePane(), playerController,
                    roundController));
        } catch (IOException e) {
            this.stage.setScene(new Scene(new BorderPane(this.renderingEngine.getGamePane())));
        }
        this.stage.setFullScreen(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMenuScene() {
        try {
            this.stage.setFullScreen(false);
            this.stage.setScene(this.sceneFactory.getMenuScene(this));
        } catch (IOException e) {
            this.setGameScene();
        }
    }

    /**
     * Sets the color picker scene.
     * The color picker scene should let the player decide his tank's color.
     */
    public void setColorPickerScene() {
        try {
            this.stage.setFullScreen(false);
            this.stage.setScene(this.sceneFactory.getColorPickerScene(this));
            this.stage.show();
            this.stage.sizeToScene();
        } catch (IOException e) {
            this.setMenuScene();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameOverScene() {
        this.setDefaultWorld();
        try {
            this.stage.setFullScreen(false);
            this.stage.setScene(this.sceneFactory.getGameOverScene(this));
            this.stage.show();
            this.stage.sizeToScene();
        } catch (IOException e) {
            this.exitGame();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameEngine getGameEngine() {
        return this.gameEngine;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(
        value = {
            "EI2"
        },
            justification = "The FxGameView must store this parameter in order to use its methods."
    )
    @Override
    public void setWorld(final World world) {
        this.world = world;
    }

    /**
     * Sets the JavaFX scene passed as the stage scene.
     *
     * @param scene the JavaFX scene to set
     */
    public void setScene(final Scene scene) {
        this.stage.setScene(scene);
    }

    private void exitGame() {
        Platform.exit();
        Runtime.getRuntime().exit(0);
    }

    /**
     * This function creates a default world if the users doesn't change settings.
     */
    private void setDefaultWorld() {
        this.world = new WorldImpl(new GameMapImpl(ClassLoader.getSystemResourceAsStream("it/unibo/maps/map1.txt")));
        final var player = new GameObjectFactoryImpl(world).getPlayer(
                new Point2D(7 * Configuration.TILE_SIZE, Configuration.TILE_SIZE * (Configuration.GRID_SIZE - 1)));
        player.addComponent(new GraphicComponent("pinkPlayer"));
        world.addPlayer(player);
    }
}

