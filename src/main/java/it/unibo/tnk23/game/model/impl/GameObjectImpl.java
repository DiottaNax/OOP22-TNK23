package it.unibo.tnk23.game.model.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.TypeObject;

public class GameObjectImpl implements GameObject {

    private TypeObject type;
    private Point2D position;
    private Directions direction;
    private int power = 1;
    private double rotation;
    private Set<Component> components;

    public GameObjectImpl(TypeObject type, Point2D position) {
        this.type = type;
        this.position = position;
        this.direction = Directions.NONE;
        this.components = new LinkedHashSet<>();
    }

    @Override
    public void update() {
        components.forEach(Component::update);
    }

    @Override
    public Stream<Component> getComponents() {
        return components.stream();
    }

    @Override
    public TypeObject getType() {
        return type;
    }

    @Override
    public <X> void notifyComponents(Message<X> message, Class<? extends NotifiableComponent> nc) {
        components.stream().filter(nc::isInstance).map(nc::cast).forEach(c -> c.receive(message));
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
    public <C extends Component> Optional<C> getComponent(Class<C> clas) {
        return this.components.stream()
                .filter(clas::isInstance)
                .map(clas::cast).findAny();
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
    
    @Override
    public void addComponent(Component comp) {
        components.add(comp);
    }

    @Override
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    @Override
    public double getRotation() {
        return this.rotation;
    }
    
}
