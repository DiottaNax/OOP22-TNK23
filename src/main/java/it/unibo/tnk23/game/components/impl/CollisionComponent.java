package it.unibo.tnk23.game.components.impl;


import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.shape.Rect2D;
import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.common.shape.Shape;

public class CollisionComponent extends AbstractComponent {

    private Rect2D hitbox;

    public CollisionComponent(GameObject entity, World world) {
        super(entity, world);
        var type = entity.getType();
        var width = type.getWidth() * (Configuration.SCALE_FACTOR - 0.05);
        var height = type.getHeight() * (Configuration.SCALE_FACTOR - 0.05);
        this.hitbox = new Rect2D(width, height,this.entity.getPosition());
    }

    public boolean isCollidingWith(Shape shape) {
        return this.hitbox.isColliding(shape);
    }

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

            
