package it.unibo.tnk23.game.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.game.graph.impl.GridGraphNode;

import java.util.List;

/**
 * Unit tests for the {@link GridGraphNode} class.
 */
class GridGraphNodeTest {

    /**
     * Tests the {@link GridGraphNode#getGraphIndex()} method.
     */
    @Test
    void testGetGraphIndex() {
        // Create a grid position pair (2, 3)
        final Pair<Integer, Integer> gridPos = new Pair<>(2, 3);

        // Create a GridGraphNode with the grid position
        final GridGraphNode node = new GridGraphNode(gridPos);

        // Assert that the graph index is the same as the grid position
        Assertions.assertEquals(gridPos, node.getGraphIndex());
    }

    /**
     * Tests the {@link GridGraphNode#getAdjacentIndexes()} method.
     */
    @Test
    void testGetAdjacentIndexes() {
        // Create a grid position pair (2, 3)
        final Pair<Integer, Integer> gridPos = new Pair<>(2, 3);

        // Create a GridGraphNode with the grid position
        final GridGraphNode node = new GridGraphNode(gridPos);

        // Get the list of adjacent indexes
        final List<Pair<Integer, Integer>> adjacentIndexes = node.getAdjacentIndexes();

        // Assert that the list contains the correct adjacent indexes
        Assertions.assertEquals(4, adjacentIndexes.size());
        Assertions.assertTrue(adjacentIndexes.contains(new Pair<>(3, 3)));
        Assertions.assertTrue(adjacentIndexes.contains(new Pair<>(2, 4)));
        Assertions.assertTrue(adjacentIndexes.contains(new Pair<>(1, 3)));
        Assertions.assertTrue(adjacentIndexes.contains(new Pair<>(2, 2)));
    }

    /**
     * Tests the {@link GridGraphNode#equals(Object)} and {@link GridGraphNode#hashCode()} methods.
     */
    @Test
    void testEqualsAndHashCode() {
        // Create two grid position pairs (2, 3)
        final Pair<Integer, Integer> gridPos1 = new Pair<>(2, 3);
        final Pair<Integer, Integer> gridPos2 = new Pair<>(2, 3);

        // Create two GridGraphNode objects with the same grid position
        final GridGraphNode node1 = new GridGraphNode(gridPos1);
        final GridGraphNode node2 = new GridGraphNode(gridPos2);

        // Assert that the nodes are equal and have the same hash code
        Assertions.assertEquals(node1, node2);
        Assertions.assertEquals(node1.hashCode(), node2.hashCode());
    }

    /**
     * Tests the {@link GridGraphNode#equals(Object)} and {@link GridGraphNode#hashCode()} methods for non-equal nodes.
     */
    @Test
    void testNotEquals() {
        // Create two grid position pairs (2, 3) and (4, 5)
        final Pair<Integer, Integer> gridPos1 = new Pair<>(2, 3);
        final Pair<Integer, Integer> gridPos2 = new Pair<>(4, 5);

        // Create two GridGraphNode objects with different grid positions
        final GridGraphNode node1 = new GridGraphNode(gridPos1);
        final GridGraphNode node2 = new GridGraphNode(gridPos2);
        final GridGraphNode node3 = new GridGraphNode(gridPos2);

        // Assert that the nodes are not equal and have different hash codes
        Assertions.assertNotEquals(node1, node2);
        Assertions.assertNotEquals(node1.hashCode(), node2.hashCode());
        Assertions.assertEquals(node3, node2);
        Assertions.assertEquals(node2.hashCode(), node3.hashCode());
    }
}

