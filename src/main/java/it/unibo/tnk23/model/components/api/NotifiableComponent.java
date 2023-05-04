package it.unibo.tnk23.model.components.api;

public interface NotifiableComponent<X>{

    void receive(Message<X> x);
    
}
