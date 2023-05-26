package it.unibo.tnk23.view.api;

import javafx.scene.Parent;

public interface RenderingEngine {

    Parent getGameSceneRoot();

    void run();
    
}
