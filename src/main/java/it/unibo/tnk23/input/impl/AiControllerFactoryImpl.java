package it.unibo.tnk23.input.impl;

import java.util.stream.Stream;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.game.graph.impl.GameGraph;
import it.unibo.tnk23.game.graph.impl.VisitableGridGraph;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.input.api.AiControllerFactory;
import it.unibo.tnk23.input.api.InputController;

public class AiControllerFactoryImpl implements AiControllerFactory{
    private final GameGraph graph;
    private final static int UPDATE_PERIOD = Configuration.FPS * 2;
    private int currentFrame = 0;

    public AiControllerFactoryImpl(GameGraph graph) {
        this.graph = graph;
    }

    @Override
    public InputController getRandomAi() {
        var iterator = Stream.iterate(Directions.SOUTH, d -> Directions.getRandomDir()).iterator();
        return () -> iterator.next();       
    }

    @Override
    public InputController getFollowStillTargetAi(GameObject target) {
        var iterator = this.graph.getPathFrom(target.getPosition()).iterator();
        return () -> iterator.hasNext() ? iterator.next() : Directions.NONE;
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