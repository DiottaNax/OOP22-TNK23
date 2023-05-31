package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.input.api.InputController;
import it.unibo.tnk23.input.impl.KeyboardInputController;
import it.unibo.tnk23.game.model.api.GameObject;

public class InputComponent implements Component {

    GameObject player;
    KeyboardInputController ctrl;

    public InputComponent(GameObject player, KeyboardInputController ctrl) {
        this.ctrl = ctrl;
        this.player = player;
    }

    @Override
    public void update() {
        player.setDirection(ctrl.getDirection());
        player.notifyComponents(ctrl::isShooting, PlayerFireComponent.class);
    }

}
