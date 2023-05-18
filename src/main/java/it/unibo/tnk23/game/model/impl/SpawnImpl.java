package it.unibo.tnk23.game.model.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.Spawn;
import it.unibo.tnk23.game.model.api.TypeObject;
import it.unibo.tnk23.game.world.api.World;
import it.unibo.tnk23.game.world.impl.WorldImpl;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;

public class SpawnImpl implements Spawn{

    private final long delay;
    private final World world;
    private RoundImpl roundImpl;
    private Iterator<GameObject> enemiesIterator;

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
        /*if(enemiesIterator.hasNext()) {
            notifySpawnEvent();
        } else {
            timer.cancel();
        }*/
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
        
        return possibilePos.get(randomPos);

    } 
    
}
