package it.unibo.model.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.unibo.tnk23.game.model.api.GameObjectType;
import it.unibo.tnk23.game.model.impl.GameObjectTypeManager;

public class TypeObjectFactoryTest {
    
    @Test
    public void testGetPlayer() {
        final long HEALTH = 3, EDGES = 24;
        final double SPEED = 1.5;
        final GameObjectType player = GameObjectTypeManager.getPlayerType();

        assertEquals(HEALTH, player.getHealth());
        assertEquals(EDGES, player.getHeight());
        assertEquals(EDGES, player.getWidth());
        assertTrue(Double.compare(player.getSpeed(), SPEED) == 0);
    }
    
    @Test
    public void testGetEnemy() {
        final long HEALTH = 1, EDGES = 24;
        final double SPEED = 1;
        final GameObjectType enemy = GameObjectTypeManager.getEnemyType();

        assertEquals(HEALTH, enemy.getHealth());
        assertEquals(EDGES, enemy.getHeight());
        assertEquals(EDGES, enemy.getWidth());
        assertTrue(Double.compare(enemy.getSpeed(), SPEED) == 0);
    }
    
    @Test
    public void testGetBullet() {
        final long HEALTH = 1, EDGES = 24;
        final double SPEED = 5;
        final GameObjectType bullet = GameObjectTypeManager.getBulletType();

        assertEquals(HEALTH, bullet.getHealth());
        assertEquals(EDGES, bullet.getHeight());
        assertEquals(EDGES, bullet.getWidth());
        assertTrue(Double.compare(bullet.getSpeed(), SPEED) == 0);
    }
    
    @Test
    public void testGetObstacle() {
        final long HEALTH = 1, EDGES = 24;
        final double SPEED = 0;
        final GameObjectType obstacle = GameObjectTypeManager.getObstacleType();

        assertEquals(HEALTH, obstacle.getHealth());
        assertEquals(EDGES, obstacle.getHeight());
        assertEquals(EDGES, obstacle.getWidth());
        assertTrue(Double.compare(obstacle.getSpeed(), SPEED) == 0);        
    }
}
