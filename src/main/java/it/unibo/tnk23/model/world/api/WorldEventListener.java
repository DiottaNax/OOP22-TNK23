package it.unibo.tnk23.model.world.api;

import it.unibo.tnk23.model.api.GameObject;

public interface WorldEventListener{

    void notifyEvent(WorldEvent we,GameObject object);

}
