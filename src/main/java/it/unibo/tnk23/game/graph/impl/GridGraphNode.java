package it.unibo.tnk23.game.graph.impl;

import it.unibo.tnk23.common.Pair;

public class GridGraphNode {
    
    protected final Pair<Integer, Integer> gridPos;

    public GridGraphNode(Pair<Integer, Integer> gridPos) {
        this.gridPos = gridPos;
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
