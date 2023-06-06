package it.unibo.tnk23.input.impl;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.input.api.KeyboardInputController;
import javafx.scene.input.KeyEvent;

public class PlayerTwoKeyboardController implements KeyboardInputController {

    private Directions direction = Directions.NONE;
    private boolean isShooting;

    @Override
    public Directions getDirection() {
        return direction;
    }
    
    @Override
    public void setOnKeyPressed(KeyEvent e) {
        switch(e.getCode()) {
            case W:
                direction = Directions.NORTH;
                break;
            case S:
                direction = Directions.SOUTH;
                break;
            case A:
            direction = Directions.WEST;
                break;
            case D:
                direction = Directions.EAST;
                break;
            case CAPS:
                isShooting = true;
                break;

            default:
                break;

        }
    }

    @Override
    public void setOnKeyReleased(KeyEvent e) {        
        switch (e.getCode()) {
            case W:
            case S:
            case A:
            case D:
                direction = Directions.NONE;
                break;
            case CAPS:
                this.isShooting = false;
                break;
            default:
                break;
        }
    }
    
    @Override
    public boolean isShooting() {
        return this.isShooting;
    }
}
