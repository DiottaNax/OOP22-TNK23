package it.unibo.tnk23.game.model.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.model.api.GameMap;

    /**
    * Implementation of the {@link GameMap} interface that represents a game map with walls and destroyable walls.
    */
public class GameMapImpl implements GameMap {

    private final InputStream mapFile;
    private final Set<Point2D> destroyableWalls;
    private final Set<Point2D> walls;
    private static final Logger LOGGER = Logger.getLogger("GameMapImplLogger");


    static final int MAP_SIZE = Configuration.GRID_SIZE * 2;

    /**
     * Constructs a GameMapImpl object with the provided map file.
     *
     * @param file The input stream of the map file.
     */
    public GameMapImpl(final InputStream file) {
        this.mapFile = file;
        this.walls = new HashSet<>();
        this.destroyableWalls = new HashSet<>();
        generateWalls();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Point2D> getDestroyableWalls() {
        return this.destroyableWalls;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Point2D> getWalls() {
        return this.walls;
    }
    /**
     * Generates the walls and destroyable walls based on the provided map file.
     */
    private void generateWalls() {
        try {
            final BufferedReader mapReader = new BufferedReader(new InputStreamReader(mapFile));

            for (int l = 0; l < MAP_SIZE; l++) {
                final var line = mapReader.readLine().toCharArray();
                for (int c = 0; c < MAP_SIZE; c++) {
                    final var ch = line[c];
                    switch (ch) {
                        case 'D':
                            this.destroyableWalls
                                    .add(new Point2D(c * Configuration.TILE_SIZE / 2, l * Configuration.TILE_SIZE / 2));
                            break;
                        case 'U':
                            this.walls
                                    .add(new Point2D(c * Configuration.TILE_SIZE / 2, l * Configuration.TILE_SIZE / 2));
                            break;
                        default:
                            break;
                    }
                }
            }

            mapReader.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading or closing the map file", e);
        }
    }
}
