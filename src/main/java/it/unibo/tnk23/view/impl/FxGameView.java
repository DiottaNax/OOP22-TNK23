package it.unibo.tnk23.view.impl;

import java.io.IOException;

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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FxGameView implements GameView {

    private final Stage stage;
    private final SceneFactory sceneFactory;

    private GameEngine gameEngine;
    private FxRenderingEngine renderingEngine;
    private PlayerInfoControllerImpl playerController;
    private RoundInfoControllerImpl roundController;
    private World world;


    public FxGameView(Stage stage) {
        this.stage = stage;
        this.sceneFactory = new SceneFactoryImpl();

        this.stage.setOnCloseRequest(e -> this.exitGame());

        this.setDefaultWorld();

        this.setMenuScene();
        this.stage.show();
    }

    @Override
    public void renderView() {
        Platform.runLater(() -> {
            renderingEngine.render();
            playerController.updateLabels();
            roundController.updateLabels();
        });
    }

    @Override
    public void setGameScene() {
        var keyEventHandler = new KeyEventHandler();
        var inputControllerOne = new PlayerOneKeyboardController();
        keyEventHandler.addInputController(inputControllerOne);

        this.stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEventHandler::onKeyPressed);
        this.stage.addEventHandler(KeyEvent.KEY_RELEASED, keyEventHandler::onKeyReleased);
        this.stage.addEventHandler(KeyEvent.KEY_TYPED, e -> {
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                this.exitGame();
            }
        });

        world.getPlayer(1).ifPresent(p -> p.addComponent(new InputComponent(p, inputControllerOne)));
        world.getPlayer(2).ifPresent(p -> {
            var inputControllerTwo = new PlayerTwoKeyboardController();
            keyEventHandler.addInputController(inputControllerTwo);
            p.addComponent(new InputComponent(p, inputControllerTwo));
        });

        gameEngine = new GameEngineImpl(world, this);
        this.renderingEngine = new FxRenderingEngine(world, this);
        this.gameEngine.startEngine();
        this.playerController = new PlayerInfoControllerImpl(world);
        this.roundController = new RoundInfoControllerImpl(this.gameEngine.getGameState().getRound());

        try {
            this.stage.setScene(this.sceneFactory.getGameScene(this.renderingEngine.getGamePane(), playerController, roundController));
        } catch (IOException e) {
            this.stage.setScene(new Scene(new BorderPane(this.renderingEngine.getGamePane())));
        }
        this.stage.setFullScreen(true);
    }

    @Override
    public void setMenuScene() {
        try{
            this.stage.setFullScreen(false);
            this.stage.setScene(this.sceneFactory.getMenuScene(this));
            this.stage.show();
            this.stage.sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setColorPickerScene() {
        try{
            this.stage.setFullScreen(false);
            this.stage.setScene(this.sceneFactory.getColorPickerScene(this));
            this.stage.show();
            this.stage.sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setGameOverScene() {
        this.stage.setFullScreen(false);
        this.stage.setScene(this.sceneFactory.getGameOverScene());
        this.stage.sizeToScene();
    }

    @Override
    public GameEngine getGameEngine() {
        return this.gameEngine;
    }

    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    public void setScene(final Scene scene) {
        this.stage.setScene(scene);
    }

    private void exitGame() {
        Platform.exit();
        Runtime.getRuntime().exit(0);
    }

    private void setDefaultWorld() {
        this.world = new WorldImpl(new GameMapImpl(ClassLoader.getSystemResourceAsStream("it/unibo/maps/map1.txt")));
        var player = new GameObjectFactoryImpl(world).getPlayer(new Point2D(7 * Configuration.TILE_SIZE, Configuration.TILE_SIZE * (Configuration.GRID_SIZE - 1)));
        player.addComponent(new GraphicComponent(player, "pinkPlayer"));
        world.addPlayer(player);
    }
}
