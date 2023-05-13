package it.unibo.tnk23.game.graph.impl;

import java.util.List;
import java.util.Queue;
import java.util.Set;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.graph.api.Graph;
import it.unibo.tnk23.game.graph.api.GraphDecorator;
import it.unibo.tnk23.game.graph.api.VisitableGraph;
import it.unibo.tnk23.game.graph.api.VisitableGraphDecorator;
import it.unibo.tnk23.game.graph.api.VisitableGraphNode;

public class GameGraph extends VisitableGraphDecorator {
    private final VisitableGridGraph graph;
    private final static int PRECISION = 2;

    public GameGraph(VisitableGridGraph toDecorate) {
        super(toDecorate);
        this.graph = toDecorate;
    }

    private Pair<Integer,Integer> getGraphPos(Point2D pos){
        double graphTileSize = Configuration.getTileSize() / PRECISION;
        return new Pair<>((int) Math.round(pos.getX() / graphTileSize),
                (int) Math.round(pos.getX() / graphTileSize));
    }

    public void setGoal(Point2D goal) {
        this.graph.setGoal(new VisitableGridGraphNode(this.getGraphPos(goal)));
    }

    public List<Directions> getPathFrom(Point2D pos) {
        return this.graph.getPathFrom(new VisitableGridGraphNode(this.getGraphPos(pos)));
    }
}
