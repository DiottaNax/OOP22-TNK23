package it.unibo.tnk23.common;

import javafx.stage.Screen;

/**
 * Configuration class that contains various constants and settings for the game.
 */
public final class Configuration {
    /**
     * The tile size in game dimension unit.
     */
    public static final int TILE_SIZE = 24;
    /**
     * The max frame rate of the game.
     */
    public static final int FPS = 120;
    /**
     * The size of the game grid in tiles.
     */
    public static final int GRID_SIZE = 19;

    /**
     * The size of the game map in game dimension unit.
     */
    public static final int MAP_SIZE = GRID_SIZE * TILE_SIZE;
    /**
     * The scale factor for resizing graphics based on the tile size.
     */
    public static final double SCALE_FACTOR = getMinorScreenEdge() / GRID_SIZE / TILE_SIZE;
    /**
     * The dimension of the game scene in pixel.
     */
    public static final double GAME_SCENE_DIMENSION = GRID_SIZE * TILE_SIZE * SCALE_FACTOR;
    /**
     * The displacement value describing the displacement between model and graphics.
     */
    public static final int DISPLACEMENT = 4;

    private Configuration() {
    }

    private static double getMinorScreenEdge() {
        final var screenDim = Screen.getPrimary().getBounds();
        return Math.min(screenDim.getHeight(), screenDim.getWidth());
    }
}
