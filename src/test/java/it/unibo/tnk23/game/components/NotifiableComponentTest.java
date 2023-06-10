package it.unibo.tnk23.game.components;

import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotifiableComponentTest {

    private static class TestComponent implements NotifiableComponent {
        private String receivedMessage;

        @Override
        public void update() {
            // Implementazione vuota o necessaria per i test specifici
        }

        @Override
        public <X> void receive(Message<X> x) {
            receivedMessage = x.getMessage().toString();
        }

        public String getReceivedMessage() {
            return receivedMessage;
        }
    }

    @Test
    public void testReceive_ShouldSetReceivedMessage() {
        // Prepare test data
        TestComponent component = new TestComponent();
        Message<Integer> message = new TestMessage<>(42); // Utilizziamo TestMessage

        // Perform the test
        component.receive(message);

        // Verify the results
        assertEquals("42", component.getReceivedMessage());
    }

    // Implementazione di TestMessage per scopi di test
    private static class TestMessage<X> implements Message<X> {
        private final X message;

        public TestMessage(X message) {
            this.message = message;
        }

        @Override
        public X getMessage() {
            return message;
        }
    }
}

