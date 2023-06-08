package it.unibo.game.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.game.graph.impl.VisitableGridGraph;
import it.unibo.tnk23.game.graph.impl.VisitableGridGraphNode;

import java.util.List;
import java.util.Set;

class VisitableGridGraphTest {

    private VisitableGridGraph graph;
    private final int gridSize = 5;

    @BeforeEach
    void setUp() {
        // Create a new VisitableGridGraph with grid size 5
        graph = new VisitableGridGraph(gridSize);
    }

    @Test
    void testGetGridSize() {
        // Assert that the grid size is 5
        Assertions.assertEquals(gridSize, graph.getGridSize());
    }

    @Test
    void testAddAndGetNodes() {
        // Add nodes to the graph
        graph.addNode(new VisitableGridGraphNode(new Pair<>(0, 0)));
        graph.addNode(new VisitableGridGraphNode(new Pair<>(0, 1)));
        graph.addNode(new VisitableGridGraphNode(new Pair<>(1, 0)));

        // Get all nodes from the graph
        Set<VisitableGridGraphNode> nodes = graph.getNodes();

        // Assert that the graph contains the added nodes
        Assertions.assertEquals(gridSize * gridSize, nodes.size());
        Assertions.assertTrue(nodes.contains(new VisitableGridGraphNode(new Pair<>(0, 0))));
        Assertions.assertTrue(nodes.contains(new VisitableGridGraphNode(new Pair<>(0, 1))));
        Assertions.assertTrue(nodes.contains(new VisitableGridGraphNode(new Pair<>(1, 0))));
    }

    @Test
    void testAddExistingNode() {
        // Add a node to the graph
        VisitableGridGraphNode node = new VisitableGridGraphNode(new Pair<>(0, 0));
        graph.addNode(node);

        // Add the same node again
        VisitableGridGraphNode duplicateNode = new VisitableGridGraphNode(new Pair<>(0, 0));
        VisitableGridGraphNode result = graph.addNode(duplicateNode);

        // Assert that the result is the existing node and the graph size remains 1
        Assertions.assertEquals(node, result);
        Assertions.assertEquals(gridSize * gridSize, graph.getNodes().size());
    }

    @Test
    void testRemoveNode() {
        // Add nodes to the graph
        Set<VisitableGridGraphNode> nodes = graph.getNodes();
        VisitableGridGraphNode node1 = new VisitableGridGraphNode(new Pair<>(0, 0));
        VisitableGridGraphNode node2 = new VisitableGridGraphNode(new Pair<>(0, 1));
        graph.addNode(node1);
        graph.addNode(node2);

        // Assert that the graph contains the node1
        Assertions.assertEquals(gridSize * gridSize, nodes.size());

        // Remove node1 from the graph
        graph.removeNode(node1);
        // Assert that the graph does not contain the removed node
        Assertions.assertEquals(gridSize * gridSize - 1, nodes.size());
        Assertions.assertFalse(nodes.contains(node1));
        Assertions.assertTrue(nodes.contains(node2));
    }

    @Test
    void testGetAdjacencies() {
        // Add nodes to the graph
        VisitableGridGraphNode node1 = new VisitableGridGraphNode(new Pair<>(0, 0));
        VisitableGridGraphNode node2 = new VisitableGridGraphNode(new Pair<>(0, 1));
        VisitableGridGraphNode node3 = new VisitableGridGraphNode(new Pair<>(1, 0));

        // Get the adjacent nodes for node1
        Set<VisitableGridGraphNode> adjacencies = graph.getAdjacencies(node1);

        Assertions.assertEquals(Set.of(node2, node3), adjacencies);
        // Assert that the adjacent nodes are correct
        Assertions.assertEquals(2, adjacencies.size());
        Assertions.assertTrue(adjacencies.contains(node2));
    }

    @Test
    void testSetGoalAndGetPathFrom() {
        // Add nodes to the graph
        VisitableGridGraphNode node1 = new VisitableGridGraphNode(new Pair<>(0, 0));
        VisitableGridGraphNode node2 = new VisitableGridGraphNode(new Pair<>(0, 1));
        VisitableGridGraphNode node3 = new VisitableGridGraphNode(new Pair<>(0, 2));
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);

        // Set node1 as the goal
        graph.setGoal(node1);

        // Get the path from node3 to the goal
        List<Directions> path = graph.getPathFrom(node3);

        // Assert that the path is correct
        Assertions.assertEquals(3, path.size());
        Assertions.assertEquals(Directions.NORTH, path.get(0));
        Assertions.assertEquals(Directions.NORTH, path.get(1));
        Assertions.assertEquals(Directions.NONE, path.get(2));
    }
}

