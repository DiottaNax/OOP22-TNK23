package it.unibo.tnk23.input.impl;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.input.api.KeyboardInputController;
import javafx.scene.input.KeyEvent;

/**
 * The PlayerTwoKeyboardController class implements the KeyboardInputController interface
 * and represents a keyboard controller for player two that provides directional input
 * and shooting functionality.
 */
public class PlayerTwoKeyboardController implements KeyboardInputController {

    private Directions direction = Directions.NONE;
    private boolean isShooting;

    /**
     * {@inheritDoc}
     */
    @Override
    public Directions getDirection() {
        return direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOnKeyPressed(final KeyEvent e) {
        switch (e.getCode()) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOnKeyReleased(final KeyEvent e) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShooting() {
        return this.isShooting;
    }
}
