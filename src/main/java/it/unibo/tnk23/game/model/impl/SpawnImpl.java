package it.unibo.tnk23.game.model.impl;

import java.util.Iterator;
import java.util.Random;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.Spawn;

public class SpawnImpl implements Spawn{

    private final long delay;
    private RoundImpl roundImpl;
    private Iterator<GameObject> enemiesIterator;

    private final Random random = new Random();

    public SpawnImpl(final long delay) {
        this.delay = delay;
        enemiesIterator = roundImpl.getEnemies().iterator();
    }

    @Override
    public void spawnEnemies() {
        startToGenerate();
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stop'");
    }

    private void startToGenerate() {
        if(enemiesIterator.hasNext()) {
            /*spawno tutti i miei nemici*/
            generatePos();

        } else {
            /*resetto il timer*/
        }
    }
    
    private Point2D generatePos() {

        Point2D cornerUpRX = new Point2D(0, 0);
        Point2D cornerUpSX = new Point2D(0, 0);
        Point2D centerUp = new Point2D(0, 0);
        int randomPos;
        final int NUM_OF_POS_SPAWN = 3;

        randomPos=random.nextInt(NUM_OF_POS_SPAWN)+1;
        
        if (randomPos == 1) {
            return cornerUpSX;
        } else if (randomPos == 2) {
            return centerUp;
        } else {
            return cornerUpRX;
        }

    }
}
