package it.unibo.tnk23.game.components;

import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The NotifiableComponentTest class is responsible for testing the functionality of the NotifiableComponent interface.
 */
class NotifiableComponentTest {

    /**
     * The TestComponent class is a test implementation of the NotifiableComponent interface.
     */
    private static class TestComponent implements NotifiableComponent {
        private String receivedMessage;

        @Override
        public void update() {
            // Empty or necessary implementation for specific tests
        }

        @Override
        public <X> void receive(final Message<X> x) {
            receivedMessage = x.getMessage().toString();
        }

        public String getReceivedMessage() {
            return receivedMessage;
        }
    }

    /**
     * Test case for the receive() method.
     * It verifies that the method sets the received message correctly.
     */
    @Test
    void testReceive() {
        // Prepare test data
        final TestComponent component = new TestComponent();
        final Message<Integer> message = new TestMessage<>(42); // Using TestMessage

        // Perform the test
        component.receive(message);

        // Verify the results
        assertEquals("42", component.getReceivedMessage());
    }

    /**
     * The TestMessage class is a test implementation of the Message interface.
     * It is used for testing purposes.
     *
     * @param <X> the type of the message
     */
    private static class TestMessage<X> implements Message<X> {
        private final X message;

        TestMessage(final X message) {
            this.message = message;
        }

        @Override
        public X getMessage() {
            return message;
        }
    }
}
