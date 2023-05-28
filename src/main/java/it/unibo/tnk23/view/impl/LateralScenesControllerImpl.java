package it.unibo.tnk23.view.impl;

import it.unibo.tnk23.view.api.LateralScenesController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LateralScenesControllerImpl implements LateralScenesController{
    
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

    Image plry1 = new Image(getClass().getResourceAsStream(/*name del file*/null));
    Image plyr2 = new Image(getClass().getResourceAsStream(/*name del file*/null));
    Image rdmEnemies = new Image(getClass().getResourceAsStream(/*name del file*/null));
    Image aiEnemies = new Image(getClass().getResourceAsStream(/*name del file*/null));

    @Override
    public void displayImages() {
        player1Image.setImage(plry1);
        player2Image.setImage(plyr2);
        randomEnemiesImage.setImage(rdmEnemies);
        aiEnemiesImage.setImage(aiEnemies);
    }

    @Override
    public void displayLabels() {
        roundLabel.setText(/*num round*/null);
        player1.setText("Player 1");
        /*Se ci sono due giocatori allora: */
        player2.setText("Player 2");
    }
    
    /*Funzione da chiamare nel render per aggiornare il counter di nemici*/
    public void updateLabels() { 
        randomEnemiesLabel.setText("x " + /*num nemici random*/null);
        aiEnemiesLabel.setText("x " + /*num nemici ai*/null);
        player1Life.setText("x " + /*player.getLife()*/null);
        /*Se ci sono due giocatori allora: */
        player2Life.setText("x " + /*player.getLife()*/null);
    } 
}
