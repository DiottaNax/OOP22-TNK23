package it.unibo.tnk23.game.model.api;

import java.util.stream.Stream;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;

public interface GameObject {

    void update();

    Stream<Component> getComponents();

    TypeObject getType();

    <X> void notifyComponents(Message<X> message, Class<NotifiableComponent> nc);

    Point2D getPosition();

    void addComponent(Component comp);
    
}
