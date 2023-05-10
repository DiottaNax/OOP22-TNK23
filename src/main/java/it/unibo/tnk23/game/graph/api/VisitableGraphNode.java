package it.unibo.tnk23.game.graph.api;

import java.util.Optional;

import it.unibo.tnk23.common.Directions;

public interface VisitableGraphNode {
    
    NodeColor getColor();

    Optional<? extends GraphNode> getParent();

    Optional<Integer> getDistance();

    Directions getDirectionToParent();

    public enum NodeColor {
        WHITE, BLACK, GREY;
    }
}
