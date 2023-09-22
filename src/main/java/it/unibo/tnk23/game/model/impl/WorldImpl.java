package it.unibo.tnk23.game.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.events.api.GameEvent;
import it.unibo.tnk23.game.events.api.GameEventListener;
import it.unibo.tnk23.game.model.api.GameMap;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

/**
* Implementation of the World interface that represents the game world.
* It maintains a collection of players, entities, obstacles, and a tower.
* The WorldImpl class provides methods to add and retrieve players, entities, and obstacles,
* as well as update the game state.
*/
public class WorldImpl implements World {

    private final List<GameObject> players;
    private final Set<GameObject> entities;
    private final Set<GameObject> obstacles;
    private final GameObject tower;
    private GameEventListener weListener;

   /**
     * Constructs a new WorldImpl instance with the specified game map.
     * Initializes the players, obstacles, entities, and tower based on the game map.
     *
     * @param gameMap the game map containing the walls, destroyable walls and tower configuration
     */
    public WorldImpl(final GameMap gameMap) {
        this.players = new ArrayList<>();
        this.obstacles = new HashSet<>();
        this.entities = Collections.synchronizedSet(new HashSet<>());
        /*
         * created a synchronized list to avoid a ConcurrentModificationException that
         * occurred in getEntities().
         */
        final var objFactory = new GameObjectFactoryImpl(this);
        var toAdd = gameMap.getWalls().stream().map(objFactory::getWall).toList();
        this.obstacles.addAll(toAdd);
        toAdd = gameMap.getDestroyableWalls().stream().map(objFactory::getDestroyableWall).toList();
        this.obstacles.addAll(toAdd);
        this.entities.addAll(this.obstacles);

        /*
         * The walls around the tower should not be in the obstacle list, 
         * in order not to be seen as obstacle during bfs.
         */
        this.entities.addAll(gameMap.getTowerWalls().stream().map(objFactory::getDestroyableWall).toList());
        tower = objFactory.getTower(gameMap.getTowerPos().sum(Configuration.DISPLACEMENT));
        this.entities.add(tower);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GameObject> getPlayer(final int id) {
        return this.players.size() >= id && id > 0 ? Optional.of(players.get(id - 1))
                : Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPlayer(final GameObject player) {
        this.entities.add(player);
        this.players.add(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GameObject> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> getEntities() {
        final Set<GameObject> toPass = new HashSet<>();
        /*
         * thanks to Synchronized list the forEach below should be "atomic"
         * avoiding ConcurrentModificationException.
         */
        this.entities.forEach(toPass::add);
        return toPass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEntity(final GameObject obj) {
        this.entities.remove(obj);
        this.obstacles.remove(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEntity(final GameObject obj) {
        this.entities.add(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> getObstacles() {
        return Collections.unmodifiableSet(this.obstacles);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings (
        value = {
            "EI"
        },
            justification = "this method needs to return the tower object in the game world"
    )
    @Override
    public GameObject getTower() {
        return this.tower;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWorldEventListener(final GameEventListener weListener) {
        this.weListener = weListener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvent(final GameEvent we) {
        weListener.notifyEvent(we);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.getEntities().stream().parallel().forEach(GameObject::update);
    }
}
