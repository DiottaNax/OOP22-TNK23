package it.unibo.tnk23.game.graph.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.game.graph.api.VisitableGraph;

public class VisitableGridGraph implements VisitableGraph<VisitableGridGraphNode> {

    private Map<VisitableGridGraphNode, Set<VisitableGridGraphNode>> graph;
    private Map<VisitableGridGraphNode, VisitableGridGraphNode> graphNodes;
    private int gridSize;
    private VisitableGridGraphNode goal = new VisitableGridGraphNode(new Pair<>(-1, -1));

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

    public Set<VisitableGridGraphNode> getAdiacencies(final VisitableGridGraphNode node) {
        return Set.copyOf(this.graph.get(node));
    }

    private Stream<VisitableGridGraphNode> getAdjacentNodes(final VisitableGridGraphNode node){
        return node.getNode().getAdjacentIndexes().stream()
                .map(VisitableGridGraphNode::new)
                .filter(this.graphNodes::containsKey)
                .map(this.graphNodes::get);
    }
    
    private void addAdjacencies() {
        this.graph.entrySet().stream()
                .parallel()
                .forEach(e -> e.getValue().addAll(getAdjacentNodes(e.getKey()).toList()));
    }

    @Override
    public List<Directions> getPathFrom(final VisitableGridGraphNode node) {
        var path = new LinkedList<Directions>();
        if (this.graphNodes.containsKey(node)) {
            var current = this.graphNodes.get(node);

            while (current.getParent().isPresent()) {
                path.addLast(current.getDirectionToParent());
                current = (VisitableGridGraphNode) current.getParent().get();
            }
        }
        path.add(Directions.NONE);

        return path;
    }

    private Directions detectDirection(final VisitableGridGraphNode current, final VisitableGridGraphNode next) {
        var c = current.getNode().getGraphIndex();
        var n = next.getNode().getGraphIndex();
        return c.getX().equals(n.getX()) ? c.getY() < n.getY() ? Directions.SOUTH : Directions.NORTH
                : c.getX() < n.getX() ? Directions.EAST : Directions.WEST;
    }

    @Override
    public void setGoal(VisitableGridGraphNode goal) {
        if (!this.goal.equals(goal) && this.graph.containsKey(goal)) {
            //here starts bfs
            this.graph.keySet().forEach(VisitableGridGraphNode::reset);
            var source = this.graphNodes.get(goal);
            source.setDistance(0);
            Queue<VisitableGridGraphNode> q = new LinkedList<>();
            q.add(source);
            while (q.size() != 0) {
                var current = q.poll();

                this.graph.get(current).stream()
                        .filter(this.graph::containsKey)
                        .filter(n -> !n.isVisited())
                        .forEach(n -> {
                            n.setDistance(current.getDistance() + 1);
                            n.setParent(current);
                            n.setVisited();
                            n.setDirectionToParent(detectDirection(n, current));
                            q.add(n);
                        });

                current.setVisited();
            }
        }
    }

    @Override
    public Set<VisitableGridGraphNode> getAdjacencies(VisitableGridGraphNode node) {
        return this.graph.get(node);
    }

    public void removeNode(Pair<Integer, Integer> node) {
        this.removeNode(new VisitableGridGraphNode(node));
    }

    @Override
    public void removeNode(VisitableGridGraphNode node) {
        this.graphNodes.remove(node);
        this.graph.remove(node);
        this.getAdjacentNodes(node).forEach(n -> this.graph.get(n).remove(node));
    }

    @Override
    public Set<VisitableGridGraphNode> getNodes() {
        return this.graph.keySet();
    }

    public void addNode(Pair<Integer, Integer> node) {
        this.addNode(new VisitableGridGraphNode(node));
    }

    @Override
    public VisitableGridGraphNode addNode(VisitableGridGraphNode node) {
        if (!this.graphNodes.containsKey(node)) {
            this.graphNodes.put(node, node);
            var adjacentNodes = this.getAdjacentNodes(node).toList();
            this.graph.put(node, adjacentNodes.stream().collect(Collectors.toCollection(HashSet::new)));
            adjacentNodes.forEach(n -> {
                this.graph.get(n).remove(node);
                this.graph.get(n).add(node);
            });
            return node;
        }

        return this.graphNodes.get(node);
    }

}
