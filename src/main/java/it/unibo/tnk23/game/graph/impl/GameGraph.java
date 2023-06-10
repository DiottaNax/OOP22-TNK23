package it.unibo.tnk23.game.graph.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.graph.api.VisitableGraphDecorator;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

/**
 * The {@code GameGraph} class represents a graph specifically designed for the game environment.
 * It extends the functionality of the underlying graph implementation by adding game-specific features.
 */
public class GameGraph extends VisitableGraphDecorator<VisitableGridGraphNode> {
    private static final int PRECISION = 2;
    private static final int UPDATE_PERIOD = Configuration.FPS * 4;
    /**
     * The size of a {@code GameGraph}'s tile in pixel.
     */
    public static final int GRAPH_TILE_SIZE = Configuration.TILE_SIZE / PRECISION;

    private final List<GameObject> obstacles;
    private int currentFrame = UPDATE_PERIOD;
    private World world;

    /**
     * Creates a new {@code GameGraph} instance with the specified underlying graph implementation.
     *
     * @param toDecorate the underlying graph implementation to decorate
     */
    public GameGraph(final VisitableGridGraph toDecorate) {
        super(toDecorate);
        this.obstacles = new ArrayList<>();
    }

    /**
     * Sets the game world for the graph.
     *
     * @param world the game world to set
     */
    @SuppressFBWarnings(
        value = {
            "EI2"
        },
        justification = "The World should change even in the graph if modified externally."
    )
    public void setWorld(final World world) {
        this.world = world;
    }

    /**
     * Returns the position in the graph of the point passed.
     * 
     * @param pos the position to convert
     * @return the position converted
     */
    private Pair<Integer, Integer> getGraphPos(final Point2D pos) {
        final double graphTileSize = (double) Configuration.TILE_SIZE / PRECISION;
        return new Pair<>((int) Math.round(pos.getX() / graphTileSize),
                (int) Math.round(pos.getY() / graphTileSize));
    }

    /**
     * Sets the goal position to set for pathfinding.
     *
     * @param goal the goal position to set
     */
    public void setGoal(final Point2D goal) {
        this.setGoal(new VisitableGridGraphNode(this.getGraphPos(goal)));
    }

    /**
     * Retrieves the path from the given position.
     *
     * @param pos the starting position
     * @return the list of directions representing the path to the goal
     */
    public List<Directions> getPathFrom(final Point2D pos) {
        return this.getPathFrom(new VisitableGridGraphNode(this.getGraphPos(pos)));
    }

    private Set<Pair<Integer, Integer>> getConnectedNodes(final GameObject obst) {
        /**
         * When a wall is added or removed from the graph even some adjacent positions are conditioned.
         * This function retrieves and returns the adjacent positions that should be modified after a changing to the graph.
         */
        final var graphPos = this.getGraphPos(obst.getPosition());
        return Stream.of(graphPos, new Pair<>(graphPos.getX() - 1, graphPos.getY()))
                .flatMap(p -> Stream.of(p, new Pair<>(p.getX(), p.getY() - 1))).collect(Collectors.toSet());
    }

    private void addObstacleToGraph(final GameObject obst) {
        this.getConnectedNodes(obst).forEach(o -> this.removeNode(new VisitableGridGraphNode(o)));
    }

    private void removeObstacleFromGraph(final GameObject obst) {
        this.getConnectedNodes(obst).forEach(o -> this.addNode(new VisitableGridGraphNode(o)));
    }

    /**
     * Updates the graph state by adding or removing obstacles based on changes in the game world.
     */
    public void update() {
        if (Objects.nonNull(this.world)) {
            final var worldObstacles = new ArrayList<>(world.getObstacles());
            if (currentFrame >= UPDATE_PERIOD) {

                this.obstacles.stream().filter(o -> !worldObstacles.contains(o)).toList().forEach(o -> {
                    this.removeObstacleFromGraph(o);
                    this.obstacles.remove(o);
                });

                worldObstacles.stream().filter(o -> !this.obstacles.contains(o)).forEach(o -> {
                    this.addObstacleToGraph(o);
                    this.obstacles.add(o);
                });

                currentFrame = 0;
            }

            currentFrame++;
            }
    }

}

