package it.unibo.tnk23.input.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.tnk23.input.impl.KeyboardInputController;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler {
    
    private List<KeyboardInputController> inputControllers;

    public KeyEventHandler() {
        this.inputControllers = new ArrayList<>();
    }

    public void onKeyPressed(KeyEvent e) {
        inputControllers.forEach(c -> c.setOnKeyPressed(e));
    }

    public void onKeyReleased(KeyEvent e) {
        inputControllers.forEach(c -> c.setOnKeyReleased(e));
    }

    public void addInputController(KeyboardInputController k) {
        this.inputControllers.add(k);
    }

    public void removeInputController(KeyboardInputController k) {
        this.inputControllers.remove(k);
    }


}
