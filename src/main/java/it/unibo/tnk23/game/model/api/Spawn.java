package it.unibo.tnk23.game.model.api;

import it.unibo.tnk23.common.Point2D;

public interface Spawn {
    
    void spawnEnemies();
    
    void update();

    Point2D getPos();
}
