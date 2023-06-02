package it.unibo.tnk23.view.impl;

import java.io.IOException;

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
import it.unibo.tnk23.input.impl.KeyboardInputController;
import it.unibo.tnk23.view.api.GameView;
import it.unibo.tnk23.view.api.SceneFactory;
import it.unibo.tnk23.view.api.SideScenesController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class FxGameView implements GameView {

    private final Stage stage;
    private final SceneFactory sceneFactory;

    private GameEngine gameEngine;
    private FxRenderingEngine renderingEngine;
    private SideScenesController sideScenesController;


    public FxGameView(Stage stage) {
        this.stage = stage;
        this.sceneFactory = new SceneFactoryImpl();

        this.stage.setOnCloseRequest(e -> {
            Platform.exit();
            Runtime.getRuntime().exit(0);
        });

        var world = new WorldImpl(new GameMapImpl(ClassLoader.getSystemResourceAsStream("it/unibo/maps/map1.txt")));
        var player = new GameObjectFactoryImpl(world).getPlayer(new Point2D(40, 40));
        player.addComponent(new GraphicComponent(player, "pinkPlayer"));
        world.addPlayer(player);
        this.setGameScene(world);
        //this.setMenuScene();

        this.stage.show();
    }

    @Override
    public void renderView() {
        Platform.runLater(() -> {
            renderingEngine.render();
            //sideScenesController.updateLabels();
        });
    }

    @Override
    public void setGameScene(final World world) {
        var keyEventHandler = new KeyEventHandler();
        var inputController = new KeyboardInputController();
        keyEventHandler.addInputController(inputController);

        this.stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEventHandler::onKeyPressed);
        this.stage.addEventHandler(KeyEvent.KEY_RELEASED, keyEventHandler::onKeyReleased);

        world.getPlayers().forEach(p -> p.addComponent(new InputComponent(p, inputController)));

        gameEngine = new GameEngineImpl(world, this);
        this.renderingEngine = new FxRenderingEngine(world, this);
        this.gameEngine.startEngine();

        this.stage.setScene(this.sceneFactory.getGameScene(this.renderingEngine.getGamePane(), this));
        this.stage.setFullScreen(true);
    }

    @Override
    public void setMenuScene() {
        try{
            this.stage.setFullScreen(false);
            this.stage.setScene(this.sceneFactory.getMenuScene(this));
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

    public void setScene(final Scene scene) {
        this.stage.setScene(scene);
    }

}
