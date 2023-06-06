package it.unibo.tnk23.input.api;

import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.common.Directions;

public interface AiControllerFactory {
    
    /**
     * A method that returns an {@link InputController} that generates random {@link Directions}.
     * The first direction returned should always be SOUTH.
     * 
     * @return an {@link InputController} generating random directions
     */
    InputController getRandomAi();


    /**
     * A method that returns an {@link InputController} that generates the exact path of {@link Directions}
     * to reach a the Tower returned by {@link World}
     * @return an {@link InputController}
     */
    InputController getFollowTowerAi(GameObject entity);

    /**
     * A method that returns an {@link InputController} that generates the exact {@link Directions}
     * to reach a target. The path is updated every once in a while, so the target to reach could be
     * a moving {@link GameObject}
     * 
     * @param target the {@link GameObject} to reach
     * @return an {@link InputController}
     */
    InputController getFollowMovingTargetAi(GameObject entity, GameObject target);
}
