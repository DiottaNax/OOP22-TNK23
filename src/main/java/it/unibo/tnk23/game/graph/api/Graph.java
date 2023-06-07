package it.unibo.tnk23.game.graph.api;

import java.util.Set;

/**
 * An interface representing a graph data structure.
 *
 * @param <N> the type of nodes in the graph
 */
public interface Graph<N> {

    /**
     * Retrieves a set of all nodes in the graph.
     *
     * @return a set of nodes
     */
    Set<N> getNodes();

    /**
     * Retrieves a set of adjacent nodes to the specified node.
     *
     * @param node the node to get the adjacencies for
     * @return a set of adjacent nodes
     */
    Set<N> getAdjacencies(N node);

    /**
     * Adds a node to the graph.
     *
     * @param node the node to add
     * @return the added node
     */
    N addNode(N node);

    /**
     * Removes a node from the graph.
     *
     * @param node the node to remove
     */
    void removeNode(N node);
}

