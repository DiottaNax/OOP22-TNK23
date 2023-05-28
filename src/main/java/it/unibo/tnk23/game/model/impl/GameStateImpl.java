package it.unibo.tnk23.game.model.impl;

import java.util.Map;

import it.unibo.tnk23.game.model.api.GameState;
import it.unibo.tnk23.game.model.api.Round;
import it.unibo.tnk23.game.model.api.World;

public class GameStateImpl implements GameState {

    private World world;
    private Round round;
    
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
    public Map<Integer, Integer> getPlayerLifes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerLifes'");
    }

    @Override
    public boolean isGameOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isGameOver'");
    }

    @Override
    public void update() {
        this.round.update();
    }

    
    
}
