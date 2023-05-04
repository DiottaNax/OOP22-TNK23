package it.unibo.tnk23.model.api;

public interface NotifiableComponent<X>{

    void receive(Message<X> x);
    
}
