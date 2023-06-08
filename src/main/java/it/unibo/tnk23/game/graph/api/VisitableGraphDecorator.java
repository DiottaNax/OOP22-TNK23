package it.unibo.tnk23.game.graph.api;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.common.Directions;

/**
 * The {@code VisitableGraphDecorator} class is an abstract decorator for a visitable graph.
 * It provides additional functionality to the decorated graph by implementing the `VisitableGraph` interface.
 *
 * @param <N> the type of nodes in the graph
 */
public abstract class VisitableGraphDecorator<N extends VisitableNode<?>> extends GraphDecorator<N> implements VisitableGraph<N> {
    private final VisitableGraph<N> toDecorate;

    /**
     * Constructs a new {@code VisitableGraphDecorator} with the specified graph to decorate.
     *
     * @param toDecorate the graph to decorate
     */
    @SuppressFBWarnings(
        value = {
            "EI2"
        },
            justification = "The VisitableGraphDecorator must store the VisitableGraph to decorate in order to use its methods."
    )
    public VisitableGraphDecorator(final VisitableGraph<N> toDecorate) {
        super(toDecorate);
        this.toDecorate = toDecorate;
    }

    /**
     * Retrieves the path from the specified node to the goal node in the graph.
     *
     * @param node the starting node
     * @return a list of directions representing the path from the starting node to the goal node
     */
    @Override
    public List<Directions> getPathFrom(final N node) {
        return this.toDecorate.getPathFrom(node);
    }

    /**
     * Sets the goal node for the graph.
     *
     * @param goal the goal node to set
     */
    @Override
    public void setGoal(final N goal) {
        this.toDecorate.setGoal(goal);
    }
}

