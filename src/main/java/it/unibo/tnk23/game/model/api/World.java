package it.unibo.tnk23.game.model.api;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.tnk23.game.events.api.WorldEvent;
import it.unibo.tnk23.game.events.api.WorldEventListener;

public interface World {
    
    Optional<GameObject> getPlayer(int id);

    List<GameObject> getPlayers();

    Set<GameObject> getEntities();

    Set<GameObject> getObstacles();
    
    GameObject getTower();

    void setWorldEventListener(WorldEventListener weListener);

    void notifyEvent(WorldEvent we);

    void update();
}
