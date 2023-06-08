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

/**
 * The SpawnImpl calss implments the functionality of spawning enemies during a game round.
 * It manages the spawning position, timing, and updates of enemies in the game world.
 */
public class SpawnImpl implements Spawn {

    private List<GameObject> roundEnemies;
    private final Round round;
    private final long delay;
    private final List<GameObject> activeEnemies;
    private final List<Rect2D> spawns;
    private final Timer timer = new Timer();
    private final Random random = new Random();
    private static final long SPAWN_DELAY = 5000;

    /**
     * Constructs a new {@link SpawnImpl} object with the specified delay and game round.
     * 
     * @param delay The delay between enemy spawns in milliseconds.
     * @param round The game round.
     */
    public SpawnImpl(final long delay, final Round round) {
        this.round = round;
        this.delay = delay;
        this.spawns = List.of(
                new Rect2D(Configuration.TILE_SIZE, Configuration.TILE_SIZE,
                        new Point2D(Configuration.TILE_SIZE / 2, Configuration.TILE_SIZE / 2)),
                new Rect2D(Configuration.TILE_SIZE, Configuration.TILE_SIZE,
                        new Point2D((Configuration.GRID_SIZE / 2) * Configuration.TILE_SIZE, Configuration.TILE_SIZE / 2)),
                new Rect2D(Configuration.TILE_SIZE, Configuration.TILE_SIZE,
                        new Point2D((Configuration.GRID_SIZE - 1) * Configuration.TILE_SIZE, Configuration.TILE_SIZE / 2))
        );
        this.activeEnemies = Collections.synchronizedList(new ArrayList<>());
        this.roundEnemies = Collections.synchronizedList(this.round.getEnemies());
        /*
         * created a synchronized list to avoid a ConcurrentModificationException
         * that occurred in run() nmethod of TimerTask.
         */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startSpawn() {
        this.timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (!roundEnemies.isEmpty()) {
                    final var enemy = roundEnemies.get(0);
                    getSpawnPos().ifPresent(p -> {
                        roundEnemies.remove(0);
                        round.getWorld().notifyEvent(new WorldEventImpl(p, enemy, WorldEventType.SPAWN_EVENT));
                        activeEnemies.add(enemy);
                    });
                }
            } 
        }, SPAWN_DELAY, this.delay);
    }

    /**
     * {@inheritDoc}
     * Updates the state of enemy spawns and removes defeated enemies from the list that contains the enemies.
     */
    @Override
    public void update() {
        if (this.roundEnemies.isEmpty() && this.round.isOver()) {
            this.timer.cancel();
            this.roundEnemies = Collections.synchronizedList(this.round.getEnemies());
        }
        /*
         * I have to use the synchronized block to avoid ConcurrentModificationException.
         */
        synchronized (activeEnemies) {
            final var diedEnemies = Collections.synchronizedList(new ArrayList<>(activeEnemies)).stream()
            .filter(this::isEnemyDead).toList();
            synchronized (diedEnemies) {
                this.round.getEnemies().removeAll(diedEnemies);
                activeEnemies.removeAll(diedEnemies);
                diedEnemies.forEach(d -> this.round.notifyEnemyDeath());
            }

        }

    }

    /**
     * Retrives a valid spawn position for an enemy.
     * 
     * @return An optional 'Point2D' representing the spawn position, or an empty optional if there isn't position is available.
     */
    private Optional<Point2D> getSpawnPos() {
        final var worldEnties = new HashSet<>(round.getWorld().getEntities());
        final var colidableEntities = worldEnties.stream().filter(e -> !TypeObjectFactory.isObstacle(e.getType()))
                .filter(e -> e.getComponent(CollisionComponent.class).isPresent())
                .map(e -> e.getComponent(CollisionComponent.class).get()).toList();
        final List<Point2D> pos = spawns.stream().filter(s -> !colidableEntities.stream().anyMatch(c -> c.isCollidingWith(s)))
                .map(Rect2D::getPos).toList();
        return pos.isEmpty() ? Optional.empty() : Optional.of(pos.get(random.nextInt(pos.size())));
    }

    private boolean isEnemyDead(final GameObject enemy) {
        synchronized (activeEnemies) {
            return !round.getWorld().getEntities().contains(enemy);
        }
    }

}
