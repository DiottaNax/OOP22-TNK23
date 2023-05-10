package it.unibo.tnk23.game.events.api;

import it.unibo.tnk23.game.world.api.World;

public interface WorldEventFactory {
    
    void onShootEvent(World world);

    void onSpawnEvent(World world);

    void onDeathEvent(World world);

}
