package it.unibo.tnk23.game.model.api;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.api.Bonus;

/**
 * The GameObjectFactory interface represents a factory for creating game objects.
 */
public interface GameObjectFactory {

    /**
     * Creates an enemy game object at the specified position and adds all its necessary components.
     *
     * @param pos the position of the enemy game object
     * @return the created enemy game object
     */
    GameObject getEnemy(Point2D pos);

    /**
     * Creates a player game object at the specified position and adds all its necessary components.
     *
     * @param pos the position of the player game object
     * @return the created player game object
     */
    GameObject getPlayer(Point2D pos);

    /**
     * Creates a bullet game object at the specified position and adds all its necessary components.
     *
     * @param pos the position of the bullet game object
     * @return the created bullet game object
     */
    GameObject getBullet(Point2D pos);

    /**
     * Creates a wall game object at the specified position and adds all its necessary components.
     *
     * @param pos the position of the wall game object
     * @return the created wall game object
     */
    GameObject getWall(Point2D pos);

    /**
     * Creates a destroyable wall game object at the specified position and adds all its necessary components.
     *
     * @param pos the position of the destroyable wall game object
     * @return the created destroyable wall game object
     */
    GameObject getDestroyableWall(Point2D pos);

    /**
     * Creates a tower game object at the specified position and adds all its necessary components.
     *
     * @param pos the position of the tower game object
     * @return the created tower game object
     */
    GameObject getTower(Point2D pos);

    /**
     * Creates a bonus game object at the specified position and adds all its necessary components.
     *
     * @param pos the position of the bonus game object
     * @return the created bonus game object
     */
    GameObject getBonus(Bonus bonus, Point2D pos);
}
