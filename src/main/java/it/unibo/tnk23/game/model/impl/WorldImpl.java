package it.unibo.tnk23.game.model.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.tnk23.game.components.impl.GraphicComponent;
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
    private final GameMap gameMap;
    private WorldEventListener weListener;

    public WorldImpl(final List<GameObject> players, final GameMap gameMap) {
        this.players = players;
        this.gameMap = gameMap;
        this.obstacles = new HashSet<>();
        this.entities = new HashSet<>();

        var objFactory = new GameObjectFactoryImpl(this);
        var toAdd = gameMap.getWalls().stream().map(objFactory::getWall);
        toAdd.forEach(w -> w.addComponent(new GraphicComponent(w, "wall")));
        this.obstacles.addAll(toAdd.toList());

        toAdd = gameMap.getDestroyableWalls().stream().map(objFactory::getDestroyableWall);
        toAdd.forEach(w -> w.addComponent(new GraphicComponent(w, "destroyableWall")));
        this.obstacles.addAll(toAdd.toList());
    }

    @Override
    public Optional<GameObject> getPlayer(int id) {
        return this.players.size() >= id && id < 0 ? Optional.of(players.get(id - 1))
                : Optional.empty();
    }
    
    @Override
    public List<GameObject> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }

    @Override
    public Set<GameObject> getEntities() {
        return this.entities;
    }

    @Override
    public Set<GameObject> getObstacles() {
        return this.obstacles;
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
    
    /*private void addTower() {
    }*/
}
