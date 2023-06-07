package it.unibo.tnk23.input.api;

import it.unibo.tnk23.game.model.api.GameObject;


/**
 * The {@code AiControllerFactory} interface defines a factory for creating AI controllers.
 * It provides methods to obtain different types of AI controllers based on specific requirements.
 */
public interface AiControllerFactory {
    
    /**
     * Returns an AI controller that generates random directions.
     * The first direction generated should always be {@code SOUTH}
     *
     * @return A random AI controller.
     */
    InputController getRandomAi();

    /**
     * Returns an AI controller that makes the entity follow the game tower.
     *
     * @param entity The game object representing the entity.
     * @return An AI controller for following the game tower.
     */
    InputController getFollowTowerAi(GameObject entity);

    /**
     * Returns an AI controller that makes the entity follow a moving target.
     *
     * @param entity The game object representing the entity.
     * @param target The game object representing the moving target to follow.
     * @return An AI controller for following a moving target.
     */
    InputController getFollowMovingTargetAi(GameObject entity, GameObject target);
}
