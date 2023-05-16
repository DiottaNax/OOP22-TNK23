package it.unibo.tnk23.game.graph.api;

import java.util.Set;

public interface VisitableGraph {

    Set<? extends GraphNode> getPathFrom(GraphNode node);

}
