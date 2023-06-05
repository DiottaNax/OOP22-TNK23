package it.unibo.tnk23.input.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.game.graph.impl.GameGraph;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.input.api.AiControllerFactory;
import it.unibo.tnk23.input.api.InputController;

public class AiControllerFactoryImpl implements AiControllerFactory{
    private final GameGraph graph;
    private final World world;
    private final static int UPDATE_PERIOD = Configuration.FPS * 2;
    private int currentFrame = 0;

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

    private InputController getFollowStillTargetAi(GameObject target) {
        var iterator = this.graph.getPathFrom(target.getPosition()).iterator();
        return () -> iterator.hasNext() ? iterator.next() : Directions.NONE;
    }

    @Override
    public InputController getFollowTowerAi() {
        return this.getFollowStillTargetAi(this.world.getTower());
    }

    private InputController getUpdatedFollowTargetAi(InputController ai, final GameObject target) {
        if (currentFrame >= UPDATE_PERIOD) {
            ai = getFollowStillTargetAi(target);
            currentFrame = 0;
        }

        return ai;
    }

    @Override
    public InputController getFollowMovingTargetAi(GameObject target) {
        var ai = this.getFollowStillTargetAi(target);
        return () -> (currentFrame++ < UPDATE_PERIOD) ? ai.getDirection()
                : getUpdatedFollowTargetAi(ai, target).getDirection();
    }
    
}
