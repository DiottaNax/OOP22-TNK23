package it.unibo.tnk23.game.model.impl;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.impl.BulletComponent;
import it.unibo.tnk23.game.components.impl.CollisionComponent;
import it.unibo.tnk23.game.components.impl.PlayerFireComponent;
import it.unibo.tnk23.game.components.impl.TankHealthComponent;
import it.unibo.tnk23.game.components.impl.TimeFireComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.GameObjectFactory;
import it.unibo.tnk23.game.model.api.World;

public class GameObjectFactoryImpl implements GameObjectFactory {

    private final World world;

    public GameObjectFactoryImpl(World world) {
        this.world = world;
    }


    @Override
    public GameObject getEnemy(Point2D pos) {
        var enemy = new GameObjectImpl(TypeObjectFactory.getEnemyType(), pos);
        enemy.addComponent(new TankHealthComponent(enemy, world));
        enemy.addComponent(new TimeFireComponent(enemy, world));
        enemy.addComponent(new CollisionComponent(enemy, world));
        return enemy;
    }

    @Override
    public GameObject getPlayer(Point2D pos) {
        var player = new GameObjectImpl(TypeObjectFactory.getPlayerType(), pos);
        player.addComponent(new TankHealthComponent(player, world));
        player.addComponent(new PlayerFireComponent(player, world));
        player.addComponent(new CollisionComponent(player, world));
        return player;
    }

    @Override
    public GameObject getBullet(Point2D pos) {
        var bullet = new GameObjectImpl(TypeObjectFactory.getBulletType(), pos);
        bullet.addComponent(new HealthComponent(bullet, world));
        bullet.addComponent(new CollisionComponent(bullet, world));
        bullet.addComponent(new BulletComponent(bullet, world));
        return bullet;
    }

    @Override
    public GameObject getWall(Point2D pos) {
        var wall = new GameObjectImpl(TypeObjectFactory.getObstacleType(), pos);
        wall.addComponent(new PhysicsComponent(wall, world));
        wall.addComponent(new CollisionComponent(wall, world));
        return wall;
    }

    @Override
    public GameObject getDestroyableWall(Point2D pos) {
        var destroyableWall = new GameObjectImpl(TypeObjectFactory.getObstacleType(), pos);
        destroyableWall.addComponent(new PhysicsComponent(destroyableWall, world));
        destroyableWall.addComponent(new CollisionComponent(destroyableWall, world));
        destroyableWall.addComponent(new HealthComponent(destroyableWall, world));
        return destroyableWall;
    }
    
}
