package it.unibo.tnk23.view.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import it.unibo.tnk23.core.api.GameEngine;
import it.unibo.tnk23.core.impl.GameEngineImpl;
import it.unibo.tnk23.game.components.impl.InputComponent;
import it.unibo.tnk23.game.model.api.GameMap;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.impl.WorldImpl;
import it.unibo.tnk23.input.impl.KeyEventHandler;
import it.unibo.tnk23.input.impl.KeyboardInputController;
import it.unibo.tnk23.view.api.GameView;
import it.unibo.tnk23.view.api.SceneFactory;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class FxGameView implements GameView {

    private final Stage stage;
    private final SceneFactory sceneFactory;

    private GameEngine gameEngine;
    private FxRenderingEngine renderingEngine;


    public FxGameView(Stage stage) {
        this.stage = stage;
        this.sceneFactory = new SceneFactoryImpl();

        this.stage.setOnCloseRequest(e -> {
            Platform.exit();
            Runtime.getRuntime().exit(0);
        });

        this.setMenuScene();
        this.stage.show();
    }

    @Override
    public void renderView() {
        Platform.runLater(renderingEngine::render);
    }

    @Override
    public void setGameScene(final List<GameObject> players, final GameMap gameMap) {
        var keyEventHandler = new KeyEventHandler(new ArrayList<>());
        var inputController = new KeyboardInputController();

        this.stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEventHandler::setOnKeyPressed);
        this.stage.addEventHandler(KeyEvent.KEY_RELEASED, keyEventHandler::setOnKeyReleased);

        players.forEach(p -> p.addComponent(new InputComponent(p, inputController)));

        var world = new WorldImpl(players, gameMap);
        gameEngine = new GameEngineImpl(world);
        this.renderingEngine = new FxRenderingEngine(world, this);

        this.stage.setScene(this.sceneFactory.getGameScene(this.renderingEngine.getGamePane(), this));
        this.stage.setFullScreen(true);
        this.gameEngine.startEngine();
    }

    @Override
    public void setMenuScene() {
        this.stage.setFullScreen(false);
        this.stage.setScene(this.sceneFactory.getMenuScene(this));
        this.stage.sizeToScene();
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
