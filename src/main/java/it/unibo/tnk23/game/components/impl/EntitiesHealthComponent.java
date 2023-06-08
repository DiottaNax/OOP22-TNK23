package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.game.model.impl.GameObjectTypeManager;

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
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public <X> void receive(Message<X> x) {
        if (isTouchable()) {
            if (x.getMessage() instanceof GameObject) {
                GameObject obj = (GameObject) x.getMessage();
                if (GameObjectTypeManager.isBullet(obj.getType())) {
                    var bulletCmp = obj.getComponent(BulletComponent.class);
                    if (bulletCmp.isPresent() && !this.getEntity().getType().equals(bulletCmp.get().getShooter())) {
                        this.setHealth( this.getHealth() - obj.getPower());
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isTouchable() {
        return isTouchable;
    }

}
