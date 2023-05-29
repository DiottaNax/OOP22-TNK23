package it.unibo.tnk23.view.impl;

import java.util.Optional;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.components.impl.GraphicComponent;
import it.unibo.tnk23.game.model.api.Round;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.view.api.SideScenesController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SideScenesControllerImpl implements SideScenesController {
    
    private World world;
    private Round round;
    
    @FXML
    private Label randomEnemiesLabel;
    private Label aiEnemiesLabel;
    private Label missingEnemiesLabel;
    private Label roundLabel;
    private Label player1;
    private Label player2;
    private Label player1Life;
    private Label player2Life;

    private ImageView player1Image;
    private ImageView player2Image;
    private ImageView randomEnemiesImage;
    private ImageView aiEnemiesImage;
    private ImageView missingEnemiesImage;

    private Image plry1;
    private Optional<Image> plyr2;
    private Image rdmEnemies = new Image(getClass().getResourceAsStream("it/unibo/sprites/brownEnemy.gif"));
    private Image aiEnemies = new Image(getClass().getResourceAsStream("it/unibo/sprites/greyEnemy.gif"));
    private Image missingEnemies = new Image(getClass().getResourceAsStream("it/unibo/prites/enemyTankIcon.png"));

    public SideScenesControllerImpl(final Round round) {
        this.round = round;
        this.world = this.round.getWorld();
        plry1 = new Image(getClass().getResourceAsStream("it/unibo/sprites/"
                + (((GraphicComponent) this.world.getPlayer(1).get()
                        .getComponent(GraphicComponent.class).get())
                        .getSpriteName())));
        var player2 = this.world.getPlayer(2);            
        plyr2 = Optional
                .of(new Image(getClass().getResourceAsStream("it/unibo/sprites/"
                + (player2.isPresent()
                        ? ((GraphicComponent) player2.get()
                                .getComponent(GraphicComponent.class).get())
                                .getSpriteName()
                        : ""))));
    }

    @Override
    public void displayImages() {
        player1Image.setImage(plry1);
        plyr2.ifPresent(player2Image::setImage);
        randomEnemiesImage.setImage(rdmEnemies);
        aiEnemiesImage.setImage(aiEnemies);
        missingEnemiesImage.setImage(missingEnemies);
        player1Image.setScaleX(Configuration.TILE_SIZE);
        player1Image.setScaleY(Configuration.TILE_SIZE);
        player2Image.setScaleX(Configuration.TILE_SIZE);
        player2Image.setScaleY(Configuration.TILE_SIZE);
        randomEnemiesImage.setScaleX(Configuration.TILE_SIZE);
        randomEnemiesImage.setScaleY(Configuration.TILE_SIZE);
        aiEnemiesImage.setScaleX(Configuration.TILE_SIZE);
        aiEnemiesImage.setScaleY(Configuration.TILE_SIZE);
        missingEnemiesImage.setScaleX(Configuration.TILE_SIZE);
        missingEnemiesImage.setScaleY(Configuration.TILE_SIZE);
    }

    @Override
    public void displayLabels() {
        roundLabel.setText(String.valueOf(round.getRound()));
        randomEnemiesLabel.setText("x " + this.round.getRandomEnemiesNum());
        aiEnemiesLabel.setText("x " + this.round.getAIEnemiesNum());
        player1.setText("Player 1");
        if (this.world.getPlayers().size() == 2) {
            player2.setText("Player 2");
        }
    }
    
    /*Funzione da chiamare nel render per aggiornare il counter di nemici*/
    public void updateLabels() {
        missingEnemiesLabel.setText("x " + this.round.getEnemies().size());
        player1Life.setText("x " + /*player.getLife()*/null);
        if (this.world.getPlayers().size() == 2) {
            player2Life.setText("x " + /* player.getLife() */null);
        }
    }
}
