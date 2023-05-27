package it.unibo.tnk23.view.impl;

import java.util.HashMap;
import java.util.Map;
import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.model.api.GameObject;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.view.api.RenderingEngine;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class FxRenderingEngine implements RenderingEngine<SubScene> {

    private Pane root;
    private SubScene gameScene;
    private Map<GameObject, ImageView> sprites;
    private World world;

    public FxRenderingEngine(World world) {
        this.root = new Pane();
        this.world = world;
        this.gameScene = new SubScene(root, Configuration.GAME_SCENE_DIMENSION, Configuration.GAME_SCENE_DIMENSION);
        this.sprites = new HashMap<>();
    }

    @Override
    public SubScene getGamePane() {
        return this.gameScene;
    }


    @Override
    public void render() {
        root.getChildren().clear();
        root.getChildren().addAll(this.sprites.values());
        this.world.getEntities().forEach(e -> {
            this.sprites.get(e).setX(e.getPosition().getX());
                this.sprites.get(e).setY(e.getPosition().getY());
                this.sprites.get(e).setRotate(e.getRotation());
            }
    );
    }

}
