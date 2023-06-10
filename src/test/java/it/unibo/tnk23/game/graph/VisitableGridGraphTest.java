package it.unibo.tnk23.game.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.game.graph.impl.VisitableGridGraph;
import it.unibo.tnk23.game.graph.impl.VisitableGridGraphNode;

import java.util.List;
import java.util.Set;

/**
 * Unit tests for the {@link VisitableGridGraph} class.
 */
class VisitableGridGraphTest {

    private final VisitableGridGraph graph = new VisitableGridGraph(GRID_SIZE);
    private final static int GRID_SIZE = 5;

    /**
     * Tests the {@link VisitableGridGraph#getGridSize()} method.
     */
    @Test
    void testGetGridSize() {
        // Assert that the grid size is 5
        Assertions.assertEquals(GRID_SIZE, graph.getGridSize());
    }

    /**
     * Tests the {@link VisitableGridGraph#addNode(VisitableGridGraphNode)} and {@link VisitableGridGraph#getNodes()} methods.
     */
    @Test
    void testAddAndGetNodes() {
        // Add nodes to the graph
        graph.addNode(new VisitableGridGraphNode(new Pair<>(0, 0)));
        graph.addNode(new VisitableGridGraphNode(new Pair<>(0, 1)));
        graph.addNode(new VisitableGridGraphNode(new Pair<>(1, 0)));

        // Get all nodes from the graph
        final Set<VisitableGridGraphNode> nodes = graph.getNodes();

        // Assert that the graph contains the added nodes
        Assertions.assertEquals(GRID_SIZE * GRID_SIZE, nodes.size());
        Assertions.assertTrue(nodes.contains(new VisitableGridGraphNode(new Pair<>(0, 0))));
        Assertions.assertTrue(nodes.contains(new VisitableGridGraphNode(new Pair<>(0, 1))));
        Assertions.assertTrue(nodes.contains(new VisitableGridGraphNode(new Pair<>(1, 0))));
    }

    /**
     * Tests adding an existing node to the graph.
     */
    @Test
    void testAddExistingNode() {
        // Add a node to the graph
        final VisitableGridGraphNode node = new VisitableGridGraphNode(new Pair<>(0, 0));
        graph.addNode(node);

        // Add the same node again
        final VisitableGridGraphNode duplicateNode = new VisitableGridGraphNode(new Pair<>(0, 0));
        final VisitableGridGraphNode result = graph.addNode(duplicateNode);

        // Assert that the result is the existing node and the graph size remains 1
        Assertions.assertEquals(node, result);
        Assertions.assertEquals(GRID_SIZE * GRID_SIZE, graph.getNodes().size());
    }

    /**
     * Tests removing a node from the graph.
     */
    @Test
    void testRemoveNode() {
        // Add nodes to the graph
        final Set<VisitableGridGraphNode> nodes = graph.getNodes();
        final VisitableGridGraphNode node1 = new VisitableGridGraphNode(new Pair<>(0, 0));
        final VisitableGridGraphNode node2 = new VisitableGridGraphNode(new Pair<>(0, 1));
        graph.addNode(node1);
        graph.addNode(node2);

        // Assert that the graph contains node1
        Assertions.assertEquals(GRID_SIZE * GRID_SIZE, nodes.size());

        // Remove node1 from the graph
        graph.removeNode(node1);

        // Assert that the graph does not contain the removed node
        Assertions.assertEquals(GRID_SIZE * GRID_SIZE - 1, nodes.size());
        Assertions.assertFalse(nodes.contains(node1));
        Assertions.assertTrue(nodes.contains(node2));
    }

    /**
     * Tests the {@link VisitableGridGraph#getAdjacencies(VisitableGridGraphNode)} method.
     */
    @Test
    void testGetAdjacencies() {
        // Add nodes to the graph
        final VisitableGridGraphNode node1 = new VisitableGridGraphNode(new Pair<>(0, 0));
        final VisitableGridGraphNode node2 = new VisitableGridGraphNode(new Pair<>(0, 1));
        final VisitableGridGraphNode node3 = new VisitableGridGraphNode(new Pair<>(1, 0));

        // Get the adjacent nodes for node1
        final Set<VisitableGridGraphNode> adjacencies = graph.getAdjacencies(node1);

        // Assert that the adjacent nodes are correct
        Assertions.assertEquals(Set.of(node2, node3), adjacencies);
        Assertions.assertEquals(2, adjacencies.size());
        Assertions.assertTrue(adjacencies.contains(node2));
    }

    /**
     * Tests setting the goal node and getting a path from a node to the goal.
     */
    @Test
    void testSetGoalAndGetPathFrom() {
        // Add nodes to the graph
        final VisitableGridGraphNode node1 = new VisitableGridGraphNode(new Pair<>(0, 0));
        final VisitableGridGraphNode node2 = new VisitableGridGraphNode(new Pair<>(0, 1));
        final VisitableGridGraphNode node3 = new VisitableGridGraphNode(new Pair<>(0, 2));
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);

        // Set node1 as the goal
        graph.setGoal(node1);

        // Get the path from node3 to the goal
        final List<Directions> path = graph.getPathFrom(node3);

        // Assert that the path is correct
        Assertions.assertEquals(3, path.size());
        Assertions.assertEquals(Directions.NORTH, path.get(0));
        Assertions.assertEquals(Directions.NORTH, path.get(1));
        Assertions.assertEquals(Directions.NONE, path.get(2));
    }
}
