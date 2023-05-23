package it.unibo.tnk23.game.graph.impl;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.graph.api.VisitableGraphDecorator;
import it.unibo.tnk23.game.model.api.GameObject;

public class GameGraph extends VisitableGraphDecorator {
    private final VisitableGridGraph graph;
    private final static int PRECISION = 2;
    public final static int GRAPH_TILE_SIZE = Configuration.TILE_SIZE / PRECISION;

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
    
    private void performToGraph(GameObject obst, Consumer<Pair<Integer, Integer>> action) {
        final var obstH = Math.round(obst.getType().getheight() * Configuration.SCALE_FACTOR);
        final var obstW = Math.round(obst.getType().getWidth() * Configuration.SCALE_FACTOR);
        final int xTiles = obstW / GRAPH_TILE_SIZE;
        final int yTiles = obstH / GRAPH_TILE_SIZE;
        var graphPos = this.getGraphPos(obst.getPosition());
        graphPos = new Pair<>(graphPos.getX() - 1, graphPos.getY() - 1);
        for (int i = 0; i < yTiles; i++) {
            for (int j = 0; j < xTiles; j++) {
                action.accept(new Pair<>(j, i));
            }
        }
    }

    public void addObstacleToGraph(GameObject obst) {
        this.performToGraph(obst, p -> this.graph.removeNode(p));
    }

    public void removeObstacleToGraph(GameObject obst) {
        this.performToGraph(obst, p -> this.graph.removeNode(p));
    }

}
