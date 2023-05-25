package it.unibo.tnk23.game.model.impl;

import java.util.Set;

import it.unibo.tnk23.game.events.api.WorldEvent;
import it.unibo.tnk23.game.events.api.WorldEventListener;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

public class WorldImpl implements World {

    private Set<GameObject> players;
    private Set<GameObject> entities;
    private Set<GameObject> obstacles;
    private GameObject tower;
    private WorldEventListener weListener;

    public WorldImpl(final Set<GameObject> players) {
        this.players = players;
    }

    @Override
    public Set<GameObject> getPlayers() {
        return this.players;
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
