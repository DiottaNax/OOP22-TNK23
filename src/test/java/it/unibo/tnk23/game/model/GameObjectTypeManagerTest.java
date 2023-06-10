package it.unibo.tnk23.game.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.unibo.tnk23.game.model.api.GameObjectType;
import it.unibo.tnk23.game.model.impl.GameObjectTypeManager;

/**
 * Unit tests for the {@link GameObjectTypeManager} class.
 */
public class GameObjectTypeManagerTest {

    /**
     * Tests the {@link GameObjectTypeManager#getPlayerType()} method.
     */
    @Test
    public void testGetPlayer() {
        // Test the getPlayerType method

        final long health = 3;
        final long edges = 24;
        final double speed = 1.5;

        // Get the player game object type
        final GameObjectType player = GameObjectTypeManager.getPlayerType();

        // Assert that the player type has the expected properties
        assertEquals(health, player.getHealth());
        assertEquals(edges, player.getHeight());
        assertEquals(edges, player.getWidth());
        assertTrue(Double.compare(player.getSpeed(), speed) == 0);
    }

    /**
     * Tests the {@link GameObjectTypeManager#getEnemyType()} method.
     */
    @Test
    public void testGetEnemy() {
        // Test the getEnemyType method

        final long health = 1;
        final long edges = 24;
        final double speed = 1;

        // Get the enemy game object type
        final GameObjectType enemy = GameObjectTypeManager.getEnemyType();

        // Assert that the enemy type has the expected properties
        assertEquals(health, enemy.getHealth());
        assertEquals(edges, enemy.getHeight());
        assertEquals(edges, enemy.getWidth());
        assertTrue(Double.compare(enemy.getSpeed(), speed) == 0);
    }

    /**
     * Tests the {@link GameObjectTypeManager#getBulletType()} method.
     */
    @Test
    public void testGetBullet() {
        // Test the getBulletType method

        final long health = 1;
        final long edges = 24;
        final double speed = 5;

        // Get the bullet game object type
        final GameObjectType bullet = GameObjectTypeManager.getBulletType();

        // Assert that the bullet type has the expected properties
        assertEquals(health, bullet.getHealth());
        assertEquals(edges, bullet.getHeight());
        assertEquals(edges, bullet.getWidth());
        assertTrue(Double.compare(bullet.getSpeed(), speed) == 0);
    }

    /**
     * Tests the {@link GameObjectTypeManager#getObstacleType()} method.
     */
    @Test
    public void testGetObstacle() {
        // Test the getObstacleType method

        final long health = 1;
        final long edges = 24;
        final double speed = 0;

        // Get the obstacle game object type
        final GameObjectType obstacle = GameObjectTypeManager.getObstacleType();

        // Assert that the obstacle type has the expected properties
        assertEquals(health, obstacle.getHealth());
        assertEquals(edges, obstacle.getHeight());
        assertEquals(edges, obstacle.getWidth());
        assertTrue(Double.compare(obstacle.getSpeed(), speed) == 0);
    }
}
