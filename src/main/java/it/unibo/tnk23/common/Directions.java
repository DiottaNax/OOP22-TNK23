package it.unibo.tnk23.common;

public enum Directions {
    NORTH(0, -1),
    SOUTH(0, 1),
    EAST(-1, 0),
    WEST(1, 0),
    NONE(0, 0);

    private Vector2D direction;

    private Directions(final int x, final int y) {
        direction = new Vector2D(x, y);
    }

    public Vector2D getVel() {
        return this.direction;
    }
}
