package it.unibo.tnk23.game.graph.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.game.graph.api.VisitableGraph;
import it.unibo.tnk23.game.graph.api.VisitableGraphNode;

public class VisitableGridGraph implements VisitableGraph {

    private Map<VisitableGridGraphNode, Set<VisitableGridGraphNode>> graph;
    private int gridSize;

    public VisitableGridGraph(int gridSize) {
        this.gridSize = gridSize;
        this.graph = new HashMap<>(gridSize * gridSize);
        this.initGraph();
    }

    public int getGridSize() {
        return this.gridSize;
    }
    
    private void initGraph() {
        IntStream.range(0, gridSize)
                .mapToObj(x -> IntStream.range(0, gridSize)
                        .mapToObj(y -> new Pair<>(x, y)))
                .flatMap(p -> p)
                .forEach(p -> graph.put(new VisitableGridGraphNode(p), new HashSet<>()));
    }
    
    private void addAdjacencies() {
        var keys = Set.copyOf(this.graph.keySet());
        for(var node : this.graph.keySet()){
            var adjacents = Stream.of(1, -1).map(i -> new Pair<>(i, 0))
                    .flatMap(p -> Stream.of(p, new Pair<>(p.getY(), p.getX()))).map(p -> new GridGraphNode(p)).toList();
            
        }
    }

    @Override
    public Set<VisitableGraphNode> getNodes() {
        return Collections.unmodifiableSet(this.graph.keySet());
    }

    @Override
    public List<Directions> getPathFrom(VisitableGraphNode node) {
        var path = new LinkedList<Directions>();
        var current = node;

        while (current.getParent().isPresent()) {
            path.addLast(current.getDirectionToParent());
            current = current.getParent().get();
        }
        path.add(Directions.NONE);

        return path;
    }

    @Override
    public void setGoal(VisitableGraphNode goal) {
        
    }

}
