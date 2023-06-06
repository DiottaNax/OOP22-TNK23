package it.unibo.tnk23.input.api;

import javafx.scene.input.KeyEvent;

public interface KeyboardInputController extends InputController {

    boolean isShooting();
    
    void setOnKeyPressed(KeyEvent e);

    void setOnKeyReleased(KeyEvent e);
}
