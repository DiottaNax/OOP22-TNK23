package it.unibo.tnk23.game.graph.api;

import java.util.Optional;

import it.unibo.tnk23.common.Directions;

public interface VisitableGraphNode {
    
    NodeColor getColor();

    void setColor(NodeColor color);

    Optional<VisitableGraphNode> getParent();

    void setParent(VisitableGraphNode parent);

    int getDistance();

    void setDistance(int distance);

    Directions getDirectionToParent();

    void setDirectionToParent(Directions dirToParent);

    void reset();

    public enum NodeColor {
        WHITE, BLACK, GREY;
    }
}
