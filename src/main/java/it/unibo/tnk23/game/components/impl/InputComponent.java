package it.unibo.tnk23.game.components.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.input.api.KeyboardInputController;
import it.unibo.tnk23.game.model.api.GameObject;

/**
 * This class represents an input component to move a game object. It implements the Component interface
 * and provides functionality for updating the game object's direction and notifying components about shooting actions.
 */
public class InputComponent implements Component {

    private final GameObject player;
    private final KeyboardInputController ctrl;

    /**
     * Constructs an InputComponent object with the specified player game object and keyboard input controller.
     *
     * @param player the game object to control
     * @param ctrl   the keyboard input controller for receiving input
     */
    @SuppressFBWarnings(
        value = {
            "EI2"
        },
            justification = "The InputComponent must store these parameters in order to use its methods."
    )
    public InputComponent(final GameObject player, final KeyboardInputController ctrl) {
        this.ctrl = ctrl;
        this.player = player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        player.setDirection(ctrl.getDirection());
        player.notifyComponents(ctrl::isShooting, PlayerFireComponent.class);
    }
}
