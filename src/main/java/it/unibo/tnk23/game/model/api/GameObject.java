package it.unibo.tnk23.game.model.api;

import java.util.Optional;
import java.util.stream.Stream;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.game.components.api.Component;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;

/**
 * The GameObject interface represents an object within the game.
 */
public interface GameObject {

    /**
     * Updates the state of the game object.
     */
    void update();

    /**
     * Retrieves the components associated with the game object.
     *
     * @return a stream of components
     */
    Stream<Component> getComponents();

    /**
     * Retrieves the type of the game object.
     *
     * @return the type of the game object
     */
    TypeObject getType();

    /**
     * Notifies the components of the game object with a message.
     *
     * @param <X>      the type of the message
     * @param message  the message to notify
     * @param nc       the class of the notifiable component
     */
    <X> void notifyComponents(Message<X> message, Class<? extends NotifiableComponent> nc);

    /**
     * Retrieves the position of the game object.
     *
     * @return the position of the game object
     */
    Point2D getPosition();

    /**
     * Sets the position of the game object.
     *
     * @param position the new position of the game object
     */
    void setPosition(Point2D position);

    /**
     * Retrieves the direction of the game object.
     *
     * @return the direction of the game object
     */
    Directions getDirection();

    /**
     * Sets the direction of the game object.
     *
     * @param direction the new direction of the game object
     */
    void setDirection(Directions direction);

    /**
     * Retrieves the power of the game object.
     *
     * @return the power of the game object
     */
    int getPower();

    /**
     * Sets the power of the game object.
     *
     * @param power the new power of the game object
     */
    void setPower(int power);
    
    /**
     * Adds a component to the game object.
     *
     * @param comp the component to add
     */
    void addComponent(Component comp);
    
    /**
     * Retrieves a component of the specified class from the game object.
     *
     * @param <C>       the type of the component
     * @param compClass the class of the component to retrieve
     * @return an optional containing the component, or empty if not found
     */
    <C extends Component> Optional<C> getComponent(Class<C> compClass);

    /**
     * Sets the rotation of the game object.
     *
     * @param rotation the new rotation of the game object
     */
    void setRotation(double rotation);

    /**
     * Retrieves the rotation of the game object.
     *
     * @return the rotation of the game object
     */
    double getRotation();
}
