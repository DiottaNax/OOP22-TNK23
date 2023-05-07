package it.unibo.tnk23.game.world.api;

public interface WorldEventFactory {
    
    void onShootEvent(World world);

    void onSpawnEvent(World world);

    void onDeathEvent(World world);

}
