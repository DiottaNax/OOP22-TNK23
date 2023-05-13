package it.unibo.tnk23.game.graph.api;

import java.util.List;
import java.util.Queue;

import it.unibo.tnk23.common.Directions;

public interface VisitableGraph extends Graph<VisitableGraphNode> {

    List<Directions> getPathFrom(VisitableGraphNode node);

    void setGoal(VisitableGraphNode goal);

}
