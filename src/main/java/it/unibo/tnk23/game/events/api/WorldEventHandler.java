package it.unibo.tnk23.game.events.api;

import it.unibo.tnk23.game.world.api.World;

public interface WorldEventHandler {
    
    void handle(WorldEvent we, World world);
    
}
