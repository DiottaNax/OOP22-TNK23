package it.unibo.tnk23.input.api;

import it.unibo.tnk23.common.Directions;

/**
 * The InputController interface represents an input controller that provides
 * directional input.
 */
public interface InputController {

    /**
     * Retrieves the direction input from the controller.
     *
     * @return the direction input
     */
    Directions getDirection();

}

