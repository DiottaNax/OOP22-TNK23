package it.unibo.tnk23.game.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.unibo.tnk23.game.components.api.Message;

/**
 * The MessageTest class is responsible for testing the functionality of the Message interface.
 */
public class MessageTest {
    /**
     * Test case for the getMessage() method.
     * It verifies that the method returns the correct message.
     */
    @Test
    public void testGetMessage() {
        final Message<String> message = new Message<String>() {
            @Override
            public String getMessage() {
                return "Hello World!";
            }
        };
        assertEquals("Hello World!", message.getMessage());
    }
}
