package it.unibo.tnk23.game.model.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.components.api.ComponetSetFactory;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.TypeObject;

public class GameObjectImpl implements GameObject{
    TypeObject type;
    Point2D position;
    Directions direction;
    int power = 1;
    Map<Class<? extends Component>, Component> components;

    public GameObjectImpl(TypeObject type, Point2D position) {
        this.type = type;
        this.position = position;
        this.direction = Directions.NONE;
        this.components = new HashMap<>();
        ComponetSetFactory setfactory;
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
    public <X> void notifyComponents(Message<X> message, Class<? extends NotifiableComponent> nc) {
        if (components.containsKey(nc)) {
            ((NotifiableComponent) components.get(nc)).receive(message);   
        }
    }
    
    public Point2D getPosition() {
        return this.position;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public Optional<? extends Component> getComponent(Class<? extends Component> clas) {
        return components.containsKey(clas) ? Optional.of(components.get(clas)) : Optional.empty();
    }

    @Override
    public void setPosition(Point2D position) {
        this.position = position;
    }

    @Override
    public Directions getDirection() {
        return this.direction;
    }

    @Override
    public void setDirection(Directions direction) {
        this.direction = direction;
    }
    
    public void addComponent(Component comp) {
        components.put(comp.getClass(), comp);
    }
    
}
