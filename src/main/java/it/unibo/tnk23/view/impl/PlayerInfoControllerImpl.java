package it.unibo.tnk23.view.impl;

import java.util.Optional;

import it.unibo.tnk23.game.components.impl.EntitiesHealthComponent;
import it.unibo.tnk23.game.components.impl.GraphicComponent;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.view.api.SideScenesController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The PlayerInfoControllerImpl class implements the SideScenesController interface and
 * represents a controller for the player information in a game interface.
 * It handles the display and updating of player information, including health and sprites.
 */
public class PlayerInfoControllerImpl implements SideScenesController {

    private final World world;

    @FXML
    private Label player2Label = new Label();
    @FXML
    private Label player1Life = new Label();
    @FXML
    private Label player2Life = new Label();
    @FXML
    private ImageView player1Image = new ImageView();
    @FXML
    private ImageView player2Image = new ImageView();
    @FXML
    private Image plry1;
    @FXML
    private Optional<Image> plyr2;

    /**
     * Constructs a PlyerInfoControllerImpl object with the specified World instance.
     * 
     * @param world The World instance taht represent the game world.
     */
    public PlayerInfoControllerImpl(final World world) {
        this.world = world;
        var player1 = this.world.getPlayer(1);
        var player2 = this.world.getPlayer(2);

        var plyr1Sprite = player1.get().getComponent(GraphicComponent.class).get().getSpriteName();
        this.plry1 = new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/" + plyr1Sprite + ".gif"));

        if (player2.isPresent()) {
            var plyr2Sprite = player2.get().getComponent(GraphicComponent.class).get().getSpriteName();
            this.plyr2 = Optional
                    .of(new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/" + plyr2Sprite + ".gif")));
        }
    }

    /**
     * {@inheritDoc}
     * This method is called to refresh the display of player information in the game interface.
     */
    @Override
    public void updateGraphic() {
        player1Life.setText(
                "x " + this.world.getPlayer(1).get().getComponent(EntitiesHealthComponent.class).get().getHealth());
        this.player1Image.setImage(plry1);
        if (this.world.getPlayer(2).isPresent()) {
            this.player2Label.setText("Player 2");
            plyr2.ifPresent(player2Image::setImage);
            player2Label.setText("Player 2:");
            player2Life.setText(
                    "x " + this.world.getPlayer(2).get().getComponent(EntitiesHealthComponent.class).get().getHealth());
        }
    }

}
