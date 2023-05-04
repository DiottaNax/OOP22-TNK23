package it.unibo.tnk23.model.api;

import java.util.List;

import it.unibo.tnk23.model.components.api.Component;
import it.unibo.tnk23.model.components.api.Message;
import it.unibo.tnk23.model.components.api.NotifiableComponent;
import it.unibo.tnk23.model.components.api.TypeObject;

public interface GameObject {

    void update();

    List<Component>getComponents();

    TypeObject getType();

    <X> void notifyComponents(Message<X> message, NotifiableComponent<X> nc);
    
}
