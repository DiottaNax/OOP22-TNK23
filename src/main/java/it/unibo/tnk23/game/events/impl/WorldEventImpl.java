package it.unibo.tnk23.game.events.impl;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.events.api.WorldEvent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.model.api.GameObject;

public class WorldEventImpl implements WorldEvent {

    Point2D position;
    GameObject actor;
    WorldEventType type;

    public WorldEventImpl(Point2D position, GameObject actor, WorldEventType type) {
        this.position = position;
        this.actor = actor;
        this.type = type;
    }

    @Override
    public Point2D getposition() {
        return position;
    }

    @Override
    public GameObject getEventActor() {
        return actor;
    }

    @Override
    public WorldEventType getType() {
        return type;
    }
    
}
