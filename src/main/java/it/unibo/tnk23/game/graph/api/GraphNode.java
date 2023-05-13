package it.unibo.tnk23.game.graph.api;

import java.util.Set;

public interface GraphNode {
    Set<? extends GraphNode> getAdjacencyList();
}
