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
import it.unibo.tnk23.game.world.api.World;
import it.unibo.tnk23.input.api.AiControllerFactory;
import it.unibo.tnk23.input.impl.AiControllerFactoryImpl;



public class RoundImpl implements Round{

    private List<GameObject> enemies = new ArrayList<>();
    private int round;
    private final SpawnImpl spawn;
    private final World world;
    private final AiControllerFactory aiFactory;
    private final GameGraph graph;

    public RoundImpl(final World world) {
        round = 1;
        spawn = new SpawnImpl(10, world); //Come faccio con delay?
        this.graph = new GameGraph(new VisitableGridGraph(Configuration.GRID_SIZE));
        this.aiFactory = new AiControllerFactoryImpl(graph);
        this.world = world;
        fillEnemiesList();
        setDelay();
    }

    @Override
    public List<GameObject> getEnemies() {
        return this.enemies;
    }

    @Override
    public boolean isOver() {
        return enemies.isEmpty();
    }

    @Override
    public int getRound() {
        return this.round;
    }

    @Override
    public void update() {
        spawn.update();
        if(this.isOver()) {
            this.round ++;
            fillEnemiesList();
        }
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
        final SpawnSettingsImpl spwnSettings;
        final long simpleDelay = 2500;
        final long mediumDelay = 2000;
        final long hardDelay = 1000;

        spwnSettings = new SpawnSettingsImpl(simpleDelay,world);
        
        if(round >= 5 && round < 10) {
            spwnSettings.setDelayOfSpawining(mediumDelay);
        } else if(round >= 10) {
            spwnSettings.setDelayOfSpawining(hardDelay);
        }
    }

    private GameObject generateRandomMovingEnemies() {
        var enemy = new GameObjectImpl(TypeObjectFactory.getEnemyType(), spawn.getPos());
        enemy.addComponent(new AiComponent(enemy, aiFactory.getRandomAi()));
        return enemy;
    }

    private GameObject generateFollowStillTargetEnemies() {
        var enemy = new GameObjectImpl(TypeObjectFactory.getEnemyType(), spawn.getPos());
        enemy.addComponent(new AiComponent(enemy, aiFactory.getFollowStillTargetAi(null)));
        return enemy;
    }

    private GameObject generateFollowMovingTargetEnemies() {
        var enemy = new GameObjectImpl(TypeObjectFactory.getEnemyType(), spawn.getPos());
        enemy.addComponent(new AiComponent(enemy, aiFactory.getFollowMovingTargetAi(null)));
        return enemy;
    }

    private void addEnemies(int numEnemies, Supplier<GameObject> enemyGenerator) {
        Stream.iterate(0, i -> i+1)
              .limit(numEnemies)
              .forEach( i -> this.enemies.add(enemyGenerator.get()));
    }
    
}
