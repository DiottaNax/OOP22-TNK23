package it.unibo.tnk23.game.graph.impl;

import java.util.List;
import java.util.stream.Stream;

import it.unibo.tnk23.common.Pair;

/**
 * The {@code GridGraphNode} class represents a node in a grid-based graph.
 */
public class GridGraphNode {

    private final Pair<Integer, Integer> gridPos;

    /**
     * Constructs a new {@code GridGraphNode} with the specified grid position.
     *
     * @param gridPos the grid position of the node
     */
    public GridGraphNode(final Pair<Integer, Integer> gridPos) {
        this.gridPos = gridPos;
    }

    /**
     * Retrieves the grid index of the node.
     *
     * @return the grid index of the node
     */
    public Pair<Integer, Integer> getGraphIndex() {
        return this.gridPos;
    }

    /**
     * Retrieves the list of adjacent grid indexes to the node.
     *
     * @return a list of adjacent grid indexes
     */
    public List<Pair<Integer, Integer>> getAdjacentIndexes() {
        return Stream.of(new Pair<>(1, 0), new Pair<>(0, 1))
                    .flatMap(p -> Stream.of(p, new Pair<>(-p.getX(), -p.getY())))
                    .map(p -> new Pair<>(gridPos.getX() + p.getX(), gridPos.getY() + p.getY()))
                    .toList();
    }

    /**
     * Checks if this node is equal to the specified object.
     *
     * @param obj the object to compare
     * @return {@code true} if the objects are equal, {@code false} otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        return this.gridPos.equals(((GridGraphNode) obj).gridPos);
    }

    /**
     * Computes the hash code of this node.
     *
     * @return the hash code of the node
     */
    @Override
    public int hashCode() {
        final int prime = 17;
        return prime * gridPos.hashCode();
    }

    /**
     * Returns a string representation of the node.
     *
     * @return a string representation of the node
     */
    @Override
    public String toString() {
        return "GridGraphNode [" + gridPos + "]";
    }
}

