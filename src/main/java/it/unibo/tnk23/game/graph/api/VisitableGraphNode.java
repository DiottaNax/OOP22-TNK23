package it.unibo.tnk23.game.graph.api;

import java.util.Optional;

public interface VisitableGraphNode {

    boolean isVisited();

    void setVisited();

    Optional<VisitableGraphNode> getParent();

    void setParent(VisitableGraphNode parent);

    int getDistance();

    void setDistance(int distance);

    void reset();
}
