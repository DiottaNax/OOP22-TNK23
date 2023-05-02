package it.unibo.tnk23.common;

import javafx.stage.Screen;

public final class Configuration {
    private final static int MAX_FPS = 120;
    private final static int GRID_SIZE = 19;
    private final static int ORIGINAL_TILE_SIZE = 24;

    public static int getMaxfps() {
        return MAX_FPS;
    }

    private static double getMinorScreenEdge(){
        var screenDim = Screen.getPrimary().getBounds();
        return (int) (screenDim.getHeight() <= screenDim.getWidth() ? screenDim.getHeight() : screenDim.getWidth());
    }

    public static int getTileSize() {
        return Math.floorDiv((int) getMinorScreenEdge(), GRID_SIZE);
    }

    public static float getScaleFactor() {
        return getTileSize() / ORIGINAL_TILE_SIZE;
    }

    public static int getGameSceneDim() {
        return getTileSize() * GRID_SIZE;
    }


}
