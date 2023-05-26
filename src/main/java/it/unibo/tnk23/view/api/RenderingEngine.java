package it.unibo.tnk23.view.api;

import java.lang.Runnable;
import javafx.scene.Parent;

public interface RenderingEngine extends Runnable {

    Parent getGameSceneRoot();
    
}
