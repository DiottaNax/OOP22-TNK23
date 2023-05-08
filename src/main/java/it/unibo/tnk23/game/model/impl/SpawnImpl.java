package it.unibo.tnk23.game.model.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.api.Message;
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
            generateEnemies(generatePos());

        } else {
            /*resetto il timer*/
        }
    }

    private <X> Message<X> sendMessage() {
        return null;
    }
    
    private Point2D generatePos() {

        List<Point2D> possibilePos = List.of(new Point2D(0, 0), new Point2D(0, 0),new Point2D(0, 0));
        int randomPos;

        randomPos=random.nextInt(possibilePos.size())+1;
        
        return possibilePos.get(randomPos);

    }

    private void generateEnemies(Point2D pos) {
        sendMessage();
    }
}
