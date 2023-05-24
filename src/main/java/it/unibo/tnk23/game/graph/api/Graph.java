package it.unibo.tnk23.game.graph.api;

import java.util.Set;

public interface Graph<N> {

    Set<N> getNodes();

    Set<N> getAdjacencies(N node);

    N addNode(N node);

    void removeNode(N node);

}
