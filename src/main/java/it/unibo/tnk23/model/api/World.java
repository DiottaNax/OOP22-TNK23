package it.unibo.tnk23.model.api;

import java.util.*;

public interface World {
    
    List<GameObject> getPlayers();

    List<GameObject> getEntities();

    List<GameObject> getObstacles();

    void update();
}
