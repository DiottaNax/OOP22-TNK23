package it.unibo.tnk23.game.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.events.api.WorldEvent;
import it.unibo.tnk23.game.events.api.WorldEventListener;
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
    private GameObject tower;
    private WorldEventListener weListener;

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
        addTower();
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
    public void setWorldEventListener(final WorldEventListener weListener) {
        this.weListener = weListener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvent(final WorldEvent we) {
        weListener.notifyEvent(we);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.getEntities().stream().parallel().forEach(GameObject::update);
    }

    /**
     * Adds a tower to the game.
     * The tower is placed at a specific grid position and creates walls near it as obstacles.
     * The tower and walls are added to the list of entities in the game.
     */
    private void addTower() {
        final int towerBoxSize = 4;
        final int tileSize = Configuration.TILE_SIZE;
        final var wallNearTower = new ArrayList<Pair<Integer, Integer>>();
        final var towerGridPos = new Pair<>(Configuration.GRID_SIZE / 2, Configuration.GRID_SIZE - 1);
        final var towerPos = new Point2D(towerGridPos.getX() * tileSize,
                towerGridPos.getY() * tileSize - Configuration.DISPLACEMENT);
        final var wallGridPos = new Pair<>(Configuration.GRID_SIZE - 2, Configuration.GRID_SIZE * 2 - 3);
        final var obstacleSize = tileSize / 2;
        final GameObjectFactoryImpl objectFactory = new GameObjectFactoryImpl(this);

        this.tower = objectFactory.getTower(new Point2D(towerGridPos.getX() * tileSize + Configuration.DISPLACEMENT,
                towerGridPos.getY() * tileSize));

        for (int i = 0; i < towerBoxSize - 1; i++) {
            for (int j = 0; j < towerBoxSize; j++) {
                final var pos = new Pair<>(wallGridPos.getX() + j, wallGridPos.getY() + i);
                wallNearTower.add(pos);
            }
        }

        this.entities.add(this.tower);
        this.entities.addAll(wallNearTower.stream()
                .map(p -> new Point2D(p.getX() * obstacleSize, p.getY() * obstacleSize - Configuration.DISPLACEMENT))
                .filter(p -> !(p.getX() >= towerPos.getX() && p.getX() <= towerPos.getX() + obstacleSize
                        && p.getY() >= towerPos.getY() && p.getY() <= towerPos.getY() + obstacleSize))
                .map(objectFactory::getDestroyableWall)
                .toList());
    }

}
