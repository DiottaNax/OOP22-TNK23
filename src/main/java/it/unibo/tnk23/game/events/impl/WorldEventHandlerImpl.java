package it.unibo.tnk23.game.events.impl;

import it.unibo.tnk23.game.events.api.WorldEvent;
import it.unibo.tnk23.game.events.api.WorldEventHandler;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.model.api.World;

public class WorldEventHandlerImpl implements WorldEventHandler {

    @Override
    public void handle(WorldEvent we, World world) {
        WorldEventType type = we.getType();
        switch (type) {
            case DEATH_EVENT:
                world.getEntities().remove(we.getEventActor());

            case SHOOT_EVENT:
                world.getEntities().add(we.getEventActor());

            case SPAWN_EVENT:
                world.getEntities().add(we.getEventActor());
        }
    }
    
}
