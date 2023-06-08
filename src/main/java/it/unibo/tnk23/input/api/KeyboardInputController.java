package it.unibo.tnk23.input.api;

import javafx.scene.input.KeyEvent;

/**
 * The KeyboardInputController interface extends the InputController interface
 * and represents a keyboard input controller that provides directional input
 * and shooting functionality.
 */
public interface KeyboardInputController extends InputController {

    /**
     * Checks if the game object is shooting.
     *
     * @return true if shooting action is triggered, false otherwise
     */
    boolean isShooting();

    /**
     * Sets the event handler for a key press event.
     *
     * @param e the key press event handler
     */
    void setOnKeyPressed(KeyEvent e);

    /**
     * Sets the event handler for a key release event.
     *
     * @param e the key release event handler
     */
    void setOnKeyReleased(KeyEvent e);
}

