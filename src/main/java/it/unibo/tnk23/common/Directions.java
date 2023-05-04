package it.unibo.tnk23.common;

public enum Directions {
    NORTH(0, -1),
    SOUTH(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    NONE(0, 0);

    private Vector2D direction;

    private Directions(final int x, final int y) {
        direction = new Vector2D(x, y);
    }

    public Vector2D getDirection() {
        return this.direction;
    }
}
