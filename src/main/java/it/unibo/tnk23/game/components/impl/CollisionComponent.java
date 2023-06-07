package it.unibo.tnk23.game.components.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.shape.Rect2D;
import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.common.shape.Shape;

/**
 * Represents a collision component for game entities.
 * Handles the collision detection and resolution for entities.
 */
public class CollisionComponent extends AbstractComponent {

    private Rect2D hitbox;

    /**
     * Constructs a CollisionComponent for the given entity and world.
     *
     * @param entity the entity to which this component belongs
     * @param world the game world
     */
    public CollisionComponent(GameObject entity, World world) {
        super(entity, world);
        var type = entity.getType();
        var width = type.getWidth() * (Configuration.SCALE_FACTOR - 0.05);
        var height = type.getHeight() * (Configuration.SCALE_FACTOR - 0.05);
        this.hitbox = new Rect2D(width, height, this.entity.getPosition());
    }

    /**
     * Checks if the entity is colliding with the given shape.
     *
     * @param shape the shape to check for collision
     * @return true if the entity is colliding with the shape, false otherwise
     */
    public boolean isCollidingWith(Shape shape) {
        return this.hitbox.isColliding(shape);
    }

    /**
     * Updates the collision component.
     * Performs collision detection and resolution for the entity.
     */
    public void update() {
        if (!entity.getDirection().equals(Directions.NONE)) {
            this.hitbox.setPos(this.entity.getPosition());

            var collidedList = world.getEntities().stream()
                    .filter(e -> !e.equals(entity))
                    .filter(e -> e.getComponent(CollisionComponent.class).isPresent())
                    .filter(e -> e.getComponent(CollisionComponent.class).get()
                            .isCollidingWith((Shape) hitbox))
                    .toList();

            collidedList.forEach(e -> {
                e.notifyComponents(() -> entity, EntitiesHealthComponent.class);
                e.notifyComponents(() -> entity, BulletHealthComponent.class);
            });
            collidedList.stream().findAny().ifPresent(e -> entity.notifyComponents(() -> e, PhysicsComponent.class));
        }
    }
}
