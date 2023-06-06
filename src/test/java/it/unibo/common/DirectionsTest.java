package it.unibo.common;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import it.unibo.tnk23.common.Directions;

public class DirectionsTest {
    
    @Test
    public void testFlipped() {
        var expected = Directions.NORTH;
        assertEquals(expected, Directions.SOUTH.flipped());
        expected = Directions.SOUTH;
        assertEquals(expected, Directions.NORTH.flipped());
        expected = Directions.WEST;
        assertEquals(expected, Directions.EAST.flipped());
        expected = Directions.EAST;
        assertEquals(expected, Directions.WEST.flipped());
        expected = Directions.NONE;
        assertEquals(expected, Directions.NONE.flipped());
    }

    @Test
    public void testFromAngle() {
        final var expected = List.of(Directions.NORTH, Directions.SOUTH, Directions.EAST, Directions.WEST);
        final var actual = List.of(0, 180, 90, -90); //Angles goes clockwise in JavaFX
        assertEquals(expected, actual.stream().map(Directions::fromAngle).toList());
    }
}
