package it.unibo.tnk23.game.model.api;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.tnk23.game.events.api.GameEvent;
import it.unibo.tnk23.game.events.api.GameEventListener;

/**
 * An interface representing the game world.
 * The world interface provides methods to interact with the game world and its enetities.
 */
public interface World {

    /**
     * Adds a player to the game world.
     * 
     * @param player the GameObject to be added.
     */
    void addPlayer(GameObject player);

    /**
     * Gets the player with the specified ID from the game world.
     * 
     * @param id the ID of the player.
     * @return an Optional containing the player GameObject, or an empty Optional if the player is not found.
     */
    Optional<GameObject> getPlayer(int id);

    /**
     * Gets a list of all players in the game world.
     * 
     * @return a list of players GameObjects.
     */
    List<GameObject> getPlayers();

    /**
     * Gets a set of all entities in the game world.
     * 
     * @return a set of entities GameObjects.
     */
    Set<GameObject> getEntities();

    /**
     * Adds an entity to the game world.
     * 
     * @param obj the GameObject to be added.
     */
    void addEntity(GameObject obj);

    /**
     * Remooves an entity from the game world.
     * 
     * @param obj the GameObject to be removed.
     */
    void removeEntity(GameObject obj);

    /**
     * Gets a set of all obstacles in the game world.
     * 
     * @return a set of obstacles GameObjects.
     */
    Set<GameObject> getObstacles();

    /**
     * Gets the tower object in the game world.
     * 
     * @return the tower GameObject. 
     */
    GameObject getTower();

    /**
     * Sets a {@link GameEventListener} for the world events.
     * 
     * @param weListener the listener for the world events.
     */
    void setWorldEventListener(GameEventListener weListener);

    /**
     * Notifies the world listener of a specific event.
     * 
     * @param we the world event to be notified.
     */
    void notifyEvent(GameEvent we);

    /**
     * Updates the state of the world.
     * This method is responsible for updating the world's entities.
     */
    void update();
}
