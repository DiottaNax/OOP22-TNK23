package it.unibo.tnk23.game.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.common.Stopwatch;
import it.unibo.tnk23.common.shape.Rect2D;
import it.unibo.tnk23.game.components.api.Bonus;
import it.unibo.tnk23.game.components.impl.CollisionComponent;
import it.unibo.tnk23.game.components.impl.EntitiesHealthComponent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.BonusGenerator;
import it.unibo.tnk23.game.model.api.World;

public class BonusGeneratorImpl implements BonusGenerator {

    private final World world;
    private final long period;
    private final List<Bonus> bonusList;
    private final Stopwatch stopwatch;
    private boolean isRunning;

    public BonusGeneratorImpl(final long period, final World world) {
        this.period = period;
        this.world = world;
        this.bonusList = new ArrayList<>();
        bonusList
                .add(e -> e.getComponent(EntitiesHealthComponent.class).ifPresent(c -> c.setHealth(c.getHealth() + 1)));
        this.stopwatch = new Stopwatch();
    }

    @Override
    public void startGenerate() {
        isRunning = true;
        stopwatch.restart();     
    }

    @Override
    public void stopGenerate() {
        isRunning = false;
        stopwatch.stop();
    }

    private Point2D findSpot() {
        final Rect2D hitbox = new Rect2D(GameObjectTypeManager.getBulletType().getHeight(), null);
        final Random random = new Random();
        Point2D pos;
        do{
            pos = new Point2D(random.nextInt(Configuration.MAP_SIZE), random.nextInt(Configuration.MAP_SIZE));
            hitbox.setPos(pos);
        } while (this.world.getObstacles().stream().anyMatch(o -> o.getComponent(CollisionComponent.class).isPresent()
                && o.getComponent(CollisionComponent.class).get().isCollidingWith(hitbox)));
        
        return pos;
    }

    @Override
    public void update() {
        if (this.isRunning && stopwatch.getElapsedTime() >= this.period) {
            stopwatch.restart();
            final Point2D pos = this.findSpot();
            final var objFactory = new GameObjectFactoryImpl(world);
            Collections.shuffle(bonusList);
            this.world.notifyEvent(
                    new WorldEventImpl(pos, objFactory.getBonus(bonusList.get(0), pos), WorldEventType.SPAWN_EVENT));
        }
    }
    
    
}
