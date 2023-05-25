package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;

public class TimeFireComponent extends AbstractFireComponent{

    private long firePeriod;
    private long currentTime;
    private long lastTime;

    public TimeFireComponent(GameObject entity, World world) {
        super(entity, world);
        firePeriod = 1000;
        currentTime = System.currentTimeMillis();
        lastTime = System.currentTimeMillis();
    }

    @Override
    protected boolean canShoot() {
        if((currentTime-lastTime) <= firePeriod) {
            currentTime = (System.currentTimeMillis() - lastTime);
            return false;
        } else {
            currentTime = System.currentTimeMillis();
            lastTime = System.currentTimeMillis();
            return true;
        }
    }
    
}