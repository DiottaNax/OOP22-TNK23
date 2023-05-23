package it.unibo.tnk23.game.model.api;

import it.unibo.tnk23.common.Point2D;

public interface GameObjectFactory {
    
    GameObject getEnemy(Point2D pos);

    GameObject getPlayer(Point2D pos);

    GameObject getBullet(Point2D pos);

    GameObject getWall(Point2D pos);

    GameObject getDestroyableWall(Point2D pos);
}
