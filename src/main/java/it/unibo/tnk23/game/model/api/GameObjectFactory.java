package it.unibo.tnk23.game.model.api;

public interface GameObjectFactory {
    
    GameObject getEnemy();

    GameObject getPlayer();

    GameObject getBullet();

    GameObject getWall();

    GameObject getDestroyableWall();
}
