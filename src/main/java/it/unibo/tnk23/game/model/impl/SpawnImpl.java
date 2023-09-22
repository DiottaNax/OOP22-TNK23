package it.unibo.tnk23.game.model.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.common.Stopwatch;
import it.unibo.tnk23.common.shape.Rect2D;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.Round;
import it.unibo.tnk23.game.model.api.Spawn;
import it.unibo.tnk23.game.components.impl.CollisionComponent;
import it.unibo.tnk23.game.events.api.GameEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;

/**
 * The SpawnImpl calss implments the functionality of spawning enemies during a game round.
 * It manages the spawning position, timing, and updates of enemies in the game world.
 */
public class SpawnImpl implements Spawn {

    private List<GameObject> roundEnemies;
    private final Round round;
    private final Stopwatch stopwatch;
    private final List<GameObject> activeEnemies;
    private final List<Rect2D> spawns;
    private final Random random = new Random();
    private static final long SPAWN_DELAY = 5000;

    /**
     * Constructs a new {@link SpawnImpl} object with the specified delay and game round.
     * 
     * @param round The game round.
     */
    public SpawnImpl(final Round round) {
        this.stopwatch = new Stopwatch();
        this.round = round;
        this.spawns = List.of(
                new Rect2D(Configuration.TILE_SIZE, Configuration.TILE_SIZE,
                        new Point2D(Configuration.TILE_SIZE / 2.0, Configuration.TILE_SIZE / 2.0)),
                new Rect2D(Configuration.TILE_SIZE, Configuration.TILE_SIZE,
                        new Point2D((Configuration.GRID_SIZE / 2.0) * Configuration.TILE_SIZE,
                                Configuration.TILE_SIZE / 2.0)),
                new Rect2D(Configuration.TILE_SIZE, Configuration.TILE_SIZE,
                        new Point2D((Configuration.GRID_SIZE - 1) * Configuration.TILE_SIZE,
                                Configuration.TILE_SIZE / 2.0)));
        this.activeEnemies = new ArrayList<>();
        this.roundEnemies = this.round.getEnemies();
    }

    /**
     * If there's a free spawn generates a WolrdEvent to spawn an enemy from roundEnemies list.
     */
    private void spawn() {
        if (!roundEnemies.isEmpty()) {
                    final var enemy = roundEnemies.get(0);
                    getSpawnPos().ifPresent(p -> {
                        roundEnemies.remove(0);
                        round.getWorld().notifyEvent(new WorldEventImpl(p, enemy, GameEventType.SPAWN_EVENT));
                        activeEnemies.add(enemy);
                    });
                }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startSpawn() {
        this.stopwatch.start();
    }

    /**
     * {@inheritDoc}
     * Updates the state of enemy spawns and removes defeated enemies from the list that contains the enemies.
     */
    @Override
    public void update() {
        if (this.roundEnemies.isEmpty() && this.round.isOver()) {
            this.roundEnemies = this.round.getEnemies();
        }

        /*
         * I have to use the synchronized block to avoid ConcurrentModificationException.
         */
        final var diedEnemies = new ArrayList<>(activeEnemies).stream()
            .filter(this::isEnemyDead).toList();
        this.round.getEnemies().removeAll(diedEnemies);
        activeEnemies.removeAll(diedEnemies);
        diedEnemies.forEach(d -> this.round.notifyEnemyDeath());

        if (stopwatch.getElapsedTime() >= SPAWN_DELAY) {
            this.stopwatch.restart();
            this.spawn();
        }
    }

    /**
     * Retrives a valid spawn position for an enemy.
     * 
     * @return An optional 'Point2D' representing the spawn position, or an empty optional if there isn't position is available.
     */
    private Optional<Point2D> getSpawnPos() {
        final var worldEnties = new HashSet<>(round.getWorld().getEntities());
        final var colidableEntities = worldEnties.stream().filter(e -> !e.getType().equals(GameObjectType.WALL))
                .filter(e -> e.getComponent(CollisionComponent.class).isPresent())
                .map(e -> e.getComponent(CollisionComponent.class).get()).toList();
        final List<Point2D> pos = spawns.stream().filter(s -> !colidableEntities.stream().anyMatch(c -> c.isCollidingWith(s)))
                .map(Rect2D::getPos).toList();

        return pos.isEmpty() ? Optional.empty() : Optional.of(pos.get(random.nextInt(pos.size())));
    }

    private boolean isEnemyDead(final GameObject enemy) {
        return !round.getWorld().getEntities().contains(enemy);
    }

}
