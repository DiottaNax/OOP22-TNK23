package it.unibo.tnk23.view.impl;

import it.unibo.tnk23.common.Configuration;
import it.unibo.tnk23.game.model.api.Round;
import it.unibo.tnk23.view.api.SidiesController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RoundInfoControllerImpl implements SidiesController {

    private final Round round;

    @FXML
    private Label randomEnemiesLabel = new Label();
    private Label aiEnemiesLabel = new Label();
    private Label missingEnemiesLabel = new Label();
    private Label roundLabel = new Label();
    private ImageView randomEnemiesImage = new ImageView();
    private ImageView aiEnemiesImage = new ImageView();
    private ImageView missingEnemiesImage = new ImageView();

    public RoundInfoControllerImpl(final Round round) {
        this.round = round;
        final Image rdmEnemies = new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/brownEnemy.gif"));
        final Image aiEnemies = new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/greyEnemy.gif"));
        final Image missingEnemies = new Image(
                ClassLoader.getSystemResourceAsStream("it/unibo/sprites/enemyTankIcon.png"));
        randomEnemiesImage.setImage(rdmEnemies);
        randomEnemiesImage.setScaleX(Configuration.TILE_SIZE);
        randomEnemiesImage.setScaleY(Configuration.TILE_SIZE);
        aiEnemiesImage.setImage(aiEnemies);
        aiEnemiesImage.setScaleX(Configuration.TILE_SIZE);
        aiEnemiesImage.setScaleY(Configuration.TILE_SIZE);
        missingEnemiesImage.setImage(missingEnemies);
        missingEnemiesImage.setScaleX(Configuration.TILE_SIZE);
        missingEnemiesImage.setScaleY(Configuration.TILE_SIZE);
    }

    @Override
    public void updateLabels() {
        roundLabel.setText(String.valueOf(this.round.getRound()));
        randomEnemiesLabel.setText("x " + this.round.getRandomEnemiesNum());
        aiEnemiesLabel.setText("x " + this.round.getAIEnemiesNum());
        missingEnemiesLabel.setText("x " + this.round.getTotalEnemies());
    }
    
}
