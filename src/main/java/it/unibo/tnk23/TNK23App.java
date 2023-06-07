package it.unibo.tnk23;

import it.unibo.tnk23.view.impl.FxGameView;
import javafx.application.Application;
import javafx.stage.Stage;

public class TNK23App extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        new FxGameView(primaryStage);
    }
    
}
