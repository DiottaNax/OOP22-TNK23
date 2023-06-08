package it.unibo.tnk23.game.model.api;

import java.util.Set;
import it.unibo.tnk23.common.Point2D;

public interface GameMap {
    /**
     * Retrieves the set of destroyable walls in the game map.
     *
     * @return the set of destroyable walls as a set of Point2D coordinates
     */
    Set<Point2D> getDestroyableWalls();

    /**
     * Retrieves the set of walls in the game map.
     *
     * @return the set of walls as a set of Point2D coordinates
     */
    Set<Point2D> getWalls();
}
