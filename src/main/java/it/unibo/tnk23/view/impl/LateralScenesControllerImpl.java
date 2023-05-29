package it.unibo.tnk23.view.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.model.api.Round;
import it.unibo.tnk23.game.model.api.World;
import it.unibo.tnk23.game.model.impl.TypeObjectFactory;
import it.unibo.tnk23.view.api.LateralScenesController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LateralScenesControllerImpl implements LateralScenesController {
    
    private World world;
    private Round round;
    
    @FXML
    Label randomEnemiesLabel;
    Label aiEnemiesLabel;
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

    public LateralScenesControllerImpl(final World world, final Round round) {
        this.world = world;
        this.round = round;
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
        player1.setText("Player 1");
        if (this.world.getPlayers().size() == 2) {
            player2.setText("Player 2");
        }
        
    }
    
    /*Funzione da chiamare nel render per aggiornare il counter di nemici*/
    public void updateLabels() { 
        randomEnemiesLabel.setText("x " + null);
        aiEnemiesLabel.setText("x " + null);
        player1Life.setText("x " + /*player.getLife()*/null);
        if (this.world.getPlayers().size() == 2) {
            player2Life.setText("x " + /* player.getLife() */null);
        }
    }
}
