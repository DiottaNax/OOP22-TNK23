package it.unibo.tnk23.game.input.impl;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.game.input.api.InputController;
import javafx.scene.input.KeyEvent;

public class KeyboardInputController implements InputController {

    private Directions direction;
    private boolean isShooting;

    @Override
    public Directions getDirection() {
        return direction;
    }
    
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
                direction = Directions.NONE;
                isShooting = true;
                break;

            default:
                break;

        }
    }

    public void setOnKeyReleased(KeyEvent e) {
        direction = Directions.NONE;
    }
    
    public boolean isShooting() {
        return this.isShooting;
    }
}
