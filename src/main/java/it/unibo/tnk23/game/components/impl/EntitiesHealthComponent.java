package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.game.model.impl.TypeObjectFactory;

public class EntitiesHealthComponent extends AbstractHealthComponent {
    private final int coolDown = Configuration.FPS * 2;  //equals to 2 seconds
    private int currentFrame;
    private boolean isTouchable;

    public EntitiesHealthComponent(GameObject entity, World world) {
        super(entity, world);
        isTouchable = false;
    }

    @Override
    public void update() {
        if (currentFrame >= coolDown) {
            isTouchable = true;
        } else {
            currentFrame++;
        }
        super.update();
    }
    
    @Override
    public <X> void receive(Message<X> x) {
        if (isTouchable()) {
            if (x instanceof GameObject) {
                GameObject obj = (GameObject) x;
                if (TypeObjectFactory.isBullet(obj.getType())) {
                    var bulletCmp = obj.getComponent(BulletComponent.class);
                    if (bulletCmp.isPresent()
                            && super.weaknesses.contains(((BulletComponent) bulletCmp.get()).getShooter())) {
                        health -= obj.getPower();
                    }
                }
            }
        }
    }

    @Override
    protected boolean isTouchable() {
        return isTouchable;
    }
    
}
