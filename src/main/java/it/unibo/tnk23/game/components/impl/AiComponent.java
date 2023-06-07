package it.unibo.tnk23.game.components.impl;

import java.util.Optional;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.game.graph.impl.GameGraph;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.input.api.InputController;
import it.unibo.tnk23.input.impl.FollowTargetAi;

public class AiComponent implements Component {
    
    private final static int DEAFULT_UPDATE_PERIOD = 2 * Configuration.FPS;
    private final int updatePeriod;
    private final GameObject entity;
    private final InputController ai;

    private int currentFrame;
    private Optional<Point2D> lastPos = Optional.empty();

    public AiComponent(GameObject entity, InputController ai) {
        this.entity = entity;
        this.ai = ai;
        var framesToTravelGraphTile = Configuration.TILE_SIZE / GameGraph.GRAPH_TILE_SIZE
                * (int) Math.round(GameGraph.GRAPH_TILE_SIZE / entity.getType().getSpeed());
        /*
         * According to the equation of motion, x-x0 = vt, the frames to travel a tile are:
         * t = tileSize / speed, as graph tile are smaller we obtain:
         * t = tileSize / graphTile * tileSize / speed.
         */
        this.updatePeriod = ai instanceof FollowTargetAi ? framesToTravelGraphTile : DEAFULT_UPDATE_PERIOD;
        //The default update period is useful for a random ai, which needs to update slower
        this.currentFrame = updatePeriod;
    }

    private boolean canUpdate() {
        if (lastPos.isPresent()) {
            var last = this.lastPos.get();
            var current = this.entity.getPosition();
            var travelled = Math
                    .abs(current.getX() == last.getX() ? current.getY() - last.getY() : current.getX() - last.getX());
            if (travelled >= GameGraph.GRAPH_TILE_SIZE) {
                this.lastPos = Optional.of(entity.getPosition());
                return true;
            }
        }

        return currentFrame >= updatePeriod;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.currentFrame++;

        if (this.canUpdate()) {
            this.entity.setDirection(ai.getDirection());
            this.currentFrame = 0;
        }
    }
    
}
