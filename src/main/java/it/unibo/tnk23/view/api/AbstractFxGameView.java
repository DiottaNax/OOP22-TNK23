package it.unibo.tnk23.view.api;

import it.unibo.tnk23.core.api.GameEngine;
//import it.unibo.tnk23.core.impl.GameEngineImpl;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.view.impl.FxRenderingEngine;
import it.unibo.tnk23.view.impl.SceneFactoryImpl;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractFxGameView implements GameView {

    protected final Stage stage;
    protected final SceneFactory sceneFactory;
    protected final FxRenderingEngine renderingEngine;
    protected final GameEngine gameEngine;
    protected final World world;
    
    public AbstractFxGameView(final Stage stage) {
        this.sceneFactory = new SceneFactoryImpl(); //to set when implemented
        this.world = null;
        this.renderingEngine = new FxRenderingEngine(this.world, this.getGameEngine().getGameView()); //to set when implemented
        this.gameEngine = null;//new GameEngineImpl(this, this.world);
        this.stage = stage;     
    }

    @Override
    public void renderView() {
        Platform.runLater(renderingEngine::render);
    }

    @Override
    public void setGameOverScene() {
        this.stage.setFullScreen(false);
        this.stage.setScene(this.sceneFactory.getMenuScene());
        this.stage.sizeToScene();
    }

    @Override
    public void setMenuScene() {
        this.stage.setFullScreen(false);
        this.stage.setScene(this.sceneFactory.getMenuScene());
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
