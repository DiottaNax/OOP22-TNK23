package it.unibo.tnk23.game.graph.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.game.graph.api.GraphNode;

public class GridGraphNode implements GraphNode{
    private final Pair<Integer, Integer> gridPos;
    private Set<GridGraphNode> adjSet;

    public GridGraphNode(Pair<Integer, Integer> gridPos) {
        this.gridPos = gridPos;
        this.adjSet = new HashSet<>();
    }

    public void addAdjacentNode(GridGraphNode node) {
        this.adjSet.add(node);
    }

    public void removeAdjacentNode(GridGraphNode node) {
        this.adjSet.remove(node);
    }

    @Override
    public Set<? extends GraphNode> getAdjacencyList() {
        return Collections.unmodifiableSet(this.adjSet);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        return this.gridPos.equals(((GridGraphNode) obj).gridPos);
    }

    @Override
    public int hashCode() {
        int prime = 17;
        return prime * gridPos.hashCode();
    }
}
