package it.unibo.tnk23.game.graph.impl;

import java.util.List;

import it.unibo.tnk23.common.Pair;

public class GridGraphNode {
    
    protected final Pair<Integer, Integer> gridPos;

    public GridGraphNode(Pair<Integer, Integer> gridPos) {
        this.gridPos = gridPos;
    }

    public Pair<Integer, Integer> getGraphIndex() {
        return this.gridPos;
    }
    
    public List<GridGraphNode> getAdjacentIndexes(){
        return Stream.of(1, -1).map(i -> new Pair<>(i, 0))
                    .flatMap(p -> Stream.of(p, new Pair<>(p.getY(), p.getX()))).map(p -> new GridGraphNode()).toList();
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
