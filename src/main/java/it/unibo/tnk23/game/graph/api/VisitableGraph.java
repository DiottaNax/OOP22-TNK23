package it.unibo.tnk23.game.graph.api;

import java.util.List;

import it.unibo.tnk23.common.Directions;

public interface VisitableGraph<N extends VisitableNode<?>> extends Graph<N> {

    List<Directions> getPathFrom(N node);

    void setGoal(N goal);

}
