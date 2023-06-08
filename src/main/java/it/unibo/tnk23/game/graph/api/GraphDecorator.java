package it.unibo.tnk23.game.graph.api;

import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * An abstract class that serves as a base decorator for the Graph interface.
 *
 * @param <N> the type of nodes in the graph
 */
public abstract class GraphDecorator<N> implements Graph<N> {
    /**
     * The graph to decorate.
     */
    private final Graph<N> toDecorate;

    /**
     * Constructs a GraphDecorator object with the specified graph to decorate.
     *
     * @param toDecorate the graph to decorate
     */
    @SuppressFBWarnings(
        value = {
            "EI2"
        },
        justification = "The GraphDecorator must store the base graph in order to use its methods."
    )
    public GraphDecorator(final Graph<N> toDecorate) {
        this.toDecorate = toDecorate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<N> getNodes() {
        return this.toDecorate.getNodes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public N addNode(final N node) {
        return this.toDecorate.addNode(node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeNode(final N node) {
        this.toDecorate.removeNode(node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<N> getAdjacencies(final N node) {
        return this.toDecorate.getAdjacencies(node);
    }
}
