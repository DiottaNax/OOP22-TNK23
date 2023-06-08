package it.unibo.tnk23.game.components.api;

/**
 * An interface that rapresent a notifiable game component.
 * Notifiable components extend the basic functionality of {@link Component} by allowing them to recive messages.
 */
public interface NotifiableComponent extends Component {

    /**
     * Recives a message of type {@code X}.
     * 
     * @param <X> the type of the message.
     * @param x the message to be recived.
     */
    <X> void receive(Message<X> x);

}
