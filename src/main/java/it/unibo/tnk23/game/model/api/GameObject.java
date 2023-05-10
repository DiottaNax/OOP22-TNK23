package it.unibo.tnk23.game.model.api;

import java.util.List;
import java.util.stream.Stream;

import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.components.api.TypeObject;

public interface GameObject {

    void update();

    Stream<Component> getComponents();

    TypeObject getType();

    <X> void notifyComponents(Message<X> message, Class<NotifiableComponent> nc);
    
}
