package it.unibo.tnk23.game.model.impl;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.game.components.impl.EntitiesHealthComponent;
import it.unibo.tnk23.game.model.api.GameState;
import it.unibo.tnk23.game.model.api.Round;
import it.unibo.tnk23.game.model.api.World;

/**
 * The implementation of the GameState interface that represents the current state of the game.
 */
public class GameStateImpl implements GameState {

    private final World world;
    private final Round round;
    private boolean isGameOver;

    /**
     * Constructs a GameStateImpl object with the specified World.
     *
     * @param world The World object representing the game world.
     */
    @SuppressFBWarnings(
        value = {
            "EI2"
        }, 
            justification = "GameStateImpl must store the original world in order to use its methods."
    )
    public GameStateImpl(final World world) {
        this.world = world;
        this.round = new RoundImpl(world);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(
        value = {
            "EI"
        },
            justification = "We need to get the original round because we need to do his update."
    )
    @Override
    public Round getRound() {
        return this.round;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRoundOver() {
        return this.round.isOver();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> getPlayerLife(final int id) {
        return this.world.getPlayer(id)
                .map(o -> o.getComponent(EntitiesHealthComponent.class))
                .map(o -> o.get().getHealth());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        return isGameOver;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        final var entities = this.world.getEntities();
        this.round.update();
        final var tower = this.world.getTower();
        isGameOver = this.world.getPlayers().stream().noneMatch(entities::contains)
                || !this.world.getEntities().contains(tower);
    }

}
