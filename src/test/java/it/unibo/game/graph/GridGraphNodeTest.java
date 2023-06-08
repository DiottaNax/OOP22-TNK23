package it.unibo.game.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.game.graph.impl.GridGraphNode;

import java.util.List;

class GridGraphNodeTest {

    @Test
    void testGetGraphIndex() {
        // Create a grid position pair (2, 3)
        Pair<Integer, Integer> gridPos = new Pair<>(2, 3);

        // Create a GridGraphNode with the grid position
        GridGraphNode node = new GridGraphNode(gridPos);

        // Assert that the graph index is the same as the grid position
        Assertions.assertEquals(gridPos, node.getGraphIndex());
    }

    @Test
    void testGetAdjacentIndexes() {
        // Create a grid position pair (2, 3)
        Pair<Integer, Integer> gridPos = new Pair<>(2, 3);

        // Create a GridGraphNode with the grid position
        GridGraphNode node = new GridGraphNode(gridPos);

        // Get the list of adjacent indexes
        List<Pair<Integer, Integer>> adjacentIndexes = node.getAdjacentIndexes();

        // Assert that the list contains the correct adjacent indexes
        Assertions.assertEquals(4, adjacentIndexes.size());
        Assertions.assertTrue(adjacentIndexes.contains(new Pair<>(3, 3)));
        Assertions.assertTrue(adjacentIndexes.contains(new Pair<>(2, 4)));
        Assertions.assertTrue(adjacentIndexes.contains(new Pair<>(1, 3)));
        Assertions.assertTrue(adjacentIndexes.contains(new Pair<>(2, 2)));
    }

    @Test
    void testEqualsAndHashCode() {
        // Create two grid position pairs (2, 3)
        Pair<Integer, Integer> gridPos1 = new Pair<>(2, 3);
        Pair<Integer, Integer> gridPos2 = new Pair<>(2, 3);

        // Create two GridGraphNode objects with the same grid position
        GridGraphNode node1 = new GridGraphNode(gridPos1);
        GridGraphNode node2 = new GridGraphNode(gridPos2);

        // Assert that the nodes are equal and have the same hash code
        Assertions.assertEquals(node1, node2);
        Assertions.assertEquals(node1.hashCode(), node2.hashCode());
    }

    @Test
    void testNotEquals() {
        // Create two grid position pairs (2, 3) and (4, 5)
        Pair<Integer, Integer> gridPos1 = new Pair<>(2, 3);
        Pair<Integer, Integer> gridPos2 = new Pair<>(4, 5);

        // Create two GridGraphNode objects with different grid positions
        GridGraphNode node1 = new GridGraphNode(gridPos1);
        GridGraphNode node2 = new GridGraphNode(gridPos2);
        GridGraphNode node3 = new GridGraphNode(gridPos2);

        // Assert that the nodes are not equal and have different hash codes
        Assertions.assertNotEquals(node1, node2);
        Assertions.assertNotEquals(node1.hashCode(), node2.hashCode());
        Assertions.assertEquals(node3, node2);
        Assertions.assertEquals(node2.hashCode(), node3.hashCode());
    }

    @Test
    void testToString() {
        // Create a grid position pair (2, 3)
        Pair<Integer, Integer> gridPos = new Pair<>(2, 3);

        // Create a GridGraphNode with the grid position
        GridGraphNode node = new GridGraphNode(gridPos);

        // Assert the string representation of the node
        Assertions.assertEquals("GridGraphNode [" + gridPos + "]", node.toString());
    }
}

