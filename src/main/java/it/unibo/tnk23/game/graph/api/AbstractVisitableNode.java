package it.unibo.tnk23.game.graph.api;

import java.util.Optional;

/**
 * An abstract implementation of the VisitableNode interface.
 *
 * @param <N> the type of the node
 */
public abstract class AbstractVisitableNode<N> implements VisitableNode<N> {
    private final N node;
    private boolean visited;
    private Optional<VisitableNode<N>> parent;
    private int distance;

    /**
     * Constructs an AbstractVisitableNode with the specified node.
     *
     * @param node the node
     */
    public AbstractVisitableNode(final N node) {
        this.node = node;
        // No call to this.reset due to a PMD error
        this.visited = false;
        this.parent = Optional.empty();
        this.distance = -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public N getNode() {
        return this.node;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVisited() {
        return this.visited;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisited() {
        this.visited = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<VisitableNode<N>> getParent() {
        return this.parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParent(final VisitableNode<N> parent) {
        this.parent = Optional.of(parent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDistance() {
        return this.distance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDistance(final int distance) {
        this.distance = distance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.visited = false;
        this.parent = Optional.empty();
        this.distance = -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.node.toString();
    }
}

