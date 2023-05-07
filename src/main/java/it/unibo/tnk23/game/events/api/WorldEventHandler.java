package it.unibo.tnk23.game.events.api;

import it.unibo.tnk23.game.world.api.World;

public interface WorldEventHandler {
    
    /*void handleShootEvent(World world);
    
    void handleSpawnEvent(World world);
    
    void handleDeathEvent(World world);
    */
    
    void handle(WorldEvent e);

}
