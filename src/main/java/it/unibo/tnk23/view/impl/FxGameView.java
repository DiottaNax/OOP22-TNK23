package it.unibo.tnk23.view.impl;

import it.unibo.tnk23.view.api.AbstractFxGameView;
import it.unibo.tnk23.view.api.RenderingEngine;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxGameView extends AbstractFxGameView {

    public FxGameView(Stage stage) {
        super(stage);

        this.stage.setOnCloseRequest(e -> {
            Platform.exit();
            Runtime.getRuntime().exit(0);
        });

        this.setMenuScene();
    }
    
    public void setScene(final Scene scene){

    }
}
