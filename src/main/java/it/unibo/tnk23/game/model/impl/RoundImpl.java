package it.unibo.tnk23.game.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.Round;



public class RoundImpl implements Round{

    public List<GameObject> enemies = new ArrayList<>();
    int round;

    public RoundImpl() {
        round = 1;
        fillEnemiesList();
    }

    @Override
    public List<GameObject> getEnemies() {
        return this.enemies;
    }

    @Override
    public boolean isOver() {
        if (enemies.isEmpty()) {
            this.round ++;
            fillEnemiesList();
        }
        return enemies.isEmpty();
    }

    @Override
    public int getRound() {
        return this.round;
    }

    private void fillEnemiesList() {
        int numEnemies1 = 0;
        int numEnemies2 = 0;
        int numEnemies3 = 0;
        double rateNumEnemies2 = 2.0;
        double rateNumEnemies3 = 4.0;

        if (round==1) {
            numEnemies1 = 6;
        } else {
            numEnemies1 = round*3;
            numEnemies2 = (int) (round/rateNumEnemies2);
            numEnemies3 = (int) (round/rateNumEnemies3);
        }

        addSimpleEnemies(numEnemies1,/*nemico che si muove random*/);
        addMediumEnemies(numEnemies2,/*nemico che insegue*/);
        addHardEnemies(numEnemies3,/*nemico che punta alla torre*/);
    }

    private void addSimpleEnemies(int numEnemies, GameObject enemy) {
        Stream.iterate(0, i -> i+1)
              .limit(numEnemies)
              .forEach( i -> this.enemies.add(new /*nome classe nemico semplice*/));
    }

    private void addMediumEnemies(int numEnemies, GameObject enemy) {
        Stream.iterate(0, i -> i+1)
              .limit(numEnemies)
              .forEach( i -> this.enemies.add(new /*nome classe nemico medio*/));
    }

    private void addHardEnemies(int numEnemies, GameObject enemy) {
        Stream.iterate(0, i -> i+1)
              .limit(numEnemies)
              .forEach( i -> this.enemies.add(new /*nome classe nemico difficile*/));
    }
    
}
