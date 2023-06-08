package it.unibo.tnk23.game.model.api;

import java.util.List;

/**
 * An interface representing a round in the game.
 * A round consist of a list of enemies and manages the progression of the game.
 */
public interface Round {

    /**
     * Gets the list of enemies in the current round.
     * 
     * @return the list of enemies.
     */
    List<GameObject> getEnemies();

    /**
     * Checks if the current round is over.
     * 
     * @return {@code true} if the round is over, {@code false} otherwise.
     */
    boolean isOver();

    /**
     * Gtes the number of the current round.
     * 
     * @return the round number.
     */
    int getRound();

    /**
     * Gets the world associated with the round.
     * 
     * @return the World object.
     */
    World getWorld();

    /**
     * Gets the number of the randomly generated enemies in the current round.
     * 
     * @return the number of random enemies.
     */
    int getRandomEnemiesNum();

    /**
     * gets the number of AI-controlled enemies in the current round.
     * 
     * @return the number of AI enemies.
     */
    int getAIEnemiesNum();

    /**
     * Gets the total number of alive enemies in the current round.
     * {@code totalEnemies} is composed of total enemies minus dead ones.
     * 
     * @return the total number of alive enemies
     */
    int getTotalEnemies();

    /**
     * Updates the round state.
     * This method is called to update the round's logic and progress.
     */
    void update();

    /**
     * Starts the current round.
     * This method is called to begin a new round when the previous one ends.
     */
    void startRound();

    /**
     * Notifies the round of an enemy's death.
     * This method is called to notify the round that an enemy ha been killed.
     */
    void notifyEnemyDeath();
}
