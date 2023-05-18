package it.unibo.tnk23.input.api;

import it.unibo.tnk23.game.model.api.GameObject;

public interface AiControllerFactory {
    
    InputController getRandomAi();

    InputController getFollowStillTargetAi(GameObject target);

    InputController getFollowMovingTargetAi(GameObject target);
}
