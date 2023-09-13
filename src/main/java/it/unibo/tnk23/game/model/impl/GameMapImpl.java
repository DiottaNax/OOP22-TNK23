package it.unibo.tnk23.game.model.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashSet;
import java.util.Set;
import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.model.api.GameMap;

    /**
    * Implementation of the {@link GameMap} interface that represents a game map with walls and destroyable walls.
    */
public class GameMapImpl implements GameMap {

    private static final Logger LOGGER = Logger.getLogger("GameMapImplLogger");
    static final int MAP_SIZE = Configuration.GRID_SIZE * 2;
    private final InputStream mapFile;
    private final Set<Point2D> destroyableWalls;
    private final Set<Point2D> walls;
    private final Set<Point2D> towerWalls;


    private Point2D tower;

    /**
     * Constructs a GameMapImpl object with the provided map file.
     *
     * @param file The input stream of the map file.
     */
    public GameMapImpl(final InputStream file) {
        this.mapFile = file;
        this.walls = new HashSet<>();
        this.destroyableWalls = new HashSet<>();
        this.towerWalls = new HashSet<>();
        generateWalls();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Point2D> getDestroyableWalls() {
        return Set.copyOf(this.destroyableWalls);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Point2D> getWalls() {
        return Set.copyOf(this.walls);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getTowerPos() {
        return this.tower.copy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Point2D> getTowerWalls() {
        return Set.copyOf(this.towerWalls);
    }

    /**
     * Generates the walls and destroyable walls based on the provided map file.
     */
    private void generateWalls() {
        try {
            final BufferedReader mapReader = new BufferedReader(new InputStreamReader(mapFile, StandardCharsets.UTF_8));
            for (int l = 0; l < MAP_SIZE; l++) {
                final String line = mapReader.readLine();
                if (line != null) {
                    final char[] chars = line.toCharArray();
                    for (int c = 0; c < MAP_SIZE; c++) {
                        final char ch = chars[c];
                        switch (ch) {
                            case 'D':
                                this.destroyableWalls.add(new Point2D(((double) c * Configuration.TILE_SIZE) / 2,
                                        ((double) l * Configuration.TILE_SIZE) / 2));
                                break;
                            case 'U':
                                this.walls.add(new Point2D(((double) c * Configuration.TILE_SIZE) / 2,
                                        ((double) l * Configuration.TILE_SIZE) / 2));
                                break;
                            case 'I':
                                this.towerWalls.add(new Point2D(((double) c * Configuration.TILE_SIZE) / 2,
                                        ((double) l * Configuration.TILE_SIZE) / 2));
                                break;
                            case 'T':
                                this.tower = new Point2D(((double) c * Configuration.TILE_SIZE) / 2,
                                        ((double) l * Configuration.TILE_SIZE) / 2);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
            mapReader.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading or closing the map file", e);
        }
    }
}
