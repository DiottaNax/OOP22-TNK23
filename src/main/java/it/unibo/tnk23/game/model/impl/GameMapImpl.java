package it.unibo.tnk23.game.model.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.model.api.GameMap;

public class GameMapImpl implements GameMap {

    private File mapFile;
    private Set<Point2D> destroyableWalls;
    private Set<Point2D> walls;

    static final private int MAP_SIZE = Configuration.GRID_SIZE * 2;

    public GameMapImpl(final File file) {
        this.mapFile = file;
        this.walls = new HashSet<>();
        this.destroyableWalls = new HashSet<>();
    }

    @Override
    public Set<Point2D> getDestroyableWalls() {
        return this.destroyableWalls;
    }

    @Override
    public Set<Point2D> getWalls() {
        return this.walls;
    }
    
    public void generateWalls() {
        try {
            BufferedReader mapReader = new BufferedReader(new FileReader(mapFile));

            for (int l = 0; l < MAP_SIZE; l++) {
                var line = mapReader.readLine().toCharArray();
                for (int c = 0; c < MAP_SIZE; c++) {
                    var ch = line[c];
                    switch (ch) {
                        case 'D':
                            this.destroyableWalls
                                    .add(new Point2D(c * Configuration.TILE_SIZE, l * Configuration.TILE_SIZE));
                            break;
                        case 'U':
                            this.walls
                                    .add(new Point2D(c * Configuration.TILE_SIZE, l * Configuration.TILE_SIZE));
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
