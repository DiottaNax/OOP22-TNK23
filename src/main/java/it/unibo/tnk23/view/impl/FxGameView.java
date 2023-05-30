package it.unibo.tnk23.view.impl;

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
import it.unibo.tnk23.view.api.AbstractFxGameView;
import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class FxGameView extends AbstractFxGameView {

    private GameEngine gameEngine;

    public FxGameView(Stage stage) {
        super(stage);

        this.stage.setOnCloseRequest(e -> {
            Platform.exit();
            Runtime.getRuntime().exit(0);
        });

        this.setMenuScene();
        this.stage.show();
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
        this.setRenderingEngine(new FxRenderingEngine(world, this));

        this.stage.setScene(this.sceneFactory.getGameScene(this.renderingEngine.getGamePane(), this));
        this.stage.setFullScreen(true);
        this.gameEngine.startEngine();
    }

    @Override
    public GameEngine getGameEngine() {
        return this.gameEngine;
    }

}
