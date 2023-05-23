package it.unibo.tnk23.game.components.impl;


import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.common.Point2D;
import it.unibo.tnk23.common.shape.Rect2D;
import it.unibo.tnk23.game.components.api.AbstractComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.world.api.World;
import it.unibo.tnk23.common.shape.Shape;

public class CollisionComponent extends AbstractComponent {

    private Rect2D hitbox;
   
    public CollisionComponent(GameObject entity, World world) {
        super(entity, world);
        var type = entity.getType();
        var width = type.getWidth() * Configuration.SCALE_FACTOR;
        var height = type.getheight() * Configuration.SCALE_FACTOR;
        this.hitbox = new Rect2D(width, height,
                new Point2D(entity.getPosition().getX() + (width / 2), entity.getPosition().getY() + (height / 2)));
    }

    public boolean isCollidingWith(Shape shape) {
        return this.hitbox.isColliding(shape);
    }
    
    public void update() {

        world.getEntities().stream().filter(e -> e.getComponent(CollisionComponent.class).isPresent())
                .filter(e -> ((CollisionComponent) e.getComponent(CollisionComponent.class).get())
                        .isCollidingWith((Shape) hitbox));
    }
}
