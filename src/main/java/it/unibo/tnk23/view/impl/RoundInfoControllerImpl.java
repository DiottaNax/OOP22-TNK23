package it.unibo.tnk23.view.impl;

import it.unibo.tnk23.game.model.api.Round;
import it.unibo.tnk23.view.api.SideScenesController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RoundInfoControllerImpl implements SideScenesController {

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
    @FXML
    private ImageView aiEnemiesImage = new ImageView();
    @FXML
    private ImageView missingEnemiesImage = new ImageView();

    private final Image rdmEnemies;
    private final Image aiEnemies;
    private final Image missingEnemies;

    public RoundInfoControllerImpl(final Round round) {
        this.round = round;
        rdmEnemies = new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/brownEnemy.gif"));
        aiEnemies = new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/greyEnemy.gif"));
        missingEnemies = new Image(
                ClassLoader.getSystemResourceAsStream("it/unibo/sprites/enemyTankIcon.png"));
    }

    @Override
    public void updateGraphic() {
        randomEnemiesImage.setImage(rdmEnemies);
        aiEnemiesImage.setImage(aiEnemies);
        missingEnemiesImage.setImage(missingEnemies);
        roundLabel.setText(String.valueOf(this.round.getRound()));
        randomEnemiesLabel.setText("x " + this.round.getRandomEnemiesNum());
        aiEnemiesLabel.setText("x " + this.round.getAIEnemiesNum());
        missingEnemiesLabel.setText("x " + this.round.getTotalEnemies());
    }
}
