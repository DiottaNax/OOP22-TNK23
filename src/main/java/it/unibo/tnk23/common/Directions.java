package it.unibo.tnk23.common;

import java.util.List;
import java.util.Random;

public enum Directions {
    NORTH(0, -1),
    SOUTH(0, 1),
    WEST(-1, 0),
    EAST(1, 0),
    NONE(0, 0);

    private Vector2D direction;

    private Directions(final int x, final int y) {
        direction = new Vector2D(x, y);
    }

    public Vector2D getVel() {
        return this.direction;
    }

    public static Directions getRandomDir() {
        final var possibilities = List.of(NONE,NORTH,SOUTH,WEST,EAST);
        return possibilities.get(new Random().nextInt(possibilities.size()));
    }
}
