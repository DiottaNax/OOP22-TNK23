package it.unibo.tnk23.game.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.unibo.tnk23.game.components.api.Message;

public class MessageTest {
    @Test
    public void testGetMessage() {
        Message<String> message = new Message<String>() {
            @Override
            public String getMessage() {
                return "Hello World!";
            }
        };
        assertEquals("Hello World!", message.getMessage());
    }
}