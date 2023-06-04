package it.unibo.tnk23.view.impl;

import java.util.Optional;

import it.unibo.tnk23.game.components.impl.GraphicComponent;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.view.api.SidiesController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerInfoControllerImpl implements SidiesController{

    private final World world;

    @FXML
    private Label player2Label = new Label();
    private Label player1Life = new Label();
    private Label player2Life = new Label();
    private ImageView player1Image = new ImageView();
    private ImageView player2Image = new ImageView();
    private Image plry1;
    private Optional<Image> plyr2;

    public PlayerInfoControllerImpl(final World world) {
        this.world = world;
        var player1 = this.world.getPlayer(1);
        var player2 = this.world.getPlayer(2);

        var plyr1Sprite = player1.get().getComponent(GraphicComponent.class).get().getSpriteName();
        this.plry1 = new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/" + plyr1Sprite + ".gif"));
        this.player1Image.setImage(plry1);

        if (player2.isPresent()) {
            var plyr2Sprite = player2.get().getComponent(GraphicComponent.class).get().getSpriteName();
            this.player2Label.setText("Player 2");
            this.plyr2 = Optional
                    .of(new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/" + plyr2Sprite + ".gif")));
            plyr2.ifPresent(player2Image::setImage);
        }
    }

    @Override
    public void updateLabels() {
        player1Life.setText("x " + /*player.getLife()*/null);
        if (this.world.getPlayer(2).isPresent()) {
            player2Life.setText("x " + /* player.getLife() */null);
        }
    }
    
}
