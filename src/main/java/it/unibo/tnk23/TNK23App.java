package it.unibo.tnk23;

import it.unibo.tnk23.view.impl.FxGameView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main application class for running TNK23.
 */
public final class TNK23App extends Application {

    /**
     * The entry point of TNK23 application.
     *
     * @param primaryStage the primary stage used for the application
     * @throws Exception if an error occurs during the application start
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        new FxGameView(primaryStage);
    }
}
