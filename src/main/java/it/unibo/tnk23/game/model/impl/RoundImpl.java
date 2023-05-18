package it.unibo.tnk23.game.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.components.impl.AiComponent;
import it.unibo.tnk23.game.graph.impl.GameGraph;
import it.unibo.tnk23.game.graph.impl.VisitableGridGraph;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.Round;
import it.unibo.tnk23.input.api.AiControllerFactory;
import it.unibo.tnk23.input.impl.AiControllerFactoryImpl;



public class RoundImpl implements Round{

    public List<GameObject> enemies = new ArrayList<>();
    public int round;
    private SpawnSettingsImpl spwnSettings;
    private final AiControllerFactory aiFactory;
    private final GameGraph graph;

    public RoundImpl() {
        round = 1;
        this.graph = new GameGraph(new VisitableGridGraph(Configuration.GRID_SIZE));
        this.aiFactory = new AiControllerFactoryImpl(graph);
        fillEnemiesList();
        setDelay();
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

        addEnemies(numEnemies1, this::generateRandomMovingEnemies);
        addEnemies(numEnemies2, this::generateFollowStillTargetEnemies);
        addEnemies(numEnemies3, this::generateFollowMovingTargetEnemies);
    }

    private void setDelay() {
        final long simpleDelay = 2500;
        final long mediumDelay = 2000;
        final long hardDelay = 1000;

        spwnSettings = new SpawnSettingsImpl(simpleDelay);
        
        if(round >= 5 && round < 10) {
            spwnSettings.setDelayOfSpawining(mediumDelay);
        } else if(round >= 10) {
            spwnSettings.setDelayOfSpawining(hardDelay);
        }
    }

    private GameObject generateRandomMovingEnemies() {
        var enemy = new GameObjectImpl(TypeObjectFactory.getEnemyType(), null);
        enemy.addComponent(new AiComponent(enemy, aiFactory.getRandomAi()));
        return enemy;
    }

    private GameObject generateFollowStillTargetEnemies() {
        var enemy = new GameObjectImpl(TypeObjectFactory.getEnemyType(), null);
        enemy.addComponent(new AiComponent(enemy, aiFactory.getFollowStillTargetAi(null)));
        return enemy;
    }

    private GameObject generateFollowMovingTargetEnemies() {
        var enemy = new GameObjectImpl(TypeObjectFactory.getEnemyType(), null);
        enemy.addComponent(new AiComponent(enemy, aiFactory.getFollowMovingTargetAi(null)));
        return enemy;
    }

    private void addEnemies(int numEnemies, Supplier<GameObject> enemyGenerator) {
        Stream.iterate(0, i -> i+1)
              .limit(numEnemies)
              .forEach( i -> this.enemies.add(enemyGenerator.get()));
    }
    
}
