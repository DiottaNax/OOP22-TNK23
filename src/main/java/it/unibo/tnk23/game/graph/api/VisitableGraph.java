package it.unibo.tnk23.game.graph.api;

import java.util.List;

import it.unibo.tnk23.common.Directions;

/**
 * An extension of the Graph interface that represents a visitable graph.
 *
 * @param <N> the type of nodes in the graph, which must implement the VisitableNode interface
 */
public interface VisitableGraph<N extends VisitableNode<?>> extends Graph<N> {

    /**
     * Gets the path from the specified node to the goal node in the graph.
     *
     * @param node the starting node
     * @return the list of directions representing the path from the starting node to the goal node
     */
    List<Directions> getPathFrom(N node);

    /**
     * Sets the goal node for the graph.
     *
     * @param goal the goal node to set
     */
    void setGoal(N goal);
}

