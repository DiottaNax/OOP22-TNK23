package it.unibo.tnk23.game.model.impl;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.impl.BulletComponent;
import it.unibo.tnk23.game.components.impl.BulletHealthComponent;
import it.unibo.tnk23.game.components.impl.CollisionComponent;
import it.unibo.tnk23.game.components.impl.EntitiesHealthComponent;
import it.unibo.tnk23.game.components.impl.GraphicComponent;
import it.unibo.tnk23.game.components.impl.PhysicsComponent;
import it.unibo.tnk23.game.components.impl.PlayerFireComponent;
import it.unibo.tnk23.game.components.impl.EnemiesFireComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.GameObjectFactory;
import it.unibo.tnk23.game.model.api.World;


/**
 * The GameObjectFactoryImpl class implements the GameObjectFactory interface and provides
 * methods for creating various game objects.
 */
public class GameObjectFactoryImpl implements GameObjectFactory {

    private final World world;

    /**
     * Creates a new GameObjectFactoryImpl instance with the specified World.
     *
     * @param world the game world
     */
    public GameObjectFactoryImpl(final World world) {
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getEnemy(final Point2D pos) {
        final var enemy = new GameObjectImpl(GameObjectTypeManager.getEnemyType(), pos);
        enemy.addComponent(new EntitiesHealthComponent(enemy, world));
        enemy.addComponent(new EnemiesFireComponent(enemy, world));
        enemy.addComponent(new PhysicsComponent(enemy, world));
        enemy.addComponent(new CollisionComponent(enemy, world));
        return enemy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getPlayer(final Point2D pos) {
        final var player = new GameObjectImpl(GameObjectTypeManager.getPlayerType(), pos);
        player.addComponent(new EntitiesHealthComponent(player, world));
        player.addComponent(new PlayerFireComponent(player, world));
        player.addComponent(new PhysicsComponent(player, world));
        player.addComponent(new CollisionComponent(player, world));
        return player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getBullet(final Point2D pos) {
        final  var bullet = new GameObjectImpl(GameObjectTypeManager.getBulletType(), pos);
        bullet.addComponent(new BulletHealthComponent(bullet, world));
        bullet.addComponent(new PhysicsComponent(bullet, world));
        bullet.addComponent(new CollisionComponent(bullet, world));
        bullet.addComponent(new BulletComponent(bullet, world));
        bullet.addComponent(new GraphicComponent("bullet"));
        return bullet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getWall(final Point2D pos) {
        final var wall = new GameObjectImpl(GameObjectTypeManager.getObstacleType(), pos);
        wall.addComponent(new CollisionComponent(wall, world));
        wall.addComponent(new GraphicComponent("wall"));
        return wall;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getDestroyableWall(final Point2D pos) {
        final var destroyableWall = new GameObjectImpl(GameObjectTypeManager.getObstacleType(), pos);
        destroyableWall.addComponent(new CollisionComponent(destroyableWall, world));
        destroyableWall.addComponent(new EntitiesHealthComponent(destroyableWall, world));
        destroyableWall.addComponent(new GraphicComponent("destroyableWall"));
        return destroyableWall;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getTower(final Point2D pos) {
        final var twr = new GameObjectImpl(GameObjectTypeManager.getObstacleType(), pos);
        twr.addComponent(new EntitiesHealthComponent(twr, world));
        twr.addComponent(new CollisionComponent(twr, world));
        twr.addComponent(new GraphicComponent("tower"));
        return twr;
    }
}
