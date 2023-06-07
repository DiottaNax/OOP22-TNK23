package it.unibo.game.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.game.graph.api.VisitableNode;
import it.unibo.tnk23.game.graph.impl.GridGraphNode;
import it.unibo.tnk23.game.graph.impl.VisitableGridGraph;
import it.unibo.tnk23.game.graph.impl.VisitableGridGraphNode;

public class VisitableGraphTest {

    private final VisitableGridGraph graph = new VisitableGridGraph(3);
    private final VisitableGridGraphNode central = new VisitableGridGraphNode(new Pair<>(1, 1));
    private final VisitableGridGraphNode upperLeft = new VisitableGridGraphNode(new Pair<>(0, 0));
    private final VisitableGridGraphNode bottomRight = new VisitableGridGraphNode(new Pair<>(2, 2));

    @Test
    public void testVisitableGridGraphNodes() {
        final var mockGraph = new VisitableGridGraph(0);
        final var testNode1 = new VisitableGridGraphNode(new Pair<>(1, 1));
        final var testNode2 = new VisitableGridGraphNode(new GridGraphNode(new Pair<>(1, 1)));
        final VisitableNode<GridGraphNode> testNode3 = new VisitableGridGraphNode(testNode1.getNode());
        assertEquals(testNode1, new VisitableGridGraphNode(new Pair<>(1, 1)));
        assertEquals(testNode2, new VisitableGridGraphNode(new Pair<>(1, 1)));
        assertEquals(testNode3, new VisitableGridGraphNode(new Pair<>(1, 1)));
        mockGraph.addNode(testNode1);
        assertNotNull(mockGraph.getAdjacencies(new VisitableGridGraphNode(new Pair<>(1, 1))));
        assertNotNull(mockGraph.getAdjacencies(new VisitableGridGraphNode(new Pair<>(-1, -1))));
    }

    @Test
    public void testGetNodes() {
        final int graphSize = graph.getNodes().size();
        assertEquals(9,graphSize);
        assertTrue(this.graph.getNodes().contains(central));
        assertTrue(this.graph.getNodes().contains(upperLeft));
        assertTrue(this.graph.getNodes().contains(bottomRight));
    }

    @Test
    public void testgetAdjacencies() {
        final var nodes = this.graph.getNodes();
        var expectedSizes = List.of(2, 3, 2, 3, 4, 3, 2, 3, 2);
        var actualSizes = this.graph.getNodes().stream()
                        .map(n -> graph.getAdjacencies((VisitableGridGraphNode) n))
                        .map(l -> l.size()).toList();
        assertEquals(expectedSizes.size(), actualSizes.size());
        assertTrue(expectedSizes.containsAll(actualSizes));

        var adiacencies = this.graph.getAdjacencies(upperLeft);
        var expectedAdjacencies = new HashSet<>(
                    List.of(new VisitableGridGraphNode(new Pair<>(0, 1)),
                                            new VisitableGridGraphNode(new Pair<>(1, 0))));
        assertEquals(adiacencies, expectedAdjacencies);

        expectedAdjacencies.addAll(
                    List.of(new VisitableGridGraphNode(new Pair<>(2, 1)),
                                        new VisitableGridGraphNode(new Pair<>(1, 2))));
        adiacencies = this.graph.getAdjacencies(central);
        assertEquals(adiacencies, expectedAdjacencies);

        expectedAdjacencies.removeAll(
                        List.of(new VisitableGridGraphNode(new Pair<>(0, 1)),
                                    new VisitableGridGraphNode(new Pair<>(1, 0))));
        adiacencies = this.graph.getAdjacencies(bottomRight);
        assertEquals(adiacencies, expectedAdjacencies);

        assertTrue(nodes.stream()
                        .allMatch(n -> graph.getNodes()
                                        .containsAll(graph.getAdjacencies((VisitableGridGraphNode) n))));
    }
    
    @Test
    public void testBfs() {
        var expectedPath = List.of(Directions.SOUTH, Directions.SOUTH, Directions.EAST, Directions.EAST,
                        Directions.NONE);

        this.graph.setGoal(bottomRight);
        var actualPath = this.graph.getPathFrom(upperLeft);
        assertTrue(actualPath.containsAll(expectedPath));
        assertEquals(actualPath.size(), expectedPath.size());

        this.graph.setGoal(upperLeft);
        expectedPath = List.of(Directions.WEST, Directions.WEST, Directions.NORTH, Directions.NORTH, Directions.NONE);
        actualPath = this.graph.getPathFrom(bottomRight);
        assertTrue(actualPath.containsAll(expectedPath));
        assertEquals(actualPath.size(), expectedPath.size());
        
        this.graph.setGoal(central);
        expectedPath = List.of(Directions.SOUTH, Directions.EAST, Directions.NONE);
        actualPath = this.graph.getPathFrom(upperLeft);
        assertTrue(actualPath.containsAll(expectedPath));
        assertEquals(actualPath.size(), expectedPath.size());

        expectedPath = List.of(Directions.NORTH, Directions.WEST, Directions.NONE);
        actualPath = this.graph.getPathFrom(bottomRight);
        assertTrue(actualPath.containsAll(expectedPath));
        assertEquals(actualPath.size(), expectedPath.size());
    }
    
}
