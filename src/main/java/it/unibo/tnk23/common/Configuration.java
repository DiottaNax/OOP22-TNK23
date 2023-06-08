package it.unibo.tnk23.common;

import javafx.stage.Screen;

/**
 * Configuration class that contains various constants and settings for the game.
 */
public final class Configuration {
    /**
     * The original tile size in pixel.
     */
    private static final int ORIGINAL_TILE_SIZE = 24;
    /**
     * The max frame rate of the game.
     */
    public static final int FPS = 120;
    /**
     * The size of the game grid.
     */
    public static final int GRID_SIZE = 19;
    /**
     * The size of each tile in pixel adapted to the monitor.
     */
    public static final int TILE_SIZE = getTileSize();
    /**
     * The scale factor for resizing graphics based on the tile size.
     */
    public static final double SCALE_FACTOR = (double) TILE_SIZE / ORIGINAL_TILE_SIZE;
    /**
     * The dimension of the game scene in pixel.
     */
    public static final int GAME_SCENE_DIMENSION = GRID_SIZE * TILE_SIZE;
    /**
     * The displacement value describing the displacement between model and graphics.
     */
    public static final int DISPLACEMENT = 6;

    private Configuration() {
    }

    private static double getMinorScreenEdge() {
        final var screenDim = Screen.getPrimary().getBounds();
        return (int) Math.min(screenDim.getHeight(), screenDim.getWidth());
    }

    private static int getTileSize() {
        final int rounded = (int) getMinorScreenEdge() / GRID_SIZE;
        return (rounded % 2) == 0 ? rounded : Math.decrementExact(rounded);
    }
}
