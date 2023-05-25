package it.unibo.tnk23.game.model.api;

import java.util.List;

public interface Round {

    List<GameObject> getEnemies();

    boolean isOver();

    int getRound();

    World getWorld();

    void update();
}
