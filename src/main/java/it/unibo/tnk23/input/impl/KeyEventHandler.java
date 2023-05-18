package it.unibo.tnk23.input.impl;

import java.util.List;

import it.unibo.tnk23.input.impl.KeyboardInputController;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler {
    
    List<KeyboardInputController> inputControllers;

    public KeyEventHandler(List<KeyboardInputController> inputControllers) {
        this.inputControllers = inputControllers;
    }

    public void setOnKeyPressed(KeyEvent e) {
        inputControllers.forEach(c -> c.setOnKeyPressed(e));
    }

    public void setOnKeyReleased(KeyEvent e) {
        inputControllers.forEach(c -> c.setOnKeyReleased(e));
    }

    public void addInputController(KeyboardInputController k) {
        this.inputControllers.add(k);
    }

    public void removeInputController(KeyboardInputController k) {
        this.inputControllers.remove(k);
    }


}
