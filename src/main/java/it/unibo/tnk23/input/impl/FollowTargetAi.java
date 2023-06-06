package it.unibo.tnk23.input.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.game.graph.impl.GameGraph;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.input.api.InputController;

public class FollowTargetAi implements InputController {

    private final static long UPDATE_PERIOD = 2000;
    private List<Directions> path = Collections.synchronizedList(new ArrayList<>());
    private Iterator<Directions> iterator;
    private final GameGraph graph;
    private final GameObject entity;
    private final GameObject target;
    private boolean timeToUpdate = true;
    private final Timer timer;

    public FollowTargetAi(GameGraph graph, GameObject entity, GameObject target) {
        this.graph = graph;
        this.entity = entity;
        this.target = target;
        this.timer = new Timer();
        this.startUpdate();
    }

    public void startUpdate() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeToUpdate = true;
            }
        }, 0, UPDATE_PERIOD);
    }

    @Override
    public Directions getDirection() {
        if (timeToUpdate) {
            this.graph.setGoal(target.getPosition());
            path.clear();
            this.path.addAll(this.graph.getPathFrom(entity.getPosition()));
            if (this.path.stream().allMatch(d -> d.equals(Directions.NONE))) {
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
