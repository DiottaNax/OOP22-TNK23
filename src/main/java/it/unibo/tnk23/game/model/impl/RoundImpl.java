package it.unibo.tnk23.game.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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


/**
 * Implmentation of the Round interface representing a game round.
 * The RoundImpl class contains the implmentation of the logic taht drives the behavior and progrssion behind a game round.
 */
public class RoundImpl implements Round {

    private int totalEnemies;
    private int round;
    private int numRandomEnemies;
    private int numFollowTargetEnemies;
    private int numTowerEnemies;
    private final List<GameObject> enemies;
    private final World world;
    private final SpawnImpl spawn;
    private final AiControllerFactory aiFactory;
    private final GameGraph graph;
    private final Random random;

    /**
     * Constructs a new {@link RoundImpl} with the specified world.
     * 
     * @param world the game world associated with this round.
     */
    @SuppressFBWarnings(
        value = {
            "EI2"
        }, 
            justification = "RoundImpl must store the world in order to generate the enemies in the original world."
    )
    public RoundImpl(final World world) {
        this.totalEnemies = 0;
        this.round = 1;
        this.numRandomEnemies = 0;
        this.numFollowTargetEnemies = 0;
        this.numTowerEnemies = 0;
        this.enemies = new ArrayList<>();
        this.world = world;
        this.spawn = new SpawnImpl(this);
        this.graph = new GameGraph(new VisitableGridGraph(Configuration.GRID_SIZE * 2));
        this.aiFactory = new AiControllerFactoryImpl(this.graph, this.world);
        this.random = new Random();
        this.fillEnemiesList();
        this.totalEnemies = this.enemies.size();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(
        value = {
            "EI"
        }, 
            justification = "We need the original list of enemies because we are modifying it in SpawnImpl."
    )
    @Override
    public List<GameObject> getEnemies() {
        return this.enemies;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.totalEnemies == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRound() {
        return this.round;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(
        value = {
            "EI"
        }, 
            justification = "We need the original world that is passed to it from the constructor and not a copy of it."
    )
    @Override
    public World getWorld() {
        return this.world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRandomEnemiesNum() {
        return this.numRandomEnemies;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAIEnemiesNum() {
        return this.numFollowTargetEnemies + this.numTowerEnemies;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalEnemies() {
        return this.totalEnemies;
    }

    /**
     * {@inheritDoc}
     * This method is responsible for updating the round's spawning process, game graph, and checking if the round is over.
     */
    @Override
    public void update() {
        this.spawn.update();
        this.graph.update();
        if (this.isOver()) {
            this.round++;
            this.startRound();
            this.fillEnemiesList();
            this.totalEnemies = this.enemies.size();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startRound() {
        this.spawn.startSpawn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEnemyDeath() {
        this.totalEnemies--;
    }

    private void fillEnemiesList() {
        this.enemies.clear();
        numRandomEnemies = 0;
        numFollowTargetEnemies = 0;
        numTowerEnemies = 0;
        final int firstRoundRdmEnemies = 6;
        final double rateFollowTargetEnemies = 2.0;
        final int rateRandomEnemies = 3;

        if (round == 1) {
            numRandomEnemies = firstRoundRdmEnemies;
        } else {
            numRandomEnemies = this.round * rateRandomEnemies;
            numFollowTargetEnemies = (int) (this.round / rateFollowTargetEnemies);
            numTowerEnemies = (int) (this.round / rateFollowTargetEnemies);
        }

        addEnemies(numRandomEnemies, this::generateRandomMovingEnemies);
        addEnemies(numFollowTargetEnemies, this::generateFollowMovingTargetEnemies);
        addEnemies(numTowerEnemies, this::generateFollowTowerEnemies);
        Collections.shuffle(this.enemies);
    }

    private GameObject generateRandomMovingEnemies() {
        final var enemy = new GameObjectFactoryImpl(world).getEnemy(new Point2D(0, 0));
        enemy.addComponent(new AiComponent(enemy, aiFactory.getRandomAi()));
        enemy.addComponent(new GraphicComponent("brownEnemy"));
        return enemy;
    }

    private GameObject generateFollowMovingTargetEnemies() {
        final var enemy = new GameObjectFactoryImpl(world).getEnemy(new Point2D(0, 0));
        final var players = this.world.getPlayers().stream().filter(this.world.getEntities()::contains).toList();
        enemy.addComponent(new AiComponent(enemy,
                aiFactory.getFollowMovingTargetAi(enemy, players.get(this.random.nextInt(players.size())))));
        enemy.addComponent(new GraphicComponent("greyEnemy"));
        return enemy;
    }

    private GameObject generateFollowTowerEnemies() {
        final var enemy = new GameObjectFactoryImpl(world).getEnemy(new Point2D(0, 0));
        enemy.addComponent(new AiComponent(enemy, this.aiFactory.getFollowTowerAi(enemy)));
        enemy.addComponent(new GraphicComponent("greyEnemy"));
        return enemy;
    }

    private void addEnemies(final int numEnemies, final Supplier<GameObject> enemyGenerator) {
        Stream.iterate(0, i -> i + 1)
                .limit(numEnemies)
                .forEach(i -> this.enemies.add(enemyGenerator.get()));
    }
 
}
