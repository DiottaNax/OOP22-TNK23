package it.unibo.tnk23.game.events.api;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.model.api.GameObject;

public interface WorldEvent {
   
    Point2D getposition();

    GameObject getEventActor();
    
}
