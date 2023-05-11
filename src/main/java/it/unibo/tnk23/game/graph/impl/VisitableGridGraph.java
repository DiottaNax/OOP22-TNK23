package it.unibo.tnk23.game.graph.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import it.unibo.tnk23.game.graph.api.VisitableGraph;
import it.unibo.tnk23.game.graph.api.VisitableGraphNode;

public class VisitableGridGraph implements VisitableGraph {

    private Map<VisitableGridGraphNode, Set<VisitableGridGraphNode>> graph;
    private int gridSize;

    public VisitableGridGraph(int gridSize) {
        this.gridSize = gridSize;
    }

    public int getGridSize(){
        return this.gridSize;
    }

    @Override
    public Set<VisitableGraphNode> getNodes() {
        return Collections.unmodifiableSet(this.graph.keySet());
    }

    @Override
    public Set<VisitableGraphNode> getPathFrom(VisitableGraphNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPathFrom'");
    }

    @Override
    public void setGoal(VisitableGraphNode goal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGoal'");
    }

}
