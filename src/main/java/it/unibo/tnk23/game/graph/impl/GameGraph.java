package it.unibo.tnk23.game.graph.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.graph.api.VisitableGraphDecorator;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

public class GameGraph extends VisitableGraphDecorator<VisitableGridGraphNode> {
    public final static int PRECISION = 2;
    public final static int GRAPH_TILE_SIZE = Configuration.TILE_SIZE / PRECISION;
    private final static int UPDATE_PERIOD = Configuration.FPS * 4;

    private final VisitableGridGraph graph;
    private int currentFrame = UPDATE_PERIOD;
    private World world;
    public List<GameObject> obstacles;

    public GameGraph(VisitableGridGraph toDecorate) {
        super(toDecorate);
        this.graph = toDecorate;
        this.obstacles = new ArrayList<>();
    }

    public void setWorld(final World world) {
        this.world = world;
    }

    private Pair<Integer,Integer> getGraphPos(Point2D pos){
        double graphTileSize = (double) Configuration.getTileSize() / PRECISION;
        return new Pair<>((int) Math.round(pos.getX() / graphTileSize),
                (int) Math.round(pos.getY() / graphTileSize));
    }

    public void setGoal(Point2D goal) {
        this.graph.setGoal(new VisitableGridGraphNode(this.getGraphPos(goal)));
    }

    public List<Directions> getPathFrom(Point2D pos) {
        return this.graph.getPathFrom(new VisitableGridGraphNode(this.getGraphPos(pos)));
    }
    
    private Set<Pair<Integer,Integer>> getConnectedNodes(GameObject obst) {
        var graphPos = this.getGraphPos(obst.getPosition());
        return Stream.of(graphPos, new Pair<>(graphPos.getX() - 1, graphPos.getY()))
                .flatMap(p -> Stream.of(p, new Pair<>(p.getX(), p.getY() - 1))).collect(Collectors.toSet());
    }

    private void addObstacleToGraph(GameObject obst) {
        this.getConnectedNodes(obst).forEach(this.graph::removeNode);
    }

    private void removeObstacleFromGraph(GameObject obst) {
        this.getConnectedNodes(obst).forEach(this.graph::addNode);
    }

    public void update() {
        var worldObstacles = new ArrayList<>(world.getObstacles());
        if (currentFrame >= UPDATE_PERIOD) {

            this.obstacles.stream().filter(o -> !worldObstacles.contains(o)).toList().forEach(o -> {
                this.removeObstacleFromGraph(o);
                this.obstacles.remove(o);
            });

            worldObstacles.stream().filter(o -> !this.obstacles.contains(o)).forEach(o -> {
                this.addObstacleToGraph(o);
                this.obstacles.add(o);
            });

            currentFrame = 0;
        }
        
        currentFrame++;
    }

}
