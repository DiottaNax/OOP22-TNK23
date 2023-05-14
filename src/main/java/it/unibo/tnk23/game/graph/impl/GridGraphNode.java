package it.unibo.tnk23.game.graph.impl;

import java.util.List;
import java.util.stream.Stream;

import it.unibo.tnk23.common.Pair;

public class GridGraphNode {
    
    protected final Pair<Integer, Integer> gridPos;

    public GridGraphNode(Pair<Integer, Integer> gridPos) {
        this.gridPos = gridPos;
    }

    public Pair<Integer, Integer> getGraphIndex() {
        return this.gridPos;
    }
    
    public List<Pair<Integer,Integer>> getAdjacentIndexes(){
        return Stream.of(new Pair<>(1, 0), new Pair<>(0, 1))
                    .flatMap(p -> Stream.of(p, new Pair<>(-p.getX(), -p.getY())))
                    .map(p -> new Pair<>(gridPos.getX() + p.getX(), gridPos.getY() + p.getY()))
                    .toList();
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

    @Override
    public String toString() {
        return "GridGraphNode [" + gridPos + "]";
    }
}
