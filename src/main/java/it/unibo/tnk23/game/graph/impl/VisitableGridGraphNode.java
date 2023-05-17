package it.unibo.tnk23.game.graph.impl;

import java.util.Optional;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.game.graph.api.VisitableGraphNode;

public class VisitableGridGraphNode extends GridGraphNode implements VisitableGraphNode {

    private boolean visited;
    private Optional<VisitableGraphNode> parent;
    private int distance;
    private Directions dirToParent;

    public VisitableGridGraphNode(Pair<Integer, Integer> gridPos) {
        super(gridPos);
        this.reset();
    }

    public VisitableGridGraphNode(GridGraphNode node) {
        super(node.gridPos);
        this.reset();
    }

    @Override
    public boolean isVisited() {
        return this.visited;
    }

    @Override
    public void setVisited() {
        this.visited = true;
    }

    @Override
    public Optional<VisitableGraphNode> getParent() {
        return this.parent;
    }

    @Override
    public void setParent(VisitableGraphNode parent) {
        this.parent = Optional.of(parent);
    }

    @Override
    public int getDistance() {
        return this.distance;
    }

    @Override
    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Directions getDirectionToParent() {
        return this.dirToParent;
    }

    public void setDirectionToParent(Directions dirToParent) {
        this.dirToParent = dirToParent;
    }

    @Override
    public void reset() {
        this.visited = false;
        this.parent = Optional.empty();
        this.distance = -1;
        this.dirToParent = Directions.NONE;
    }
    
}
