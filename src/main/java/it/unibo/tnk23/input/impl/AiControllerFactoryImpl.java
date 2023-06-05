package it.unibo.tnk23.input.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Stream;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.game.graph.impl.GameGraph;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.input.api.AiControllerFactory;
import it.unibo.tnk23.input.api.InputController;

public class AiControllerFactoryImpl implements AiControllerFactory {
    private final GameGraph graph;
    private final World world;

    public AiControllerFactoryImpl(final GameGraph graph, final World world) {
        this.graph = graph;
        this.graph.setWorld(world);
        this.world = world;
    }

    private Directions getPseudoRandomDir(Directions dir) {
        var possibilities = new ArrayList<>(
                List.of(Directions.NONE, Directions.SOUTH, Directions.WEST, Directions.EAST));
        possibilities.addAll(possibilities);
        possibilities.remove(dir);
        return possibilities.get(new Random().nextInt(possibilities.size()));
    }

    @Override
    public InputController getRandomAi() {
        var iterator = Stream.iterate(Directions.SOUTH, this::getPseudoRandomDir).iterator();
        return () -> iterator.next();       
    }

    private InputController getFollowStillTargetAi(GameObject entity, GameObject target) {
        this.graph.setGoal(target.getPosition());
        var path = this.graph.getPathFrom(entity.getPosition());
        var iterator = path.iterator();
        return () -> iterator.hasNext() ? iterator.next() : Directions.NONE;
    }

    @Override
    public InputController getFollowTowerAi(GameObject entity) {
        return this.getFollowStillTargetAi(entity, this.world.getTower());
    }

    @Override
    public InputController getFollowMovingTargetAi(GameObject entity, GameObject target) {
        return new FollowMovingTargetAi(graph, entity, target);
    }
    
}
