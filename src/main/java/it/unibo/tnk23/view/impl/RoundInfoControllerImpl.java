package it.unibo.tnk23.view.impl;

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
    @FXML
    private Label aiEnemiesLabel = new Label();
    @FXML
    private Label missingEnemiesLabel = new Label();
    @FXML
    private Label roundLabel = new Label();
    @FXML
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
        aiEnemiesImage.setImage(aiEnemies);
        missingEnemiesImage.setImage(missingEnemies);
    }

    @Override
    public void updateLabels() {
        roundLabel.setText(String.valueOf(this.round.getRound()));
        randomEnemiesLabel.setText("x " + this.round.getRandomEnemiesNum());
        aiEnemiesLabel.setText("x " + this.round.getAIEnemiesNum());
        missingEnemiesLabel.setText("x " + this.round.getTotalEnemies());
    }
}
