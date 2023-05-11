package it.unibo.tnk23.game.graph.api;

import java.util.Set;

public interface VisitableGraph extends Graph<VisitableGraphNode> {

    Set<VisitableGraphNode> getPathFrom(VisitableGraphNode node);

    void setGoal(VisitableGraphNode goal);

}
