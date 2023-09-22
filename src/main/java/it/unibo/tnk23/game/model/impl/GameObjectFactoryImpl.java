package it.unibo.tnk23.game.model.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.api.Bonus;
import it.unibo.tnk23.game.components.impl.BonusComponent;
import it.unibo.tnk23.game.components.impl.BulletComponent;
import it.unibo.tnk23.game.components.impl.BulletHealthComponent;
import it.unibo.tnk23.game.components.impl.CollisionComponent;
import it.unibo.tnk23.game.components.impl.EntitiesHealthComponent;
import it.unibo.tnk23.game.components.impl.GraphicComponent;
import it.unibo.tnk23.game.components.impl.PhysicsComponent;
import it.unibo.tnk23.game.components.impl.PlayerFireComponent;
import it.unibo.tnk23.game.components.impl.TemporaryHealthComponent;
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
    @SuppressFBWarnings(
        value = {
            "EI2"
        },
            justification = "The GameObjectFactoryImpl must store this parameter in order to use its methods."
    )
    public GameObjectFactoryImpl(final World world) {
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getEnemy(final Point2D pos) {
        final var enemy = new GameObjectImpl(GameObjectType.ENEMY, pos);
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
        final var player = new GameObjectImpl(GameObjectType.PLAYER, pos);
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
        final  var bullet = new GameObjectImpl(GameObjectType.BULLET, pos);
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
        final var wall = new GameObjectImpl(GameObjectType.WALL, pos);
        wall.addComponent(new CollisionComponent(wall, world));
        wall.addComponent(new GraphicComponent("wall"));
        return wall;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getDestroyableWall(final Point2D pos) {
        final var destroyableWall = new GameObjectImpl(GameObjectType.WALL, pos);
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
        final var twr = new GameObjectImpl(GameObjectType.TOWER, pos);
        twr.addComponent(new EntitiesHealthComponent(twr, world));
        twr.addComponent(new CollisionComponent(twr, world));
        twr.addComponent(new GraphicComponent("tower"));
        return twr;
    }

    @Override
    public GameObject getBonus(final Bonus bonus, final Point2D pos) {
        final var bonusObj = new GameObjectImpl(GameObjectType.BONUS, pos);
        bonusObj.addComponent(new BonusComponent(bonusObj, world, bonus));
        bonusObj.addComponent(new TemporaryHealthComponent(bonusObj, world, 7 * 1000));
        bonusObj.addComponent(new GraphicComponent("lifeBonus"));
        return bonusObj;
    }

    
}
