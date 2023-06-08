package it.unibo.tnk23.input.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.game.graph.impl.GameGraph;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.input.api.AiControllerFactory;
import it.unibo.tnk23.input.api.InputController;

/**
 * The {@code AiControllerFactoryImpl} class implements the {@link AiControllerFactory} interface
 * and provides methods to create AI controllers for different scenarios.
 * 
 * @author Federico Diotallevi
 */
public class AiControllerFactoryImpl implements AiControllerFactory {
    private final GameGraph graph;
    private final World world;
    private final Random random;

    /**
     * Constructs an {@code AiControllerFactoryImpl} with the specified game graph and world.
     *
     * @param graph The game graph.
     * @param world The game world.
     */
    @SuppressFBWarnings(
        value = {
            "EI2"
        },
        justification = "The AiControllerFactory must store a graph and world in order to generate ai controllers."
    )
    public AiControllerFactoryImpl(final GameGraph graph, final World world) {
        this.graph = graph;
        this.graph.setWorld(world);
        this.world = world;
        this.random = new Random();
    }

    /**
     * Generates a pseudo-random direction different from the given direction.
     * It is less likely that the next direction is equal to the previous or to {@code NONE}.
     *
     * @param dir The current direction.
     * @return A pseudo-random direction.
     */
    private Directions getPseudoRandomDir(final Directions dir) {
        final var possibilities = new ArrayList<>(
                List.of(Directions.NONE, Directions.SOUTH, Directions.WEST, Directions.EAST));
        possibilities.addAll(possibilities);
        possibilities.remove(dir);
        possibilities.remove(Directions.NONE);
        return possibilities.get(this.random.nextInt(possibilities.size()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputController getRandomAi() {
        final var iterator = Stream.iterate(Directions.SOUTH, this::getPseudoRandomDir).iterator();
        return () -> iterator.next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputController getFollowTowerAi(final GameObject entity) {
        return new FollowTargetAi(graph, entity, this.world.getTower(), this.world);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputController getFollowMovingTargetAi(final GameObject entity, final GameObject target) {
        return new FollowTargetAi(graph, entity, target, this.world);
    }

}
