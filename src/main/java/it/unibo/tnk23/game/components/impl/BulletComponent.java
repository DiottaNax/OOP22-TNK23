package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.components.api.Message;
import it.unibo.tnk23.game.components.api.NotifiableComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.TypeObject;
import it.unibo.tnk23.game.model.impl.TypeObjectFactory;
import it.unibo.tnk23.game.model.api.World;

/**
 * Represent a component for a bullet entity
 * Implements the {@link NotifiableComponent} interface for receiving messages.
 * The BulletComponent handles the behavior of bullet GameObject.
 */
public class BulletComponent extends AbstractComponent implements NotifiableComponent {

    private static TypeObject shooterType;

    /**
     * Constructs a new {@link BulletComponent} with the specified entity and world.
     * 
     * @param entity the GameObject associated with this bullet component
     * @param world the World in which the bullet component exists.
     */
    public BulletComponent(final GameObject entity, final World world) {
        super(entity, world);
        shooterType = TypeObjectFactory.getBulletType();
    }

    /**
     * {@inheritDoc}
     * This method is empty as the bullet component does not require specific update logic.
     */
    @Override
    public void update() {
    }

    /**
     * {@inheritDoc}
     * Save in {@code shooterType} the type of the sender.
     */
    @Override
    public <X> void receive(Message<X> x) {
        if(x.getMessage() instanceof GameObject) {
            GameObject obj = (GameObject) x.getMessage();
            shooterType = obj.getType();
        }
    }

    /**
     * Retrieves the type of the shooter that fired this bullet.
     * 
     * @return the type of the shooter.
     */
    public TypeObject getShooter() {
        return shooterType;
    }

}
