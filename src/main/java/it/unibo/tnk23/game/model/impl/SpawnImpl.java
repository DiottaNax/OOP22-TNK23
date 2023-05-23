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
import it.unibo.tnk23.game.model.api.Spawn;
import it.unibo.tnk23.game.world.api.World;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;

public class SpawnImpl implements Spawn{

    private RoundImpl roundImpl;
    private Iterator<GameObject> enemiesIterator;
    private List<GameObject> activeEnemies;
    private Point2D pos;
    private final long delay;
    private final World world;

    private final Timer timer = new Timer();
    private final Random random = new Random();


    public SpawnImpl(final long delay, final World world) {
        this.delay = delay;
        this.world = world;
        roundImpl = new RoundImpl(world);
        activeEnemies = new ArrayList<>();
        enemiesIterator = roundImpl.getEnemies().iterator();
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
        roundImpl.getEnemies().removeAll(getDiedEnemies());
    }

    @Override
    public Point2D getPos() {
        return this.pos;
    }
    
    private void start() {
        this.timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (enemiesIterator.hasNext()) {
                    activeEnemies.add(enemiesIterator.next());
                    world.notifyEvent(new WorldEventImpl(generatePos(), activeEnemies.get(activeEnemies.size() - 1),
                            WorldEventType.SPAWN_EVENT));
                }
            }
            
        }, delay);
    }
    
    private Point2D generatePos() {

        List<Point2D> possibilePos = List.of(new Point2D(0, 0),
                new Point2D((Configuration.GRID_SIZE / 2) * Configuration.TILE_SIZE, 0),
                new Point2D((Configuration.GRID_SIZE - 1) * Configuration.TILE_SIZE, 0));
        int randomPos;

        randomPos=random.nextInt(possibilePos.size());
        pos = possibilePos.get(randomPos);
        return pos;

    }

    private List<GameObject> getDiedEnemies() {
        var worldEnemies = world.getEntities();
        return activeEnemies.stream().filter(e -> !worldEnemies.contains(e)).toList(); //Mi da i nemici attiva che non sono più nel mondo 
    }
    
}
