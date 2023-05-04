package it.unibo.tnk23.model.api;

import java.util.List;

public interface Round {

    List<GameObject> getEnemies();

    boolean isOver();

    int getRound();
}
