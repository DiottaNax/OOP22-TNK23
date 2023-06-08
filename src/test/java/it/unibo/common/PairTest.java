package it.unibo.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.unibo.tnk23.common.Pair;

class PairTest {

    @Test
    void testEqualsAndHashCode() {
        // Create two pairs with the same values
        Pair<String, Integer> pair1 = new Pair<>("A", 1);
        Pair<String, Integer> pair2 = new Pair<>("A", 1);

        // Assert that the pairs are equal
        Assertions.assertEquals(pair1, pair2);
        Assertions.assertEquals(pair1.hashCode(), pair2.hashCode());
    }

    @Test
    void testNotEquals() {
        // Create two pairs with different values
        Pair<String, Integer> pair1 = new Pair<>("A", 1);
        Pair<String, Integer> pair2 = new Pair<>("B", 2);

        // Assert that the pairs are not equal
        Assertions.assertNotEquals(pair1, pair2);
        Assertions.assertNotEquals(pair1.hashCode(), pair2.hashCode());
    }

    @Test
    void testToString() {
        // Create a pair
        Pair<String, Integer> pair = new Pair<>("A", 1);

        // Assert the string representation of the pair
        Assertions.assertEquals("(A, 1)", pair.toString());
    }
}
