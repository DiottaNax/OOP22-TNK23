package it.unibo.tnk23.game.components.api;

import it.unibo.tnk23.game.model.api.GameObject;

@FunctionalInterface
public interface Bonus {

    public void perform(GameObject entity);
    
}
