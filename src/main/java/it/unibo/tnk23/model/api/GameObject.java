package it.unibo.tnk23.model.api;

import java.util.List;

public interface GameObject {

    void update();

    List<Component>getComponents();

    Type getType();

    <X> void notifyComponents(Message<X> message, NotifiableComponent<X> nc);
    
}
