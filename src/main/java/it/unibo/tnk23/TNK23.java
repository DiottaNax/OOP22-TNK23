package it.unibo.tnk23;

import javafx.application.Application;

/**
 * This is the main class to run TNK23.
 */
public final class TNK23 {

    /**
     * This private constructor is to prevent instantiation of this utility class.
     * The constructor of a utility class should never be called
     */
    private TNK23() {
    }

    /**
     * The launcher of TNK23.
     *
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(TNK23App.class, args);
    }
}
