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
 * <p>The AI periodically updates the path to the target, and if the target is no longer present or 
 * the entity's position hasn't changed for a significant time, a backup AI is activated to provide 
 * alternative directions.
 * 
 * <p>The backup AI is provided by the {@link AiControllerFactoryImpl} class and is used in case the 
 * principal AI loses the path to the target. If the target is no longer present, the backup AI becomes 
 * a random AI, otherwise it hangs around a bit to find an alternative path.
 * 
 * <p>Note that the backup AI is only activated when necessary, and the principal AI is used whenever 
 * a valid path is available.
 * 
 * <p>Instances of this class should be created using the {@link AiControllerFactoryImpl} to ensure proper initialization.
 * 
 * @author Federico Diotallevi
 */
public class FollowTargetAi implements InputController {

    private static final long UPDATE_PERIOD = 2000;
    private final World world;
    private final InputController backupAi;
    private Point2D lastPos;
    private final List<Directions> path = new ArrayList<>();
    private Iterator<Directions> iterator;
    private final GameGraph graph;
    private final GameObject entity;
    private final GameObject target;
    private boolean backupAiIsActive;
    private boolean timeToUpdate = true;
    private final Timer timer;

    /**
     * Constructs a new instance of {@code FollowTargetAi} with the specified game graph, entity, target, and world.
     *
     * @param graph  The game graph used for pathfinding.
     * @param entity The entity controlled by the AI.
     * @param target The game object target to follow.
     * @param world  The game world.
     */
    public FollowTargetAi(final GameGraph graph, final GameObject entity, final GameObject target, final World world) {
        this.graph = graph;
        this.entity = entity;
        this.lastPos = entity.getPosition();
        this.target = target;
        this.world = world;
        /*
         * The backup ai is useful if the principal ai loose path, either beacause the target died or because of an error.
         * If the target died this ai becomes a random one, otherwise it hangs around a bit to make the graph find an alternative path.
         */
        this.backupAi = new AiControllerFactoryImpl(graph, world).getRandomAi();
        this.timer = new Timer();
        this.startUpdate();
    }

    /**
     * Starts the periodic update task to recalculate the path to the target.
     */
    private void startUpdate() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                final var distance = path.size();
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
     * If called it retrieves and returns path to the goal from the graph.
     */
    private void updatePath() {
        this.graph.setGoal(target.getPosition());
        path.clear();
        this.path.addAll(this.graph.getPathFrom(entity.getPosition()));

        if (this.path.stream().allMatch(d -> d.equals(Directions.NONE))) {
            backupAiIsActive = true;
        }

        this.iterator = this.path.iterator();
        this.backupAiIsActive = false;
        this.timeToUpdate = false;
    }

    /**
     * Should be called if the entity is stuck.
     * If called sets up a different path to follow using the backup ai.
     */
    private void setBackupPath() {
        final int backupSize = 15;
        this.path.clear();
        while (path.size() < backupSize) {
            Directions dir;
            do {
                dir = backupAi.getDirection();
                // If the backup ai is activated the entity was already still, there's no point in setting Direction.NONE.
            } while (dir.equals(Directions.NONE));
            
            /**
             * The update period for this ai is very short, doing this it gets longer,
             * otherwise it would change direction every half tile.
             */ 
            this.path.addAll(List.of(dir, dir, dir));
            
            iterator = path.iterator();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Directions getDirection() {
        lastPos = this.entity.getPosition();

        if (timeToUpdate) {
            // If enough time passed, the ai updates the path. 
            this.updatePath();
        }

        if (backupAiIsActive) {
            // If entity lost path is stuck a backup path is setted
            this.setBackupPath();
        }

        return backupAiIsActive ? backupAi.getDirection()
                : iterator.hasNext() ? iterator.next() : Directions.NONE;
    }
}
