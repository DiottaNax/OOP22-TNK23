package it.unibo.tnk23.game.graph.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
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

        this.addAdjacencies();
    }

    public Set<VisitableGridGraphNode> getAdiacencies(final VisitableGridGraphNode node) {
        return Set.copyOf(this.graph.get(node));
    }
    
    private void addAdjacencies() {
        var keys = Set.copyOf(this.graph.keySet());
        for(var node : keys){
            var adjacencies = node.getAdjacentIndexes().stream().map(p -> new VisitableGridGraphNode(p)).toList();
            this.graph.get(node).addAll(this.graph.keySet().stream().filter(adjacencies::contains).toList());           
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
