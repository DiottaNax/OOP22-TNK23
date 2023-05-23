package it.unibo.tnk23.game.graph.api;

import java.util.Set;

public abstract class GraphDecorator<X> implements Graph<X> {
    protected final Graph<X> toDecorate;

    public GraphDecorator(Graph<X> toDecorate) {
        this.toDecorate = toDecorate;
    }

    @Override
    public Set<X> getNodes() {
        return toDecorate.getNodes();
    }
    
}
