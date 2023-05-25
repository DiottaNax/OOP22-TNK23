package it.unibo.tnk23.game.graph.api;

import java.util.List;
import java.util.Set;

import it.unibo.tnk23.common.Directions;

public abstract class VisitableGraphDecorator<N extends VisitableNode<?>> extends GraphDecorator<N> implements VisitableGraph<N>{
    private final VisitableGraph<N> toDecorate;
    
    public VisitableGraphDecorator(VisitableGraph<N> toDecorate) {
        super(toDecorate);
        this.toDecorate = toDecorate;
    }

    @Override
    public List<Directions> getPathFrom(N node) {
        return this.toDecorate.getPathFrom(node);
    }

    @Override
    public void setGoal(N goal) {
        this.setGoal(goal);
    }

    @Override
    public N addNode(N node) {
        return this.toDecorate.addNode(node);
    }

    @Override
    public Set<N> getAdjacencies(N node) {
        return this.toDecorate.getAdjacencies(node);
    }

    @Override
    public Set<N> getNodes() {
        return this.getNodes();
    }

    @Override
    public void removeNode(N node) {
        this.removeNode(node);
    }
    
}
