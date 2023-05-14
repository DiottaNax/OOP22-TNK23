package it.unibo.game.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.game.graph.impl.VisitableGridGraph;
import it.unibo.tnk23.game.graph.impl.VisitableGridGraphNode;

public class VisitableGraphTest {

    private final VisitableGridGraph graph = new VisitableGridGraph(3);
    private final VisitableGridGraphNode central = new VisitableGridGraphNode(new Pair<>(1, 1));
    private final VisitableGridGraphNode upperLeft = new VisitableGridGraphNode(new Pair<>(0, 0));
    private final VisitableGridGraphNode bottomRight = new VisitableGridGraphNode(new Pair<>(2, 2));


    @Test
    public void testGetNodes() {
        final int graphSize = graph.getNodes().size();
        assertEquals(graphSize, 9);
        assertTrue(this.graph.getNodes().contains(central));
        assertTrue(this.graph.getNodes().contains(upperLeft));
        assertTrue(this.graph.getNodes().contains(bottomRight));
    }

    @Test
    public void testGetAdiacencies() {
        var expectedSizes = List.of(2, 3, 2, 3, 4, 3, 2, 3, 2);
        var actualSizes = this.graph.getNodes().stream()
                .map(n -> graph.getAdiacencies((VisitableGridGraphNode) n))
                .map(l -> l.size()).toList();
        assertEquals(expectedSizes.size(), actualSizes.size());
        assertTrue(expectedSizes.containsAll(actualSizes));

        var adiacencies = this.graph.getAdiacencies(upperLeft);
        var expectedAdjacencies = new HashSet<>(
                List.of(new VisitableGridGraphNode(new Pair<>(0, 1)), new VisitableGridGraphNode(new Pair<>(1, 0))));
        assertEquals(adiacencies, expectedAdjacencies);

        expectedAdjacencies.addAll(
                List.of(new VisitableGridGraphNode(new Pair<>(2, 1)), new VisitableGridGraphNode(new Pair<>(1, 2))));
        adiacencies = this.graph.getAdiacencies(central);
        assertEquals(adiacencies, expectedAdjacencies);

        expectedAdjacencies.removeAll(
                List.of(new VisitableGridGraphNode(new Pair<>(0, 1)), new VisitableGridGraphNode(new Pair<>(1, 0))));
        adiacencies = this.graph.getAdiacencies(bottomRight);
        assertEquals(adiacencies, expectedAdjacencies);
    }
    
}
