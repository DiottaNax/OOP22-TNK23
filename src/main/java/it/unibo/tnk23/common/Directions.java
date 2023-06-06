package it.unibo.tnk23.common;

public enum Directions {
    NORTH(0, -1),
    SOUTH(0, 1),
    WEST(-1, 0),
    EAST(1, 0),
    NONE(0, 0);

    private Vector2D vel;

    Directions(final int x, final int y) {
        vel = new Vector2D(x, y);
    }

    public Vector2D getVel() {
        return this.vel;
    }

    public static Directions fromVector(Vector2D v) {
        for (var d : values()) {
            if (d.vel.equals(v)) {
                return d;
            }
        }
        return NONE;
    }

    public Directions flipped() {
        return fromVector(this.vel.mul(-1));
    }

    public static Directions fromAngle(int angle) {
        var dir = NONE;
        switch (angle) {
            case 0:
                dir = NORTH;
                break;
            case 90:
                dir = EAST;
                break;
            case -90:
                dir = WEST;
                break;
            case 180:
                dir = SOUTH;
                break;
            default:
                break;
        }

        return dir;
    }
}
