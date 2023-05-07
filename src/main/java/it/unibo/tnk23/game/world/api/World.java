package it.unibo.tnk23.game.world.api;

import java.util.*;

import it.unibo.tnk23.game.model.api.GameObject;

public interface World {
    
    List<GameObject> getPlayers();

    List<GameObject> getEntities();

    List<GameObject> getObstacles();

    void update();
}
