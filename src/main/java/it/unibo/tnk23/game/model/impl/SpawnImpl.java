package it.unibo.tnk23.game.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.Round;
import it.unibo.tnk23.game.model.api.Spawn;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;

public class SpawnImpl implements Spawn{

    private Round round;
    private List<GameObject> roundEnemies;
    private List<GameObject> activeEnemies;
    private final int delay;
    private final Timer timer = new Timer();
    private final Random random = new Random();


    public SpawnImpl(final int delay, final Round round) {
        this.delay = delay;
        this.round = round;
        this.activeEnemies = Collections.synchronizedList(new ArrayList<>());
        this.roundEnemies = Collections.synchronizedList(this.round.getEnemies());
    }

    @Override
    public Point2D getPos() {
        List<Point2D> possibilePos = List.of(new Point2D(Configuration.TILE_SIZE / 2,Configuration.TILE_SIZE / 2),
            new Point2D((Configuration.GRID_SIZE / 2) * Configuration.TILE_SIZE,Configuration.TILE_SIZE / 2),
                new Point2D((Configuration.GRID_SIZE - 1) * Configuration.TILE_SIZE, Configuration.TILE_SIZE / 2)
        );
        return possibilePos.get(random.nextInt(possibilePos.size()));
    }

    @Override
    public void startSpawn() {
        this.timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (!roundEnemies.isEmpty()) {
                    var enemy = roundEnemies.get(0);
                    roundEnemies.remove(0);
                    round.getWorld().notifyEvent(new WorldEventImpl(getPos(), enemy, WorldEventType.SPAWN_EVENT));
                }
            } 
        }, this.delay, this.delay);
    }

    @Override
    public void update() {
        if (this.roundEnemies.isEmpty()) {
            this.timer.cancel();
        }
        this.round.getEnemies().removeIf(this::isEnemyDead);
        activeEnemies.removeIf(this::isEnemyDead);
    }
    
    private boolean isEnemyDead(final GameObject enemy) {
        return !round.getWorld().getEntities().contains(enemy);
    }
    
}
