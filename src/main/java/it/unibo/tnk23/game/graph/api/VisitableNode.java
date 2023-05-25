package it.unibo.tnk23.game.graph.api;

import java.util.Optional;

public interface VisitableNode<N> {

    N getNode();

    boolean isVisited();

    void setVisited();

    Optional<VisitableNode<N>> getParent();

    void setParent(VisitableNode<N> parent);

    int getDistance();

    void setDistance(int distance);

    void reset();
}
