package it.unibo.tnk23.game.model.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.model.api.GameMap;

public class GameMapImpl implements GameMap {

    private InputStream mapFile;
    private Set<Point2D> destroyableWalls;
    private Set<Point2D> walls;

    static final private int MAP_SIZE = Configuration.GRID_SIZE * 2;

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
    public void generateWalls() {
        try {
            BufferedReader mapReader = new BufferedReader(new InputStreamReader(mapFile));

            for (int l = 0; l < MAP_SIZE; l++) {
                var line = mapReader.readLine().toCharArray();
                for (int c = 0; c < MAP_SIZE; c++) {
                    var ch = line[c];
                    switch (ch) {
                        case 'D':
                            this.destroyableWalls
                                    .add(new Point2D(c * Configuration.TILE_SIZE/2, l * Configuration.TILE_SIZE/2));
                            break;
                        case 'U':
                            this.walls
                                    .add(new Point2D(c * Configuration.TILE_SIZE/2, l * Configuration.TILE_SIZE/2));
                            break;
                        default:
                            break;
                    }
                }
            }

            mapReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
