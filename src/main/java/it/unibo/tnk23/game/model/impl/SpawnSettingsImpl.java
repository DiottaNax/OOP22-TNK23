package it.unibo.tnk23.game.model.impl;

import it.unibo.tnk23.game.model.api.Spawn;
import it.unibo.tnk23.game.model.api.SpawnSettings;

public class SpawnSettingsImpl implements SpawnSettings{

    private long delay;

    public SpawnSettingsImpl(final long delay) {
        this.delay = delay;
    }

    @Override
    public Spawn start() {
        return new SpawnImpl(this.delay);
    }

    @Override
    public void setDelayOfSpawining(final long interval) {
        this.delay = interval;
    }
    
}
