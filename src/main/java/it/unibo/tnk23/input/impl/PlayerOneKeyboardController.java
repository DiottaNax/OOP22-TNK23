package it.unibo.tnk23.input.impl;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.input.api.KeyboardInputController;
import javafx.scene.input.KeyEvent;

public class PlayerOneKeyboardController implements KeyboardInputController {

    private Directions direction = Directions.NONE;
    private boolean isShooting;

    @Override
    public Directions getDirection() {
        return direction;
    }
    
    @Override
    public void setOnKeyPressed(KeyEvent e) {
        switch(e.getCode()) {
            case UP:
                direction = Directions.NORTH;
                break;
            case DOWN:
                direction = Directions.SOUTH;
                break;
            case LEFT:
            direction = Directions.WEST;
                break;
            case RIGHT:
                direction = Directions.EAST;
                break;
            case ENTER:
                isShooting = true;
                break;

            default:
                break;

        }
    }

    @Override
    public void setOnKeyReleased(KeyEvent e) {        
        switch (e.getCode()) {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                direction = Directions.NONE;
                break;
            case ENTER:
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
