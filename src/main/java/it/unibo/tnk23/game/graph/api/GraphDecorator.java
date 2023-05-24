package it.unibo.tnk23.game.graph.api;


public abstract class GraphDecorator<N> implements Graph<N> {
    protected final Graph<N> toDecorate;

    public GraphDecorator(Graph<N> toDecorate) {
        this.toDecorate = toDecorate;
    }
    
}
