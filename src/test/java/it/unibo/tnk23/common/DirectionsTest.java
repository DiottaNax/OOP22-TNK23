package it.unibo.tnk23.common;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

/**
 * Unit tests for the {@link Directions} class.
 */
public class DirectionsTest {

    /**
     * Tests the {@link Directions#flipped()} method.
     */
    @Test
    public void testFlipped() {
        // Test flipping SOUTH direction
        var expected = Directions.NORTH;
        assertEquals(expected, Directions.SOUTH.flipped());

        // Test flipping NORTH direction
        expected = Directions.SOUTH;
        assertEquals(expected, Directions.NORTH.flipped());

        // Test flipping EAST direction
        expected = Directions.WEST;
        assertEquals(expected, Directions.EAST.flipped());

        // Test flipping WEST direction
        expected = Directions.EAST;
        assertEquals(expected, Directions.WEST.flipped());

        // Test flipping NONE direction
        expected = Directions.NONE;
        assertEquals(expected, Directions.NONE.flipped());
    }

    /**
     * Tests the {@link Directions#fromAngle(int)} method.
     */
    @Test
    public void testFromAngle() {
        // Define the expected directions corresponding to the angles
        final var expected = List.of(Directions.NORTH, Directions.SOUTH, Directions.EAST, Directions.WEST);

        // Define the angles in clockwise order
        final var actual = List.of(0, 180, 90, -90); // Angles go clockwise in JavaFX

        // Assert that the directions obtained from the angles match the expected directions
        assertEquals(expected, actual.stream().map(Directions::fromAngle).toList());
    }
}


