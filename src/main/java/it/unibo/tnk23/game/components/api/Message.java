package it.unibo.tnk23.game.components.api;

/**
 * An interface rappresenting a message.
 * 
 * @param <X> the type of the message content.
 */
public interface Message<X> {

    /**
     * Retrieves the message content.
     * 
     * @return the message content.
     */
    X getMessage();   
}
