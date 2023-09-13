package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.shape.Rect2D;
import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.game.model.impl.GameObjectTypeManager;
import it.unibo.tnk23.common.shape.Shape;

/**
 * Represents a collision component for game entities.
 * Handles the collision detection and resolution for entities.
 */
public class CollisionComponent extends AbstractComponent {

    private final Rect2D hitbox;
    /**
    * Sensibility constant used for approximation of the hitbox.
    */
    private static final double SENSIBILITY = 0.05;

    /**
     * Constructs a CollisionComponent for the given entity and world.
     *
     * @param entity the entity to which this component belongs
     * @param world the game world
     */
    public CollisionComponent(final GameObject entity, final World world) {
        super(entity, world);
        final var type = entity.getType();
        final var width = type.getWidth()  - SENSIBILITY;
        final var height = type.getHeight() - SENSIBILITY;
        this.hitbox = new Rect2D(width, height, entity.getPosition());
    }

    /**
     * Checks if the entity is colliding with the given shape.
     *
     * @param shape the shape to check for collision
     * @return true if the entity is colliding with the shape, false otherwise
     */
    public boolean isCollidingWith(final Shape shape) {
        return this.hitbox.isColliding(shape);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (!this.getEntity().getDirection().equals(Directions.NONE)) {
            this.hitbox.setPos(this.getEntity().getPosition());

            final var collidedList = this.getWorld().getEntities().stream()
                    .filter(e -> !e.equals(this.getEntity()))
                    .filter(e -> e.getComponent(CollisionComponent.class).isPresent())
                    .filter(e -> e.getComponent(CollisionComponent.class).get()
                            .isCollidingWith((Shape) hitbox))
                    .toList();
             if (!collidedList.isEmpty() && GameObjectTypeManager.isBullet(this.getEntity().getType())) {
                 this.getWorld().notifyEvent(new WorldEventImpl(this.getEntity().getPosition(), this.getEntity(),
                         WorldEventType.DEATH_EVENT));
            }

            collidedList.forEach(e -> {
                e.notifyComponents(() -> this.getEntity(), EntitiesHealthComponent.class);
                e.notifyComponents(() -> this.getEntity(), BulletHealthComponent.class);
            });
            collidedList.stream().findAny().ifPresent(e -> this.getEntity().notifyComponents(() -> e, PhysicsComponent.class));
        }
    }
}
