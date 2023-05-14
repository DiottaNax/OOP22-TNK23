package it.unibo.tnk23.game.graph.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
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
        var current = (VisitableGridGraphNode) node;

        while (current.getParent().isPresent()) {
            path.addLast(current.getDirectionToParent());
            current = (VisitableGridGraphNode) current.getParent().get();
        }
        path.add(Directions.NONE);

        return path;
    }

    private Directions detectDirection(VisitableGridGraphNode current, VisitableGridGraphNode next) {
        var c = current.getGraphIndex();
        var n = next.getGraphIndex();
        return c.getX().equals(n.getX()) ? (c.getY() < n.getY() ? Directions.SOUTH : Directions.NORTH)
                : (c.getX() < n.getX() ? Directions.WEST : Directions.EAST);
    }

    @Override
    public void setGoal(VisitableGraphNode goal) {
        var nodes = this.graph.keySet().stream().collect(Collectors.toMap(n -> n, n -> n));

        this.graph.keySet().forEach(VisitableGridGraphNode::reset);
        var source = nodes.get(goal);
        source.setDistance(0);
        Queue<VisitableGridGraphNode> q = new LinkedList<>();
        q.add(source);
        while (q.size() != 0) {
            var current = q.poll();

            this.graph.get(current).stream()
                    .filter(n -> !n.isVisited())
                    .forEach(n -> {
                        n.setDistance(current.getDistance() + 1);
                        n.setParent(current);
                        n.setDirectionToParent(detectDirection(n, current));
                        q.add(n);
                    });
            
            current.setVisited();
        }
    }

}
