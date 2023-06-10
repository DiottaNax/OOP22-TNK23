package it.unibo.tnk23.game.components.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.game.graph.impl.GameGraph;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.input.api.InputController;
import it.unibo.tnk23.input.impl.FollowTargetAi;

/**
 * The {@code AiComponent} class represents a component that controls the movement of a game object using an AI input controller.
 * It updates the movement direction of the entity at regular intervals based on the specified update period.
 * 
 * @author Federico Diotallevi
 */
public class AiComponent implements Component {

    private static final int DEAFULT_UPDATE_PERIOD = 2 * Configuration.FPS;
    private final int updatePeriod;
    private final GameObject entity;
    private final InputController ai;

    private int currentFrame;

    /**
     * Constructs a new instance of the {@code AiComponent} class with the specified entity and AI input controller.
     *
     * @param entity The game object associated with the AI component.
     * @param ai     The AI input controller used to control the entity's movement.
     */
    @SuppressFBWarnings(
        value = {
            "EI2"
        },
        justification = "The Ai component must store the entity in order to see its direction"
    )
    public AiComponent(final GameObject entity, final InputController ai) {
        this.entity = entity;
        this.ai = ai;
        /*
         * According to the equation of motion, x-x0 = vt, the frames to travel a tile are:
         * t = tileSize / speed, 
         * as graph tile are smaller we obtain:
         * t = tileSize / graphTile * tileSize / speed.
         */
        final var framesToTravelGraphTile = Configuration.TILE_SIZE / GameGraph.GRAPH_TILE_SIZE
                * (int) Math.round(GameGraph.GRAPH_TILE_SIZE / entity.getType().getSpeed());

        // The default update period is useful for a random AI, which needs to update slower
        this.updatePeriod = ai instanceof FollowTargetAi ? framesToTravelGraphTile : DEAFULT_UPDATE_PERIOD;

        this.currentFrame = updatePeriod;
    }

    private boolean canUpdate() {
        return currentFrame >= updatePeriod;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Updates the movement direction of the associated entity if the update conditions are met.
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

