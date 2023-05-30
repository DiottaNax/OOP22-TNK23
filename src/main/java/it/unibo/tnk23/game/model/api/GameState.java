package it.unibo.tnk23.game.model.api;

import java.util.Map;
import java.util.Optional;

public interface GameState {
    
    Round getRound();

    boolean isRoundOver();

    Map<GameObject, Integer> getPlayerLifes();
    
    Optional<Integer> getPlayerLife(int id); 

    boolean isGameOver();

    void update();
}
