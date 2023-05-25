package it.unibo.model.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.game.components.impl.CollisionComponent;
import it.unibo.tnk23.game.components.impl.HealthComponent;
import it.unibo.tnk23.game.components.impl.PlayerFireComponent;
import it.unibo.tnk23.game.model.api.GameObjectFactory;
import it.unibo.tnk23.game.model.impl.GameObjectFactoryImpl;

public class GameObjectFactoryTest {
    private final GameObjectFactory factory = new GameObjectFactoryImpl(null);

    @Test
    public void testGetPlayer() {
        var expectedComponentsClass = List.of(HealthComponent.class, PlayerFireComponent.class,
                CollisionComponent.class);
        var player = this.factory.getPlayer(null);
        assertEquals(3, player.getComponents().count());
        assertTrue(player.getComponents().map(Component::getClass)
                .allMatch(expectedComponentsClass::contains));
    }
}