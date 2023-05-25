package it.unibo.tnk23.game.graph.api;

import java.util.Optional;

public abstract class AbstractVisitableNode<N> implements VisitableNode<N> {
    private final N node;
    private boolean visited;
    private Optional<VisitableNode<N>> parent;
    private int distance;

    public AbstractVisitableNode(N node) {
        this.node = node;
        this.reset();
    }

    @Override
    public N getNode() {
        return this.node;
    }

    @Override
    public boolean isVisited() {
        return this.visited;
    }

    @Override
    public void setVisited() {
        this.visited = true;
    }

    @Override
    public Optional<VisitableNode<N>> getParent() {
        return this.parent;
    }

    @Override
    public void setParent(VisitableNode<N> parent) {
        this.parent = Optional.of(parent);
    }

    @Override
    public int getDistance() {
        return this.distance;
    }

    @Override
    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public void reset() {
        this.visited = false;
        this.parent = Optional.empty();
        this.distance = -1;
    }

    @Override
    public String toString() {
        return this.node.toString();
    }
    
}
