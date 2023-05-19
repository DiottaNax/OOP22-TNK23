package it.unibo.tnk23.game.model.impl;

import it.unibo.tnk23.game.model.api.Spawn;
import it.unibo.tnk23.game.model.api.SpawnSettings;
import it.unibo.tnk23.game.world.api.World;

public class SpawnSettingsImpl implements SpawnSettings{

    private long delay;
    private final World world;

    public SpawnSettingsImpl(final long delay, final World world) {
        this.delay = delay;
        this.world = world;
    }

    @Override
    public Spawn start() {
        return new SpawnImpl(this.delay, this.world);
    }

    @Override
    public void setDelayOfSpawining(final long interval) {
        this.delay = interval;
    }
    
}
