package it.unibo.tnk23.input.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.graph.impl.GameGraph;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.input.api.InputController;

/**
 * The {@code FollowTargetAi} class implements the {@link InputController} interface
 * to provide an AI input controller that follows a target game object.
 * It uses a {@link GameGraph} to calculate the path to the target and provides the next direction to move.
 * 
 * @author Federico Diotallevi
 */
public class FollowTargetAi implements InputController {

    private final static long UPDATE_PERIOD = 2000;
    private final World world;
    private final InputController backupAi;
    private Point2D lastPos;
    private List<Directions> path = new ArrayList<>();
    private Iterator<Directions> iterator;
    private final GameGraph graph;
    private final GameObject entity;
    private final GameObject target;
    private boolean backupAiIsActive = false;
    private boolean timeToUpdate = true;
    private final Timer timer;

    /**
     * Constructs a new instance of {@code FollowTargetAi} with the specified game graph, entity, and target.
     *
     * @param graph  The game graph used for pathfinding.
     * @param entity The entity controlled by the AI.
     * @param target The target game object to follow.
     */
    public FollowTargetAi(GameGraph graph, GameObject entity, GameObject target, World world) {
        this.graph = graph;
        this.entity = entity;
        this.lastPos = entity.getPosition();
        this.target = target;
        this.world = world;
        this.backupAi = new AiControllerFactoryImpl(graph, world).getRandomAi();
        /*
         * The backup ai is useful if the principal ai loose path, either beacause the target died or because of an error.
         * If the target died this ai becomes a random one, otherwise it hangs around a bit to find an alternative path.
         */
        this.timer = new Timer();
        this.startUpdate();
    }

    /**
     * Starts the periodic update task to recalculate the path to the target.
     */
    public void startUpdate() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                var distance = path.size();
                if (!world.getEntities().contains(target) || entity.getPosition().equals(lastPos) && distance > 8) {
                    backupAiIsActive = true;
                } else {
                    backupAiIsActive = false;
                    timeToUpdate = true;
                }
            }
        }, 0, UPDATE_PERIOD);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Directions getDirection() {
        lastPos = this.entity.getPosition();
        if (timeToUpdate) {
            this.graph.setGoal(target.getPosition());
            path.clear();
            this.path.addAll(this.graph.getPathFrom(entity.getPosition()));
            if (this.path.stream().allMatch(d -> d.equals(Directions.NONE))) {
                backupAiIsActive = true;
            }
            iterator = this.path.iterator();
            timeToUpdate = false;
        }
        
        if (backupAiIsActive) {
            int backupSize = 12;
            this.path.clear();
            while (path.size() < backupSize) {
                Directions dir;
                do{
                    dir = backupAi.getDirection();
                } while (dir.equals(Directions.NONE));
                this.path.addAll(List.of(dir, dir, dir, dir, dir));
                /*
                 * The update period for this ai is very short, doing this it gets longer.
                 * Otherwise it would change direction every half tile.
                 */ 
                iterator = path.iterator();
            }
        }
        System.out.println("FOLLOWS TOWER? " + target.equals(world.getTower()));
        System.out.println("PATH: " + path);
        System.out.println("IS BACKUP ACTIVE? " + backupAiIsActive);
        return backupAiIsActive ? backupAi.getDirection()
                : iterator.hasNext() ? iterator.next() : Directions.NONE;
    }
}
