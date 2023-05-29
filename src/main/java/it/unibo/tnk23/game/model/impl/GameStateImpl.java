package it.unibo.tnk23.game.model.impl;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.tnk23.game.components.impl.EntitiesHealthComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.GameState;
import it.unibo.tnk23.game.model.api.Round;
import it.unibo.tnk23.game.model.api.World;

public class GameStateImpl implements GameState {

    private final World world;
    private final Round round;
    private boolean isGameOver;
    
    public GameStateImpl(final World world) {
        this.world = world;
        this.round = new RoundImpl(world);
    }

    @Override
    public Round getRound() {
        return this.round;
    }

    @Override
    public boolean isRoundOver() {
        return this.round.isOver();
    }

    @Override
    public Map<GameObject, Integer> getPlayerLifes() {
        return this.world.getPlayers().stream().collect(
                Collectors.toMap(e -> e, e -> e.getComponent(EntitiesHealthComponent.class).get().getHealth()));
    }

    @Override
    public Optional<Integer> getPlayerLife(int id) {
        return this.world.getPlayer(id)
                .map(o -> o.getComponent(EntitiesHealthComponent.class))
                .map(o -> o.get().getHealth());
    }

    @Override
    public boolean isGameOver() {
        return isGameOver;
    }

    @Override
    public void update() {
        var entites = this.world.getEntities();
        this.round.update();
        isGameOver = this.world.getPlayers().stream().noneMatch(entites::contains);
    }

    
    
}
