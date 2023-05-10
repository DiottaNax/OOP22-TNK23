package it.unibo.tnk23.game.world.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.tnk23.game.events.api.WorldEvent;
import it.unibo.tnk23.game.events.api.WorldEventListener;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.world.api.World;

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


    @Override
    public void setWorldEventListener(WorldEventListener weListener) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setWorldEventListener'");
    }


    @Override
    public void notifyEvent(WorldEvent we) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notifyEvent'");
    }


}
