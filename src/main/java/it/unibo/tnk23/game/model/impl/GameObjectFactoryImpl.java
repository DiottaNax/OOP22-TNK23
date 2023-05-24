package it.unibo.tnk23.game.model.impl;

import java.util.List;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.game.components.impl.CollisionComponent;
import it.unibo.tnk23.game.components.impl.HealthComponent;
import it.unibo.tnk23.game.components.impl.PlayerFireComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.GameObjectFactory;
import it.unibo.tnk23.game.model.api.TypeObject;
import it.unibo.tnk23.game.world.api.World;

public class GameObjectFactoryImpl implements GameObjectFactory {

    private final World world;

    public GameObjectFactoryImpl(World world) {
        this.world = world;
    }


    @Override
    public GameObject getEnemy(Point2D pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEnemy'");
    }

    @Override
    public GameObject getPlayer(Point2D pos) {
        var player = new GameObjectImpl(TypeObjectFactory.getPlayerType(), pos);
        /* var components = List.of(HealthComponent.class, PlayerFireComponent.class, CollisionComponent.class);
        components.stream().forEach(c -> {
            try{
                player.addComponent(c.getConstructor(GameObject.class, World.class)
                        .newInstance(player, this.world));
            } catch(Exception e) { 
                e.printStackTrace();
            }}); */
        player.addComponent(new HealthComponent(player, world));
        player.addComponent(new PlayerFireComponent(player, world));
        player.addComponent(new CollisionComponent(player, world));
        return player;
    }

    @Override
    public GameObject getBullet(Point2D pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBullet'");
    }

    @Override
    public GameObject getWall(Point2D pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWall'");
    }

    @Override
    public GameObject getDestroyableWall(Point2D pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDestroyableWall'");
    }
    
}
