package it.unibo.tnk23.view.api;

import it.unibo.tnk23.view.impl.FxRenderingEngine;
import it.unibo.tnk23.view.impl.SceneFactoryImpl;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractFxGameView implements GameView {

    protected final Stage stage;
    protected final SceneFactory sceneFactory;
    protected FxRenderingEngine renderingEngine;
    
    public AbstractFxGameView(final Stage stage) {
        this.sceneFactory = new SceneFactoryImpl(); //to set when implemented
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

    protected void setRenderingEngine(final FxRenderingEngine engine) {
        this.renderingEngine = engine;
    }

    public void setScene(final Scene scene) {
        this.stage.setScene(scene);
    }
    
}
