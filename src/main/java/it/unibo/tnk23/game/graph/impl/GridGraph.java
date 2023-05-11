package it.unibo.tnk23.game.graph.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.game.graph.api.Graph;
import it.unibo.tnk23.game.graph.api.GraphNode;

public class GridGraph implements Graph {
    private static final int PRECISION = 2;
    private Map<Pair<Integer, Integer>, GridGraphNode> graph;

    public GridGraph(int gridSize) {
        this.graph = new HashMap<>();
    }

    public void createNewGraph(int gridSize) {
        this.graph.clear();

        for (int i = 0; i < gridSize * PRECISION; i++) {
            for (int j = 0; j < gridSize * PRECISION; j++) {
                var gridPos = new Pair<>(j, i);
                this.graph.put(gridPos, new GridGraphNode(gridPos));
            }
        }

        this.graph.forEach((p, n) -> Stream.of(p.getX() - 1, p.getX() + 1)
                .flatMap(i -> Stream.of(new Pair<>(i, p.getY() - 1), new Pair<>(i, p.getX() + 1)))
                .map(pair -> new GridGraphNode(pair))
                .filter(this.graph::containsValue)
                .forEach(n::addAdjacentNode));
    }

    @Override
    public Set<? extends GraphNode> getNodes() {
        return this.graph.values().stream().collect(Collectors.toSet());
    }
}
