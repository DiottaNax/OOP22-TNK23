package it.unibo.tnk23.model.world.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.tnk23.model.world.api.World;
import it.unibo.tnk23.model.api.GameObject;

public class WorldImpl implements World{
    

    private final Set<GameObject> setGameObject;


    protected WorldImpl(final List<GameObject> listGameObject) {
        setGameObject = listGameObject.stream().collect(Collectors.toSet());
    }


    @Override
    public List<GameObject> getPlayers() {
        return setGameObject.stream().toList();
    }


    @Override
    public List<GameObject> getEntities() {
        return setGameObject.stream().toList();
    }


    @Override
    public List<GameObject> getObstacles() {
        return setGameObject.stream().toList();
    }


    @Override
    public void update() {
        setGameObject.stream().forEach(g ->g.update());
    }


}
