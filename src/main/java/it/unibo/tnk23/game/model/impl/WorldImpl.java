package it.unibo.tnk23.game.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.events.api.WorldEvent;
import it.unibo.tnk23.game.events.api.WorldEventListener;
import it.unibo.tnk23.game.model.api.GameMap;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

public class WorldImpl implements World {

    private List<GameObject> players;
    private Set<GameObject> entities;
    private Set<GameObject> obstacles;
    private GameObject tower;
    private WorldEventListener weListener;

    public WorldImpl(final GameMap gameMap) {
        this.players = new ArrayList<>();
        this.obstacles = new HashSet<>();
        this.entities = new HashSet<>();
        
        var objFactory = new GameObjectFactoryImpl(this);
        var toAdd = gameMap.getWalls().stream().map(objFactory::getWall).toList();
        this.obstacles.addAll(toAdd);
        toAdd = gameMap.getDestroyableWalls().stream().map(objFactory::getDestroyableWall).toList();
        this.obstacles.addAll(toAdd);
        this.entities.addAll(this.obstacles);
        addTower();
        
    }

    @Override
    public Optional<GameObject> getPlayer(int id) {
        return this.players.size() >= id && id > 0 ? Optional.of(players.get(id - 1))
                : Optional.empty();
    }

    @Override
    public void addPlayer(GameObject player) {
        this.entities.add(player);
        this.players.add(player);
    }
    
    @Override
    public List<GameObject> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }

    @Override
    public Set<GameObject> getEntities() {
        return Collections.unmodifiableSet(this.entities);
    }

    @Override
    public void removeEntity(GameObject obj) {
        this.entities.remove(obj);
        this.obstacles.remove(obj);
    }

    @Override
    public void addEntity(GameObject obj) {
        this.entities.add(obj);
    }

    @Override
    public Set<GameObject> getObstacles() {
        return Collections.unmodifiableSet(this.obstacles);
    }

    @Override
    public GameObject getTower() {
        return this.tower;
    }

    @Override
    public void setWorldEventListener(WorldEventListener weListener) {
        this.weListener = weListener;
    }

    @Override
    public void notifyEvent(WorldEvent we) {
        weListener.notifyEvent(we);
    }

    @Override
    public void update() {
        this.getEntities().stream().parallel().forEach(GameObject::update);
    }
    
    private void addTower() {
        int towerBoxSize = 4;
        int tileSize = Configuration.TILE_SIZE;
        var wallNearTower = new ArrayList<Pair<Integer, Integer>>();
        var towerGridPos = new Pair<>(Configuration.GRID_SIZE / 2, Configuration.GRID_SIZE -1);
        var wallGridPos = new Pair<>(Configuration.GRID_SIZE-2, (Configuration.GRID_SIZE * 2) - 3); 
        var obstacleSize = tileSize / 2;
        GameObjectFactoryImpl objectFactory = new GameObjectFactoryImpl(this);
        this.tower = objectFactory.getTower(new Point2D(towerGridPos.getX() * Configuration.TILE_SIZE,
                towerGridPos.getY() * Configuration.TILE_SIZE));
        
        for (int i = 0; i < towerBoxSize-1; i++) {
            for (int j = 0; j < towerBoxSize; j++) {
                var pos = new Pair<>(wallGridPos.getX() + j, wallGridPos.getY() + i);
                wallNearTower.add(pos);
            }
        }
        
        this.entities.add(this.tower);
        var towerPos = tower.getPosition();
        this.entities.addAll(wallNearTower.stream()
                .map(p -> new Point2D(p.getX() * obstacleSize, p.getY() * obstacleSize))
                .filter(p -> !(p.getX() >= towerPos.getX() && p.getX() <= towerPos.getX() + obstacleSize
                        && p.getY() >= towerPos.getY() && p.getY() <= towerPos.getY() + obstacleSize))
                .map(objectFactory::getDestroyableWall)
                .toList());
    }
    
}
