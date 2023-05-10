package it.unibo.tnk23.game.model.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.Spawn;

public class SpawnImpl implements Spawn{

    private final long delay;
    private RoundImpl roundImpl;
    private Iterator<GameObject> enemiesIterator;

    private final Timer timer = new Timer();
    private final Random random = new Random();


    public SpawnImpl(final long delay) {
        this.delay = delay;
        enemiesIterator = roundImpl.getEnemies().iterator();
    }

    @Override
    public void spawnEnemies() {
        if(enemiesIterator.hasNext()) {
            notifySpawnEvent(generatePos());
        } else {
            timer.cancel();
        }
    }
    
    private void notifySpawnEvent(Point2D pos) {
        
    }
    
    private Point2D generatePos() {

        List<Point2D> possibilePos = List.of(new Point2D(0, 0), new Point2D(0, 0),new Point2D(0, 0));
        int randomPos;

        randomPos=random.nextInt(possibilePos.size());
        
        return possibilePos.get(randomPos);

    } 
    
}
