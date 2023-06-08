package it.unibo.tnk23.view.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.components.impl.GraphicComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.game.model.impl.TypeObjectFactory;
import it.unibo.tnk23.view.api.RenderingEngine;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
/**
 * FxRenderingEngine is an implementation of the RenderingEngine interface that uses JavaFX for rendering the game.
 */
public class FxRenderingEngine implements RenderingEngine<Pane> {

    private Pane root;
    private final Map<GameObject, ImageView> sprites;
    private final World world;
    private Map<String, Image> cachedSprites;

    /**
     * Constructs an instance of the FxRenderingEngine.
     *
     * @param world the game world
     */
    public FxRenderingEngine(final World world) {
        this.root = new Pane();
        this.world = world;
        this.root.setStyle("-fx-background-color: #0C0C0C;");
        this.setCachedSprites();
        this.sprites = new HashMap<>();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Pane getGamePane() {
        return this.root;
    }

    private void setCachedSprites() {
        this.cachedSprites = new HashMap<>();
        this.world.getPlayers().stream()
                .filter(p -> p.getComponent(GraphicComponent.class).isPresent())
                .map(p -> p.getComponent(GraphicComponent.class).get())
                .forEach(g -> this.cachedSprites.put(g.getSpriteName(),
                        new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/" + g.getSpriteName() + ".gif"))));
        this.cachedSprites.put("wall", new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/wall.png")));
        this.cachedSprites.put("destroyableWall",
                new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/destroyableWall.png")));
        this.cachedSprites.put("brownEnemy",
                new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/brownEnemy.gif")));
        this.cachedSprites.put("greyEnemy",
                new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/greyEnemy.gif")));
        this.cachedSprites.put("superTank",
                new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/superTank.gif")));
        this.cachedSprites.put("tower",
                new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/tower.png")));
        this.cachedSprites.put("bullet",
                new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/bullet.png")));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.update();
        root.getChildren().clear();
        this.world.getEntities().stream().filter(this.sprites::containsKey).forEach(e -> {
            var x = e.getPosition().getX();
            var y = e.getPosition().getY();
            if (TypeObjectFactory.isObstacle(e.getType()) || TypeObjectFactory.isBullet(e.getType())) {
                x += Configuration.DISPLACEMENT;
                y += Configuration.DISPLACEMENT;
            }
            this.sprites.get(e).setX(x);
            this.sprites.get(e).setY(y);
            this.sprites.get(e).setRotate(e.getRotation());
        });
        this.sprites.keySet().removeIf(k -> !this.world.getEntities().contains(k));
        root.getChildren().addAll(this.sprites.values());
    }

    /**
     * Adds a sprite for the specified game object.
     *
     * @param entity the game object to add a sprite for
     */
    public void addSprite(final GameObject entity) {
        if (!sprites.containsKey(entity)) {
            var gc = entity.getComponent(GraphicComponent.class).get();
            this.sprites.put(entity, new ImageView());
            this.sprites.get(entity).setImage(this.cachedSprites.get(gc.getSpriteName()));
            this.sprites.get(entity).setScaleX(Configuration.SCALE_FACTOR);
            this.sprites.get(entity).setScaleY(Configuration.SCALE_FACTOR);
        }
    }
    /**
     * Updates the sprites based on the current state of the game world.
     * This method adds sprites for new game objects that do not have a corresponding sprite yet.
     */
    private void update() {
        this.world.getEntities().stream().filter(e -> !sprites.containsKey(e)).forEach(this::addSprite);
    }

}
