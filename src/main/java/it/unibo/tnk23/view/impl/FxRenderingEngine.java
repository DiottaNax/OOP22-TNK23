package it.unibo.tnk23.view.impl;

import java.util.HashMap;
import java.util.Map;
import it.unibo.tnk23.game.components.impl.GraphicComponent;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.view.api.RenderingEngine;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class FxRenderingEngine implements RenderingEngine<Pane> {

    private Pane root;
    private final Map<GameObject, ImageView> sprites;
    private final World world;
    private Map<String, Image> cachedSprites;

    public FxRenderingEngine(final World world) {
        this.root = new Pane();
        this.setCachedSprites();
        this.world = world;
        this.sprites = new HashMap<>();

    }

    @Override
    public Pane getGamePane() {
        return this.root;
    }

    @Override
    public void render() {
        this.update();
        root.getChildren().clear();
        this.world.getEntities().forEach(e -> {
            this.sprites.get(e).setX(e.getPosition().getX());
            this.sprites.get(e).setY(e.getPosition().getY());
            this.sprites.get(e).setRotate(e.getRotation());
        });
        root.getChildren().addAll(this.sprites.values());
    }

    private void setCachedSprites() {
        this.cachedSprites = new HashMap<>();
        this.cachedSprites.put("pink",
                new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/pinkPlayer.gif")));
        this.cachedSprites.put("wall", new Image(ClassLoader.getSystemResourceAsStream("it/unibo/wall/.png")));
        this.cachedSprites.put("destroyableWall",
                new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/destroyableWall.png")));
        this.cachedSprites.put("brownEnemy",
                new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/brownEnemy.gif")));
        this.cachedSprites.put("greyEnemy",
                new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/greyEnemy.gif")));
        this.cachedSprites.put("superTank",
                new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/superTank.gif")));
    }
    
    public void addSprite(final GameObject entity) {
        if (!sprites.containsKey(entity)) {
            var gc = (GraphicComponent) entity.getComponent(GraphicComponent.class).get();
            this.sprites.put(entity, new ImageView(this.cachedSprites.get(gc.getSpriteName())));  
        }
    }
    
    public void update() {
        this.world.getEntities().stream().filter(e -> !sprites.containsKey(e)).forEach(this::addSprite);
        this.sprites.keySet().stream()
                .filter(k -> !this.world.getEntities().contains(k))
                .forEach(this.sprites::remove);
    }

}
