package it.unibo.tnk23.game.model.api;

import java.util.Set;

import it.unibo.tnk23.common.Point2D;

public interface GameMap {

    Set<Point2D> getDestroyableWalls();

    Set<Point2D> getWalls();    
}
