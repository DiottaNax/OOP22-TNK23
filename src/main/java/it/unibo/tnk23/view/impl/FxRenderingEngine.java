package it.unibo.tnk23.view.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.components.impl.GraphicComponent;
import it.unibo.tnk23.game.events.api.GameEvent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.game.model.impl.GameObjectType;
import it.unibo.tnk23.view.api.RenderingEngine;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
/**
 * FxRenderingEngine is an implementation of the RenderingEngine interface that uses JavaFX for rendering the game.
 */
public class FxRenderingEngine implements RenderingEngine<Pane> {

    private final Pane root;
    private final Map<GameObject, ImageView> sprites;
    private Map<String, Image> cachedSprites;

    /**
     * Constructs an instance of the FxRenderingEngine.
     *
     * @param world the game world
     */
    @SuppressFBWarnings (
        value = {
            "EI"
        },
            justification = "FxRenderingEngine needs the world not a copy of it "
    )
    public FxRenderingEngine(final World world) {
        this.root = new Pane();
        this.root.setStyle("-fx-background-color: #0C0C0C;");
        this.setCachedSprites(world);
        this.sprites = Collections.synchronizedMap(new HashMap<GameObject, ImageView>());
        world.getEntities().forEach(this::addSprite);
    }
    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings (
        value = {
            "EI"
        },
            justification = "this method needs to return the game pane for rendering,not a copy"
    )
    @Override
    public Pane getGamePane() {
        return this.root;
    }

    private void setCachedSprites(final World world) {
        this.cachedSprites = new HashMap<>();
        world.getPlayers().stream()
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
        this.cachedSprites.put("lifeBonus",
                new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/lifeBonus.png")));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        root.getChildren().clear();
        
        synchronized(this.sprites) {
            this.sprites.entrySet().stream().forEach(e -> {
            final var entity = e.getKey();
            final var sprite = e.getValue();
            var x = entity.getPosition().getX();
            var y = entity.getPosition().getY();
                if (GameObjectType.WALL.equals(entity.getType()) || GameObjectType.BULLET.equals(entity.getType())) {
                x += Configuration.DISPLACEMENT;
                y += Configuration.DISPLACEMENT;
            }
            sprite.setX(x * Configuration.SCALE_FACTOR);
            sprite.setY(y * Configuration.SCALE_FACTOR);
            sprite.setRotate(entity.getRotation());
            });

            root.getChildren().addAll(this.sprites.values());
        }
        
    }

    /**
     * Adds a sprite for the specified game object.
     *
     * @param entity the game object to add a sprite for
     */
    private void addSprite(final GameObject entity) {
        if (entity.getComponent(GraphicComponent.class).isPresent()) {
            final var gc = entity.getComponent(GraphicComponent.class).get();
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
    @Override
    public void handle(final GameEvent we) {
        //this.world.getEntities().stream().filter(e -> !sprites.containsKey(e)).forEach(this::addSprite);
        switch (we.getType()) {
            case DEATH_EVENT:
                this.sprites.remove(we.getEventActor());
                break;            
            default:
                this.addSprite(we.getEventActor());
                break;
        }
    }
}
