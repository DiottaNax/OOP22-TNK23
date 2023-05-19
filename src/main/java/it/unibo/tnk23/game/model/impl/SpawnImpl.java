package it.unibo.tnk23.game.model.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.Spawn;
import it.unibo.tnk23.game.world.api.World;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;

public class SpawnImpl implements Spawn{

    private RoundImpl roundImpl;
    private Iterator<GameObject> enemiesIterator;
    private Point2D pos;
    private final long delay;
    private final World world;

    private final Timer timer = new Timer();
    private final Random random = new Random();


    public SpawnImpl(final long delay, final World world) {
        this.delay = delay;
        this.world = world;
        enemiesIterator = roundImpl.getEnemies().iterator();
    }

    @Override
    public void spawnEnemies() {
        while(enemiesIterator.hasNext()) {
            notifySpawnEvent();
        }
        timer.cancel();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Point2D getPos() {
        return this.pos;
    }
    
    private void notifySpawnEvent() {
        this.timer.schedule(new TimerTask() {

            @Override
            public void run() {
                world.notifyEvent(new WorldEventImpl(generatePos(),enemiesIterator.next(),WorldEventType.SPAWN_EVENT));
            }
            
        }, delay);
    }
    
    private Point2D generatePos() {

        List<Point2D> possibilePos = List.of(new Point2D(0, 0), new Point2D(0, 0),new Point2D(0, 0));
        int randomPos;

        randomPos=random.nextInt(possibilePos.size());
        pos = possibilePos.get(randomPos);
        return pos;

    }

    private List<GameObject> getDiedEnemies() {
        return null;
    }
    
}
