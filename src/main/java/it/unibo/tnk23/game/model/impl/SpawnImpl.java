package it.unibo.tnk23.game.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.common.shape.Rect2D;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.Round;
import it.unibo.tnk23.game.model.api.Spawn;
import it.unibo.tnk23.game.components.impl.CollisionComponent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;

public class SpawnImpl implements Spawn{

    private Round round;
    private List<GameObject> roundEnemies;
    private List<GameObject> activeEnemies;
    private List<Rect2D> spawns;
    private final long delay;
    private final Timer timer = new Timer();
    private final Random random = new Random();
    private final static long SPAWN_DELAY = 5000;



    public SpawnImpl(final long delay, final Round round) {
        this.delay = delay;
        this.round = round;
        this.spawns = List.of(new Rect2D(Configuration.TILE_SIZE, Configuration.TILE_SIZE, new Point2D(Configuration.TILE_SIZE / 2, Configuration.TILE_SIZE / 2)),
                new Rect2D(Configuration.TILE_SIZE, Configuration.TILE_SIZE, new Point2D((Configuration.GRID_SIZE / 2) * Configuration.TILE_SIZE, Configuration.TILE_SIZE / 2)),
                new Rect2D(Configuration.TILE_SIZE, Configuration.TILE_SIZE, new Point2D((Configuration.GRID_SIZE - 1) * Configuration.TILE_SIZE, Configuration.TILE_SIZE / 2))
        );
        this.activeEnemies = Collections.synchronizedList(new ArrayList<>());
        this.roundEnemies = Collections.synchronizedList(this.round.getEnemies());
    }

    @Override
    public void startSpawn() {
        this.timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (!roundEnemies.isEmpty()) {
                    var enemy = roundEnemies.get(0);
                    getSpawnPos().ifPresent(p -> {
                        roundEnemies.remove(0);
                        round.getWorld().notifyEvent(new WorldEventImpl(p, enemy, WorldEventType.SPAWN_EVENT));
                        activeEnemies.add(enemy);
                    });
                }
            } 
        }, SPAWN_DELAY, this.delay);
    }

    @Override
    public void update() {
        if (this.roundEnemies.isEmpty() && this.round.isOver()) {
            this.timer.cancel();
            this.roundEnemies = Collections.synchronizedList(this.round.getEnemies());
        }
        synchronized (activeEnemies) {
            var diedEnemies = Collections.synchronizedList(new ArrayList<>(activeEnemies)).stream()
            .filter(this::isEnemyDead).toList();
            synchronized (diedEnemies) {
                this.round.getEnemies().removeAll(diedEnemies);
                activeEnemies.removeAll(diedEnemies);
                diedEnemies.forEach(d -> this.round.notifyEnemyDeath());
            }
            
        }
        
    }

    private Optional<Point2D> getSpawnPos() {
        var worldEnties = new HashSet<>(round.getWorld().getEntities());
        var colidableEntities = worldEnties.stream().filter(e -> !TypeObjectFactory.isObstacle(e.getType()))
                .filter(e -> e.getComponent(CollisionComponent.class).isPresent())
                .map(e -> e.getComponent(CollisionComponent.class).get()).toList();
        List<Point2D> pos = spawns.stream().filter(s -> !colidableEntities.stream().anyMatch(c -> c.isCollidingWith(s)))
                .map(Rect2D::getPos).toList();
        return pos.isEmpty() ? Optional.empty() : Optional.of(pos.get(random.nextInt(pos.size())));
    }
    
    private boolean isEnemyDead(final GameObject enemy) {
        synchronized (activeEnemies) {
            return !round.getWorld().getEntities().contains(enemy);
        }
    }
    
}
