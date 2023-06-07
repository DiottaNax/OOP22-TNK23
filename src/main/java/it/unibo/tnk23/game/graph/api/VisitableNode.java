package it.unibo.tnk23.game.graph.api;

import java.util.Optional;

/**
 * The {@code VisitableNode} interface represents a node in a visitable graph.
 * It provides methods to track the node's visited state, parent node, distance, and allows resetting of its state.
 *
 * @param <N> the type of the node
 */
public interface VisitableNode<N> {

    /**
     * Retrieves the underlying node object.
     *
     * @return the underlying node object
     */
    N getNode();

    /**
     * Checks if the node has been visited.
     *
     * @return true if the node has been visited, false otherwise
     */
    boolean isVisited();

    /**
     * Sets the node as visited.
     */
    void setVisited();

    /**
     * Retrieves the parent node of the current node.
     *
     * @return an optional containing the parent node, or an empty optional if no parent is set
     */
    Optional<VisitableNode<N>> getParent();

    /**
     * Sets the parent node of the current node.
     *
     * @param parent the parent node to set
     */
    void setParent(VisitableNode<N> parent);

    /**
     * Retrieves the distance of the node from the goal.
     *
     * @return the distance of the node from the goal or {@code -1} if not reachable
     */
    int getDistance();

    /**
     * Sets the distance of the node from the goal.
     *
     * @param distance the distance to set
     */
    void setDistance(int distance);

    /**
     * Resets the state of the node, clearing visited state, parent node, and distance.
     */
    void reset();
}
