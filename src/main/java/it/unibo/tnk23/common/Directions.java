package it.unibo.tnk23.common;

/**
 * The {@code Directions} enum represents different directions in a two-dimensional space.
 * Each direction is associated with a velocity vector represented by the {@link Vector2D} class.
 */
public enum Directions {
    /**
     * The north direction (up).
     */
    NORTH(0, -1),

    /**
     * The south direction (down).
     */
    SOUTH(0, 1),

    /**
     * The west direction (left).
     */
    WEST(-1, 0),

    /**
     * The east direction (right).
     */
    EAST(1, 0),

    /**
     * No direction (stationary).
     */
    NONE(0, 0);

    private Vector2D vel;

    /**
     * Constructs a direction with the specified x and y components of the velocity vector.
     *
     * @param x The x component of the velocity vector.
     * @param y The y component of the velocity vector.
     */
    Directions(final int x, final int y) {
        vel = new Vector2D(x, y);
    }

    /**
     * Retrieves the velocity vector associated with the direction.
     *
     * @return The velocity vector of the direction.
     */
    public Vector2D getVel() {
        return new Vector2D(this.vel.getX(), this.vel.getY());
    }

    /**
     * Converts a {@link Vector2D} object into the corresponding {@code Directions} enum value.
     * If the provided vector does not match any predefined direction, {@link #NONE} is returned.
     *
     * @param v The vector to convert into a direction.
     * @return The {@code Directions} enum value corresponding to the provided vector.
     */
    public static Directions fromVector(final Vector2D v) {
        for (final var d : values()) {
            if (d.vel.equals(v)) {
                return d;
            }
        }
        return NONE;
    }

    /**
     * Returns the opposite direction of the current direction.
     *
     * @return The opposite direction of the current direction.
     */
    public Directions flipped() {
        return fromVector(this.vel.mul(-1));
    }

    /**
     * Converts an angle (in degrees) into the corresponding {@code Directions} enum value.
     * It maps specific angles to the predefined directions (0 degrees - {@link #NORTH},
     * 90 degrees - {@link #EAST}, -90 degrees - {@link #WEST}, 180 degrees - {@link #SOUTH}).
     *
     * @param angle The angle to convert into a direction.
     * @return The {@code Directions} enum value corresponding to the provided angle.
     */
    public static Directions fromAngle(final int angle) {
        var dir = NONE;
        switch (angle) {
            case 0:
                dir = NORTH;
                break;
            case 90: //JavaFX calculates the rotation clockwise
                dir = EAST;
                break;
            case -90: //JavaFX calculates the rotation clockwise
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
