package it.unibo.tnk23.view.impl;

import it.unibo.tnk23.common.Configuration;
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
    Label randomEnemiesLabel;
    Label aiEnemiesLabel;
    Label lastEnemies;
    Label roundLabel;
    Label player1;
    Label player2;
    Label player1Life;
    Label player2Life;

    ImageView player1Image;
    ImageView player2Image;
    ImageView randomEnemiesImage;
    ImageView aiEnemiesImage;

    Image plry1 = new Image(getClass().getResourceAsStream("it/unibo/sprites/PinkPlayer.gif"));
    Image plyr2 = new Image(getClass().getResourceAsStream("it/unibo/sprites/PinkPlayer.gif"));
    Image rdmEnemies = new Image(getClass().getResourceAsStream("it/unibo/sprites/brownEnemy.gif"));
    Image aiEnemies = new Image(getClass().getResourceAsStream("it/unibo/sprites/greyEnemy.gif"));

    public SideScenesControllerImpl(final Round round) {
        this.round = round;
        this.world = this.round.getWorld();
    }

    @Override
    public void displayImages() {
        player1Image.setImage(plry1);
        player2Image.setImage(plyr2);
        randomEnemiesImage.setImage(rdmEnemies);
        aiEnemiesImage.setImage(aiEnemies);
        player1Image.setScaleX(Configuration.TILE_SIZE);
        player1Image.setScaleY(Configuration.TILE_SIZE);
        player2Image.setScaleX(Configuration.TILE_SIZE);
        player2Image.setScaleY(Configuration.TILE_SIZE);
        randomEnemiesImage.setScaleX(Configuration.TILE_SIZE);
        randomEnemiesImage.setScaleY(Configuration.TILE_SIZE);
        aiEnemiesImage.setScaleX(Configuration.TILE_SIZE);
        aiEnemiesImage.setScaleY(Configuration.TILE_SIZE);
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
        lastEnemies.setText("x " + this.round.getEnemies().size());
        player1Life.setText("x " + /*player.getLife()*/null);
        if (this.world.getPlayers().size() == 2) {
            player2Life.setText("x " + /* player.getLife() */null);
        }
    }
}
