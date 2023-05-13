package it.unibo.tnk23.game.graph.api;

import java.util.List;
import java.util.Queue;
import java.util.Set;

import it.unibo.tnk23.common.Directions;

public abstract class VisitableGraphDecorator extends GraphDecorator<VisitableGraphNode> implements VisitableGraph{
    private final VisitableGraph toDecorate;
    
    public VisitableGraphDecorator(VisitableGraph toDecorate) {
        super(toDecorate);
        this.toDecorate = toDecorate;
    }

    @Override
    public void setGoal(VisitableGraphNode goal) {
        this.toDecorate.setGoal(goal);
    }

    @Override
    public List<Directions> getPathFrom(VisitableGraphNode node) {
        return this.toDecorate.getPathFrom(node);
    }
}
