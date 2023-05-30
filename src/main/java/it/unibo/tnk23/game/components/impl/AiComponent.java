package it.unibo.tnk23.game.components.impl;

import java.util.Optional;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.game.graph.impl.GameGraph;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.input.api.InputController;

public class AiComponent implements Component {
    
    private final static int DEAFULT_UPDATE_PERIOD = 2 * Configuration.FPS;
    private final int updatePeriod;
    private final GameObject entity;
    private final InputController ai;

    private int currentFrame;
    private Optional<Point2D> lastPos = Optional.empty();

    public AiComponent(GameObject entity, InputController ai, int updatePeriod) {
        this.entity = entity;
        this.ai = ai;
        this.updatePeriod = updatePeriod;
        this.currentFrame = updatePeriod;
    }

    public AiComponent(GameObject entity, InputController ai) {
        this(entity, ai, DEAFULT_UPDATE_PERIOD);
        this.lastPos = Optional.of(entity.getPosition());
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

    @Override
    public void update() {
        this.currentFrame++;

        if (this.canUpdate()) {
            this.entity.setDirection(ai.getDirection());
            this.currentFrame = 0;
        }
    }
    
}
