package it.unibo.tnk23.game.model.api;

import java.util.Map;
import java.util.Optional;

/**
 * The GameState interface represents the current state of the game.
 * It provides information about the current round, player lives, and game over status.
 */
public interface GameState {

    /**
     * Retrieves the current round of the game.
     *
     * @return The Round object representing the current round.
     */
    Round getRound();

    /**
     * Checks if the current round is over.
     *
     * @return true if the round is over, false otherwise.
     */
    boolean isRoundOver();

    /**
     * Retrieves a map of GameObjects and their corresponding life counts for each player.
     *
     * @return A Map containing GameObjects as keys and their respective life counts as values.
     */
    Map<GameObject, Integer> getPlayerLifes();

    /**
     * Retrieves the life count for a specific player identified by the given ID.
     *
     * @param id The ID of the player.
     * @return An Optional containing the life count if the player exists, or an empty Optional otherwise.
     */
    Optional<Integer> getPlayerLife(int id); 

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise.
     */
    boolean isGameOver();

    /**
     * Updates the game state, performing any necessary state changes.
     */
    void update();
}
