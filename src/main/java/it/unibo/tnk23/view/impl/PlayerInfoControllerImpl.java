package it.unibo.tnk23.view.impl;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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

    /*
     * I have suppressed the PMD warnings because the images 
     * and labels need to be updated during the round,
     * and therefore, they cannot be final.
     */
    @FXML
    private Label player2Label = new Label(); //NOPMD
    // suppressed as it is a false positive
    @FXML
    private Label player1Life = new Label(); //NOPMD
    // suppressed as it is a false positive
    @FXML
    private Label player2Life = new Label(); //NOPMD
    // suppressed as it is a false positive
    @FXML
    private ImageView player1Image = new ImageView(); //NOPMD
    // suppressed as it is a false positive
    @FXML
    private ImageView player2Image = new ImageView(); //NOPMD
    // suppressed as it is a false positive
    @FXML
    private final Image plry1;
    @FXML
    private final Optional<Image> plyr2;

    /**
     * Constructs a PlyerInfoControllerImpl object with the specified World instance.
     * 
     * @param world The World instance taht represent the game world.
     */
    @SuppressFBWarnings(value = { "EI2" }, justification =
    "PlayerInfoControllerImpl must store the original world it belongs to in order to update the labels and images."
    )
    public PlayerInfoControllerImpl(final World world) {
        this.world = world;
        final var player1 = this.world.getPlayer(1);
        final var player2 = this.world.getPlayer(2);

        final var plyr1Sprite = player1.get().getComponent(GraphicComponent.class).get().getSpriteName();
        this.plry1 = new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/" + plyr1Sprite + ".gif"));

        if (player2.isPresent()) {
            final var plyr2Sprite = player2.get().getComponent(GraphicComponent.class).get().getSpriteName();
            this.plyr2 = Optional
                    .of(new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/" + plyr2Sprite + ".gif")));
        } else {
            this.plyr2 = Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     * This method is called to refresh the display of player information in the game interface.
     */
    @Override
    public void updateGraphics() {
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
