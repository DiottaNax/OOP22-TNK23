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

/**
 * The {@code VisitableGridGraph} class represents a grid-based graph where nodes can be visited.
 */
public class VisitableGridGraph implements VisitableGraph<VisitableGridGraphNode> {

    private final Map<VisitableGridGraphNode, Set<VisitableGridGraphNode>> graph;
    private final Map<VisitableGridGraphNode, VisitableGridGraphNode> graphNodes;
    private final int gridSize;
    private final VisitableGridGraphNode goal = new VisitableGridGraphNode(new Pair<>(-1, -1));

    /**
     * Constructs a new {@code VisitableGridGraph} with the specified grid size.
     *
     * @param gridSize the size of the grid
     */
    public VisitableGridGraph(final int gridSize) {
        this.gridSize = gridSize;
        this.graph = new HashMap<>(gridSize * gridSize);
        this.graphNodes = new HashMap<>(gridSize * gridSize);
        this.initGraph();
    }

    /**
     * Retrieves the size of the grid.
     *
     * @return the grid size
     */
    public int getGridSize() {
        return this.gridSize;
    }

    /**
     * Initializes the graph by adding nodes and their adjacencies.
     */
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

    private Stream<VisitableGridGraphNode> getAdjacentNodes(final VisitableGridGraphNode node) {
        /**
         * Retrieves the graph nodes adjacent to the specified node based on their adjacent indexes.
         *
         * @param node the node
         * @return a stream of adjacent nodes
         */
        return node.getNode().getAdjacentIndexes().stream()
                .map(VisitableGridGraphNode::new)
                .filter(this.graphNodes::containsKey)
                .map(this.graphNodes::get);
    }

    private void addAdjacencies() {
        /**
         * Adds adjacencies to all nodes in the graph.
         */
        this.graph.entrySet().stream()
                .parallel()
                .forEach(e -> e.getValue().addAll(getAdjacentNodes(e.getKey()).toList()));
    }

    /**
     * Retrieves the path from the specified node to the goal node in the graph.
     *
     * @param node the starting node
     * @return a list of directions representing the path
     */
    @Override
    public List<Directions> getPathFrom(final VisitableGridGraphNode node) {
        final var path = new LinkedList<Directions>();
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
        /**
         * Detects the direction from the current node to the next node in the graph.
         */
        final var c = current.getNode().getGraphIndex();
        final var n = next.getNode().getGraphIndex();
        return c.getX().equals(n.getX()) ? c.getY() < n.getY() ? Directions.SOUTH : Directions.NORTH
                : c.getX() < n.getX() ? Directions.EAST : Directions.WEST;
    }

    /**
     * Sets the goal node in the graph.
     *
     * @param goal the goal node
     */
    @Override
    public void setGoal(final VisitableGridGraphNode goal) {
        if (!this.goal.equals(goal) && this.graph.containsKey(goal)) {
            // Here starts BFS
            this.graph.keySet().forEach(VisitableGridGraphNode::reset);
            final var source = this.graphNodes.get(goal);
            source.setDistance(0);
            final Queue<VisitableGridGraphNode> q = new LinkedList<>();
            q.add(source);
            while (q.size() != 0) {
                final var current = q.poll();

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

    /**
     * Retrieves the adjacent nodes of the specified node in the graph.
     *
     * @param node the node
     * @return a set of adjacent nodes or an empty set if the node is not present
     */
    @Override
    public Set<VisitableGridGraphNode> getAdjacencies(final VisitableGridGraphNode node) {
        return this.graph.containsKey(node) ? Set.copyOf(this.graph.get(node)) : Set.of();
    }

    /**
     * Removes the specified node from the graph.
     *
     * @param node the node to remove
     */
    @Override
    public void removeNode(final VisitableGridGraphNode node) {
        this.graphNodes.remove(node);
        this.graph.remove(node);
        this.getAdjacentNodes(node).forEach(n -> this.graph.get(n).remove(node));
    }

    /**
     * Retrieves all the nodes in the graph.
     *
     * @return a set of nodes
     */
    @Override
    public Set<VisitableGridGraphNode> getNodes() {
        return this.graph.keySet();
    }

    /**
     * Adds a new node to the graph.
     *
     * @param node the node to add
     * @return the added node or the existing equivalent node if already present in the graph
     */
    @Override
    public VisitableGridGraphNode addNode(final VisitableGridGraphNode node) {
        if (!this.graphNodes.containsKey(node)) {
            this.graphNodes.put(node, node);
            final var adjacentNodes = this.getAdjacentNodes(node).toList();
            this.graph.put(node, adjacentNodes.stream().collect(Collectors.toCollection(HashSet::new)));
            adjacentNodes.forEach(n -> {
                this.graph.get(n).remove(node);
                this.graph.get(n).add(node);
            });
            return node;
        }

        return this.graphNodes.get(node);
    }

    /**
     * Removes the specified node from the graph using its grid position.
     *
     * @param node the node to remove
     */
    public void removeNode(final Pair<Integer, Integer> node) {
        this.removeNode(new VisitableGridGraphNode(node));
    }

    /**
     * Adds a new node to the graph using its grid position.
     *
     * @param node the node to add
     */
    public void addNode(final Pair<Integer, Integer> node) {
        this.addNode(new VisitableGridGraphNode(node));
    }

}


