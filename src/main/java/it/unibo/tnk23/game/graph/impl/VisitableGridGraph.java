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
    private Map<VisitableGridGraphNode, VisitableGridGraphNode> graphNodes;
    private int gridSize;

    public VisitableGridGraph(int gridSize) {
        this.gridSize = gridSize;
        this.graph = new HashMap<>(gridSize * gridSize);
        this.graphNodes = new HashMap<>(gridSize * gridSize);
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
                .map(p -> new VisitableGridGraphNode(p))
                .forEach(n -> graphNodes.put(n, n));
        this.graphNodes.keySet().forEach(n -> this.graph.put(n, new HashSet<>()));

        this.addAdjacencies();
    }

    public void removeNode(Pair<Integer, Integer> node) {
        var toRemove = new VisitableGridGraphNode(node);
        this.graphNodes.remove(toRemove);
        this.graph.remove(toRemove);
    }

    public void addNode(Pair<Integer, Integer> node) {
        var toAdd = new VisitableGridGraphNode(node);
        if (!this.graphNodes.containsKey(toAdd)) {
            this.graphNodes.put(toAdd, toAdd);
            this.graph.put(toAdd,
                    toAdd.getAdjacentIndexes().stream().map(VisitableGridGraphNode::new)
                            .filter(this.graphNodes::containsKey).map(this.graphNodes::get)
                            .collect(Collectors.toCollection(HashSet::new)));
        }
    }

    public Set<VisitableGridGraphNode> getAdiacencies(final VisitableGridGraphNode node) {
        return Set.copyOf(this.graph.get(node));
    }
    
    private void addAdjacencies() {
        this.graph.entrySet().stream().parallel()
                .forEach(e -> e.getValue()
                        .addAll(e.getKey().getAdjacentIndexes().stream().map(VisitableGridGraphNode::new)
                                .filter(this.graphNodes::containsKey).map(this.graphNodes::get).toList()));
    }

    @Override
    public Set<VisitableGraphNode> getNodes() {
        return Collections.unmodifiableSet(this.graph.keySet());
    }

    @Override
    public List<Directions> getPathFrom(VisitableGraphNode node) {
        var path = new LinkedList<Directions>();
        var current = this.graphNodes.get(node);

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
                : (c.getX() < n.getX() ? Directions.EAST : Directions.WEST);
    }

    @Override
    public void setGoal(VisitableGraphNode goal) {
        this.graph.keySet().forEach(VisitableGridGraphNode::reset);
        var source = this.graphNodes.get(goal);
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
