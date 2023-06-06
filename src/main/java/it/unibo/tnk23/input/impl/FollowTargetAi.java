package it.unibo.tnk23.input.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.game.graph.impl.GameGraph;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.input.api.InputController;

/**
 * The {@code FollowTargetAi} class implements the {@link InputController} interface
 * to provide an AI input controller that follows a target game object.
 * It uses a {@link GameGraph} to calculate the path to the target and provides the next direction to move.
 * 
 * @author Federico Diotallevi
 */
public class FollowTargetAi implements InputController {

    private final static long UPDATE_PERIOD = 2000;
    private List<Directions> path = new ArrayList<>();
    private Iterator<Directions> iterator;
    private final GameGraph graph;
    private final GameObject entity;
    private final GameObject target;
    private boolean timeToUpdate = true;
    private final Timer timer;

    /**
     * Constructs a new instance of {@code FollowTargetAi} with the specified game graph, entity, and target.
     *
     * @param graph  The game graph used for pathfinding.
     * @param entity The entity controlled by the AI.
     * @param target The target game object to follow.
     */
    public FollowTargetAi(GameGraph graph, GameObject entity, GameObject target) {
        this.graph = graph;
        this.entity = entity;
        this.target = target;
        this.timer = new Timer();
        this.startUpdate();
    }

    /**
     * Starts the periodic update task to recalculate the path to the target.
     */
    public void startUpdate() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeToUpdate = true;
            }
        }, 0, UPDATE_PERIOD);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Directions getDirection() {
        if (timeToUpdate) {
            this.graph.setGoal(target.getPosition());
            path.clear();
            this.path.addAll(this.graph.getPathFrom(entity.getPosition()));
            if (this.path.stream().allMatch(d -> d.equals(Directions.NONE))) {
                /*
                 * The enemy goes backwards if, due to some errors, it ends in a position not present in the game graph.
                 */
                for (int i = 0; i < 3; i++) {
                    this.path.add(Directions.fromAngle((int) entity.getRotation()).flipped());
                }
                path.add(Directions.NONE);
            }
            iterator = this.path.iterator();
            timeToUpdate = false;
        }

        return !iterator.hasNext() ? Directions.NONE : iterator.next();
    }
}
