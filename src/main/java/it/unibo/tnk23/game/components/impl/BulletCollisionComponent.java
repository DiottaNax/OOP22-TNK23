package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.shape.Rect2D;
import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.events.api.WorldEventType;
import it.unibo.tnk23.game.events.impl.WorldEventImpl;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.common.shape.Shape;

/**
 * Represents a collision component for bullets in the game.
 * Handles the collision detection and resolution for bullets.
 */
public class BulletCollisionComponent extends AbstractComponent {

    private Rect2D hitbox;

    /**
     * Constructs a BulletCollisionComponent for the given entity and world.
     *
     * @param entity the bullet entity
     * @param world the game world
     */
    public BulletCollisionComponent(GameObject entity, World world) {
        super(entity, world);
        var type = entity.getType();
        var width = type.getWidth() * (Configuration.SCALE_FACTOR - 0.05);
        var height = type.getHeight() * (Configuration.SCALE_FACTOR - 0.05);
        this.hitbox = new Rect2D(width, height, this.getEntity().getPosition());
    }

    /**
     * Checks if the bullet is colliding with the given shape.
     *
     * @param shape the shape to check for collision
     * @return true if the bullet is colliding with the shape, false otherwise
     */
    public boolean isCollidingWith(Shape shape) {
        return this.hitbox.isColliding(shape);
    }

    /**
     * Updates the bullet collision component.
     * Performs collision detection and resolution for the bullet.
     */
    public void update() {
        if (!this.getEntity().getDirection().equals(Directions.NONE)) {
            this.hitbox.setPos(this.getEntity().getPosition());

            var collidedList = this.getWorld().getEntities().stream()
                    .filter(e -> !e.equals(this.getEntity()))
                    .filter(e -> e.getComponent(CollisionComponent.class).isPresent())
                    .filter(e -> e.getComponent(CollisionComponent.class).get()
                            .isCollidingWith((Shape) hitbox))
                    .toList();

            if (!collidedList.isEmpty()) {
                this.getWorld().notifyEvent(new WorldEventImpl(this.getEntity().getPosition(), this.getEntity(), WorldEventType.DEATH_EVENT));
            }

            collidedList.forEach(e -> {
                e.notifyComponents(() -> this.getEntity(), EntitiesHealthComponent.class);
                e.notifyComponents(() -> this.getEntity(), BulletHealthComponent.class);
            });
            collidedList.stream().findAny().ifPresent(e -> this.getEntity().notifyComponents(() -> e, PhysicsComponent.class));
        }
    }
}
