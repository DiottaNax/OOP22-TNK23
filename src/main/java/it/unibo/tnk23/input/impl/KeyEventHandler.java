package it.unibo.tnk23.input.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.tnk23.input.api.KeyboardInputController;
import javafx.scene.input.KeyEvent;

/**
 * The KeyEventHandler class handles key events and notifies KeyboardInputControllers.
 * It allows adding and removing input controllers to receive key events.
 */
public class KeyEventHandler {

    private final List<KeyboardInputController> inputControllers;

    /**
     * Constructs a new KeyEventHandler object.
     * Initializes the list of input controllers.
     */
    public KeyEventHandler() {
        this.inputControllers = new ArrayList<>();
    }

    /**
     * Notifies all registered input controllers about a key pressed event.
     * 
     * @param e The KeyEvent representing the key pressed event.
     */
    public void onKeyPressed(final KeyEvent e) {
        inputControllers.forEach(c -> c.setOnKeyPressed(e));
    }

    /**
     * Notifies all registered input controllers about a key released event.
     * 
     * @param e The KeyEvent representing the key released event.
     */
    public void onKeyReleased(final KeyEvent e) {
        inputControllers.forEach(c -> c.setOnKeyReleased(e));
    }

    /**
     * Adds an input controller to the list of registered input controllers.
     * 
     * @param k The KeyboardInputController to be added.
     */
    public void addInputController(final KeyboardInputController k) {
        this.inputControllers.add(k);
    }

    /**
     * Removes an input controller from the list of registered input controllers.
     * 
     * @param k The KeyboardInputController to be removed.
     */
    public void removeInputController(final KeyboardInputController k) {
        this.inputControllers.remove(k);
    }
}
