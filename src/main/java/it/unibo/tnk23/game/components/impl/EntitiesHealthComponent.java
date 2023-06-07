package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.game.model.impl.TypeObjectFactory;

/**
 * This class represents a health component for entities in the game (bullets excluded). It extends the
 * AbstractHealthComponent class, implements the isTouchable method and provides additional functionality specific to entities.
 */
public class EntitiesHealthComponent extends AbstractHealthComponent {

    private final int coolDown = Configuration.FPS * 2;  //equals to 2 seconds
    private int currentFrame;
    private boolean isTouchable;

    /**
     * Constructs an EntitiesHealthComponent with the specified parameters GameObject and World.
     *
     * @param entity the GameObject associated with this component
     * @param world  the World object in which the component exists
     */
    public EntitiesHealthComponent(GameObject entity, World world) {
        super(entity, world);
        isTouchable = false;
    }

    /**
     * Updates the component's state. If enough time has passed based on the cool-down period,
     * the component becomes touchable.
     */
    @Override
    public void update() {
        if (currentFrame >= coolDown) {
            isTouchable = true;
        } else {
            currentFrame++;
        }
        super.update();
    }

    /**
     * Receives a message and, if the component is touchable, check if it's a Bullet-type GameObject to eventually performs health-decrement actions (getting the entity which shoted the bullet).
     *
     * @param <X> the type of message being received
     * @param x   the message to receive
     */
    @Override
    public <X> void receive(Message<X> x) {
        if (isTouchable()) {
            if (x.getMessage() instanceof GameObject) {
                GameObject obj = (GameObject) x.getMessage();
                if (TypeObjectFactory.isBullet(obj.getType())) {
                    var bulletCmp = obj.getComponent(BulletComponent.class);
                    if (bulletCmp.isPresent() && !entity.getType().equals(bulletCmp.get().getShooter())) {
                        health -= obj.getPower();
                    }
                }
            }
        }
    }

    /**
     * Checks if the component is touchable.
     *
     * @return true if the component is touchable, false otherwise
     */
    @Override
    protected boolean isTouchable() {
        return isTouchable;
    }

}
