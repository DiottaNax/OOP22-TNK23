package it.unibo.tnk23.common;

import javafx.stage.Screen;

public final class Configuration {
    private final static int ORIGINAL_TILE_SIZE = 24;
    public final static int FPS = 120;
    public final static int GRID_SIZE = 19;
    public final static int TILE_SIZE = getTileSize();
    public final static float SCALE_FACTOR = TILE_SIZE / ORIGINAL_TILE_SIZE;
    public final static int GAME_SCENE_DIMENSION = GRID_SIZE * TILE_SIZE;

    private Configuration() {   
    }

    private static double getMinorScreenEdge(){
        final var screenDim = Screen.getPrimary().getBounds();
        return (int) Math.min(screenDim.getHeight(),screenDim.getWidth());
    }

    public static int getTileSize() {
        final int rounded = (int) getMinorScreenEdge() / GRID_SIZE;
        return (rounded % 2) == 0 ? rounded : Math.decrementExact(rounded);
    }
}
