package it.unibo.tnk23.game.components.api;

public interface NotifiableComponent<X>{

    void receive(Message<X> x);
    
}
