package it.unibo.tnk23.game.model.impl;


import java.util.LinkedHashSet;
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

/**
 * The GameObjectImpl class implements the GameObject interface and represents a game object
 * in the game model.
 */
public class GameObjectImpl implements GameObject {

    private TypeObject type;
    private Point2D position;
    private Directions direction;
    private int power = 1;
    private double rotation;
    private Set<Component> components;

    /**
     * Creates a new GameObjectImpl instance with the specified type and position.
     *
     * @param type     the type of the game object
     * @param position the position of the game object
     */
    public GameObjectImpl(TypeObject type, Point2D position) {
        this.type = type;
        this.position = position;
        this.direction = Directions.NONE;
        this.components = new LinkedHashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        components.forEach(Component::update);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<Component> getComponents() {
        return components.stream();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeObject getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <X> void notifyComponents(Message<X> message, Class<? extends NotifiableComponent> nc) {
        components.stream().filter(nc::isInstance).map(nc::cast).forEach(c -> c.receive(message));
    }
    
    /**
     * Retrieves the position of the game object.
     *
     * @return the position of the game object
     */
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPower() {
        return this.power;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <C extends Component> Optional<C> getComponent(Class<C> clas) {
        return this.components.stream()
                .filter(clas::isInstance)
                .map(clas::cast).findAny();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Directions getDirection() {
        return this.direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(Directions direction) {
        this.direction = direction;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addComponent(Component comp) {
        components.add(comp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getRotation() {
        return this.rotation;
    }
    
}
