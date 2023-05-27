package it.unibo.tnk23.view.api;

import javafx.application.Platform;
import javafx.stage.Stage;

public abstract class AbstractFxGameView implements GameView {

    protected final Stage stage;
    protected final SceneFactory sceneFactory;
    protected final RenderingEngine renderingEngine;
    
    public AbstractFxGameView(final Stage stage) {
        this.sceneFactory = null; //to set when implemented
        this.renderingEngine = null; //to set when implemented
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
    public void setGameScene() {
        this.stage.setFullScreen(true);
        //this.stage.setScene();
    }

    @Override
    public void setMenuScene() {
        this.stage.setFullScreen(false);
        this.stage.setScene(this.sceneFactory.getMenuScene());
        this.stage.sizeToScene();
    }
    
}
