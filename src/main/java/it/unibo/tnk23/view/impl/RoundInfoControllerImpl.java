package it.unibo.tnk23.view.impl;

import it.unibo.tnk23.game.model.api.Round;
import it.unibo.tnk23.view.api.SideScenesController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The RoundInfoControllerImpl class implments the SideScenesController interface and
 * represents a controller for the round information in a game interface.
 * It handles the display and updating of round information, including enemy counts and images.
 */
public class RoundInfoControllerImpl implements SideScenesController {

    private final Round round;

    @FXML
    private final Label randomEnemiesLabel;
    @FXML
    private final Label aiEnemiesLabel;
    @FXML
    private final Label missingEnemiesLabel;
    @FXML
    private final Label roundLabel;
    @FXML
    private final ImageView randomEnemiesImage;
    @FXML
    private final ImageView aiEnemiesImage;
    @FXML
    private final ImageView missingEnemiesImage;

    private final Image rdmEnemies;
    private final Image aiEnemies;
    private final Image missingEnemies;

    /**
     * Constructs a RoundInfoControllerImpl object with the specified Round instance.
     * 
     * @param round The Round instance that represent the current round of the game.
     */
    public RoundInfoControllerImpl(final Round round) {
        this.round = round;
        this.randomEnemiesLabel = new Label();
        this.aiEnemiesLabel = new Label();
        this.missingEnemiesLabel = new Label();
        this.roundLabel = new Label();
        this.randomEnemiesImage = new ImageView();
        this.aiEnemiesImage = new ImageView();
        this.missingEnemiesImage = new ImageView();

        rdmEnemies = new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/brownEnemy.gif"));
        aiEnemies = new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/greyEnemy.gif"));
        missingEnemies = new Image(
                ClassLoader.getSystemResourceAsStream("it/unibo/sprites/enemyTankIcon.gif"));
    }

    /**
     * {@inheritDoc}
     * This method is called to refresh the display of round information in the game interface.
     */
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
