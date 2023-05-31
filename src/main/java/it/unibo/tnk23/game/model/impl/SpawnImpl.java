package it.unibo.tnk23.game.model.impl;

import java.util.ArrayList;
import java.util.Iterator;
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
    private Iterator<GameObject> enemiesIterator;
    private List<GameObject> activeEnemies;
    private final long delay;

    private final Timer timer = new Timer();
    private final Random random = new Random();


    public SpawnImpl(final long delay, final Round round) {
        this.delay = delay;
        this.round = round;
        activeEnemies = new ArrayList<>();
        enemiesIterator = round.getEnemies().iterator();
    }

    @Override
    public void spawnEnemies() {
        start();
    }

    @Override
    public void update() {
        if (!enemiesIterator.hasNext()) {
            timer.cancel();
        }
        round.getEnemies().removeAll(getDiedEnemies());
    }

    @Override
    public Point2D getPos() {
        List<Point2D> possibilePos = List.of(new Point2D(15, 15),
                new Point2D((Configuration.GRID_SIZE / 2) * Configuration.TILE_SIZE, 15),
                new Point2D((Configuration.GRID_SIZE - 1) * Configuration.TILE_SIZE, 15));
        return possibilePos.get(random.nextInt(possibilePos.size()));
    }
    
    private void start() {
        this.timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (enemiesIterator.hasNext()) {
                    activeEnemies.add(enemiesIterator.next());
                    round.getWorld().notifyEvent(new WorldEventImpl(getPos(), activeEnemies.get(activeEnemies.size() - 1),
                            WorldEventType.SPAWN_EVENT));
                }
            } 
        }, delay);
    }
    
    private List<GameObject> getDiedEnemies() {
        var worldEnemies = round.getWorld().getEntities();
        return activeEnemies.stream().filter(e -> !worldEnemies.contains(e)).toList(); //Mi da i nemici attiva che non sono più nel mondo 
    }
    
}
