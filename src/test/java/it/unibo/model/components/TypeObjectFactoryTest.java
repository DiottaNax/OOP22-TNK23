package it.unibo.model.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.unibo.tnk23.game.model.api.GameObjectType;
import it.unibo.tnk23.game.model.impl.GameObjectTypeManager;

public class TypeObjectFactoryTest {
    
    @Test
    public void testGetPlayer() {
        final long health = 3;
        final long edges = 24;
        final double speed = 1.5;
        final GameObjectType player = GameObjectTypeManager.getPlayerType();

        assertEquals(health, player.getHealth());
        assertEquals(edges, player.getHeight());
        assertEquals(edges, player.getWidth());
        assertTrue(Double.compare(player.getSpeed(), speed) == 0); //NOPMD
        //assertEquals for double is deprecated
    }
    
    @Test
    public void testGetEnemy() {
        final long health = 1;
        final long edges = 24;
        final double speed = 1;
        final GameObjectType enemy = GameObjectTypeManager.getEnemyType();

        assertEquals(health, enemy.getHealth());
        assertEquals(edges, enemy.getHeight());
        assertEquals(edges, enemy.getWidth());
        assertTrue(Double.compare(enemy.getSpeed(), speed) == 0); //NOPMD
        //assertEquals for double is deprecated
    }
    
    @Test
    public void testGetBullet() {
        final long health = 1;
        final long edges = 6;
        final double speed = 5.0;
        final GameObjectType bullet = GameObjectTypeManager.getBulletType();

        assertEquals(health, bullet.getHealth());
        assertEquals(edges, bullet.getHeight());
        assertEquals(edges, bullet.getWidth());
        assertTrue(Double.compare(bullet.getSpeed(), speed) == 0); //NOPMD
        //assertEquals for double is deprecated
    }
    
    @Test
    public void testGetObstacle() {
        final long health = 1;
        final long edges = 12;
        final double speed = 0.0;
        final GameObjectType obstacle = GameObjectTypeManager.getObstacleType();

        assertEquals(health, obstacle.getHealth());
        assertEquals(edges, obstacle.getHeight());
        assertEquals(edges, obstacle.getWidth());
        assertTrue(Double.compare(obstacle.getSpeed(), speed) == 0); //NOPMD
        //assertEquals for double is deprecated        
    }
}
