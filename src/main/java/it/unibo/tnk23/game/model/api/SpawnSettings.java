package it.unibo.tnk23.game.model.api;

public interface SpawnSettings {
    
    Spawn start(); /*inizia il gioco*/

    void setDelayOfSpawining (long interval); /*setta il tempo di attesa tra uno spawn ed un altro*/

}
