package it.unibo.tnk23.game.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.impl.AiComponent;
import it.unibo.tnk23.game.components.impl.GraphicComponent;
import it.unibo.tnk23.game.graph.impl.GameGraph;
import it.unibo.tnk23.game.graph.impl.VisitableGridGraph;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.Round;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.input.api.AiControllerFactory;
import it.unibo.tnk23.input.impl.AiControllerFactoryImpl;



public class RoundImpl implements Round{

    private List<GameObject> enemies;
    private int totalEnemies = 0;
    private int round;
    private long spawnDelay;
    private World world;
    private int numRandomEnemies = 0;
    private int numFollowTargetEnemies = 0;
    private int numTowerEnemies = 0;
    private final SpawnImpl spawn;
    private final AiControllerFactory aiFactory;
    private final GameGraph graph;

    public RoundImpl(final World world) {
        this.round = 1;
        this.enemies = new ArrayList<>();
        this.world = world;
        this.setDelay();
        this.spawn = new SpawnImpl(this.spawnDelay, this);
        this.graph = new GameGraph(new VisitableGridGraph(Configuration.GRID_SIZE));
        this.aiFactory = new AiControllerFactoryImpl(this.graph, this.world);
        this.fillEnemiesList();
        this.totalEnemies = this.enemies.size();
        System.out.println(totalEnemies);
        System.out.println(enemies);
    }

    @Override
    public List<GameObject> getEnemies() {
        return this.enemies;
    }

    @Override
    public boolean isOver() {
        return this.totalEnemies == 0;
    }

    @Override
    public int getRound() {
        return this.round;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public int getRandomEnemiesNum() {
        return this.numRandomEnemies;
    }

    @Override
    public int getAIEnemiesNum() {
        return (this.numFollowTargetEnemies + this.numTowerEnemies);
    }

    @Override
    public void update() {
        this.spawn.update();
        this.graph.update();
        if (this.isOver()) {
            System.out.println("Fine round");
            this.round++;
            this.setDelay();
            this.fillEnemiesList();
            this.totalEnemies = this.enemies.size();
            System.out.println(totalEnemies);
            System.out.println(enemies);
            this.startRound();
            System.out.println("Inizia il round: " +this.round);
        }
    }
    
    @Override
    public void startRound() {
        this.spawn.startSpawn();
    }

    private void fillEnemiesList() {
        this.enemies.clear();
        numRandomEnemies = 0;
        numFollowTargetEnemies = 0;
        numTowerEnemies = 0;
        double rateFollowTargetEnemies = 2.0;
        double rateTowerEnemies = 4.0;

        if (round==1) {
            numRandomEnemies = 6;
        } else {
            numRandomEnemies = this.round*3;
            numFollowTargetEnemies = (int) (this.round/rateFollowTargetEnemies);
            numTowerEnemies = (int) (this.round / rateTowerEnemies);
        }

        addEnemies(numRandomEnemies, this::generateRandomMovingEnemies);
        addEnemies(numFollowTargetEnemies, this::generateFollowMovingTargetEnemies);
        //addEnemies(numTowerEnemies, this::generateFollowTowerEnemies);
    }

    private void setDelay() {
        final long simpleDelay = 4000;
        final long hardDelay = 2500;

        this.spawnDelay = simpleDelay;
        
        if(round >= 5) {
            this.spawnDelay = hardDelay;
        }
    }

    private GameObject generateRandomMovingEnemies() {
        var enemy = new GameObjectFactoryImpl(world).getEnemy(new Point2D(0, 0));
        enemy.addComponent(new AiComponent(enemy, aiFactory.getRandomAi()));
        enemy.addComponent(new GraphicComponent(enemy, "brownEnemy"));
        return enemy;
    }

    private GameObject generateFollowMovingTargetEnemies() {
        var enemy = new GameObjectFactoryImpl(world).getEnemy(new Point2D(0, 0));
        enemy.addComponent(new AiComponent(enemy,
                aiFactory.getFollowMovingTargetAi(this.world.getPlayer(1).get())));
        enemy.addComponent(new GraphicComponent(enemy, "greyEnemy"));
        return enemy;
    }
    
    /*private GameObject generateFollowTowerEnemies() {
        var enemy = new GameObjectFactoryImpl(world).getEnemy(new Point2D(0, 0));
        enemy.addComponent(new AiComponent(enemy, this.aiFactory.getFollowTowerAi()));
        enemy.addComponent(new GraphicComponent(enemy, "greyEnemy"));
        return enemy;
    }*/

    private void addEnemies(int numEnemies, Supplier<GameObject> enemyGenerator) {
        Stream.iterate(0, i -> i + 1)
                .limit(numEnemies)
                .forEach(i -> this.enemies.add(enemyGenerator.get()));
    }

    @Override
    public void notifyEnemyDeath() {
        System.out.println(totalEnemies);
        this.totalEnemies--;
    }

    
}
