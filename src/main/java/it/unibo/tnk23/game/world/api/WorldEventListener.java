package it.unibo.tnk23.game.world.api;

import it.unibo.tnk23.game.model.api.GameObject;

public interface WorldEventListener{

    void notifyEvent(WorldEvent we,GameObject object);

}
