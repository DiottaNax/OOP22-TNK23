package it.unibo.tnk23.game.model.api;

import java.util.List;

import it.unibo.tnk23.game.world.api.World;

public interface Round {

    List<GameObject> getEnemies();

    boolean isOver();

    int getRound();

    World getWorld();

    void update();
}
