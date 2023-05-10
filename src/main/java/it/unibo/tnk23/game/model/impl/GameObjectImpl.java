package it.unibo.tnk23.game.model.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.model.api.ComponentSetFactory;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.TypeObject;
import javafx.scene.control.skin.TextInputControlSkin.Direction;
public class GameObjectImpl implements GameObject{
    TypeObject type;
    Point2D position;
    Direction direction;
    Map<Class<? extends Component>, Component> components;

    public GameObjectImpl(TypeObject type, Point2D position, Direction direction) {
        this.type = type;
        this.position = position;
        this.direction = direction;
        this.components = new HashMap<>();
        ComponentSetFactory setFactory;
        //setFactory.getComponents(type).forEach(c -> components.put(c.getClass(), c));
    }

    @Override
    public void update() {
        components.values().forEach(Component::update);
    }

    @Override
    public Stream<Component> getComponents() {
        return components.values().stream();
    }

    @Override
    public TypeObject getType() {
        return type;
    }

    @Override
    public <X> void notifyComponents(Message<X> message, Class<NotifiableComponent> nc) {
        ((NotifiableComponent)components.get(nc)).receive(message);
    }
    
}
