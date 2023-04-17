package it.unibo.tnk23.model.api;

import java.util.Map;

public interface GameState {
    
    Round getRound();

    boolean isRoundOver();

    Map<Integer,Integer> getPlayerLifes();

    boolean isGameOver();
}
