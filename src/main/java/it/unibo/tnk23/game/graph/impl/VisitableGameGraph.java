package it.unibo.tnk23.game.graph.impl;

import java.util.Set;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.graph.api.Graph;
import it.unibo.tnk23.game.graph.api.GraphDecorator;
import it.unibo.tnk23.game.graph.api.VisitableGraph;
import it.unibo.tnk23.game.graph.api.VisitableGraphNode;

public class VisitableGameGraph extends GraphDecorator<VisitableGraphNode> implements VisitableGraph{
    private int gridSize;
    private final VisitableGridGraph toDecorate = new VisitableGridGraph(gridSize * PRECISION);
    private final static int PRECISION = 2;

    private VisitableGameGraph(Graph<VisitableGraphNode> toDecorate) {
        super(toDecorate);
    }

    public VisitableGameGraph(int gridSize) {
        this((Graph<VisitableGraphNode>) );
        this.gridSize = gridSize;
    }

    public void setGoal(Point2D goal) {
        double graphTileSize = Configuration.getTileSize() / PRECISION;
        var graphPos = new Pair<>((int) (goal.getX() / graphTileSize), (int) (goal.getX() / graphTileSize));
    }

    @Override
    public Set<VisitableGraphNode> getPathFrom(VisitableGraphNode node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPathFrom'");
    }

    @Override
    public void setGoal(VisitableGraphNode goal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGoal'");
    }
    
}
