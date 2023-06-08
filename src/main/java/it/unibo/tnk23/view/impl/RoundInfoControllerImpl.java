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
    private final Image rdmEnemies;
    private final Image aiEnemies;
    private final Image missingEnemies;

    /*
     * I have suppressed the PMD warnings because the images 
     * and labels need to be updated during the round,
     * and therefore, they cannot be final.
     */
    @FXML
    private Label randomEnemiesLabel  = new Label(); //NOPMD
    // suppressed as it is a false positive
    @FXML
    private Label aiEnemiesLabel  = new Label(); //NOPMD
    // suppressed as it is a false positive
    @FXML
    private Label missingEnemiesLabel  = new Label(); //NOPMD
    // suppressed as it is a false positive
    @FXML
    private Label roundLabel  = new Label(); //NOPMD
    // suppressed as it is a false positive
    @FXML
    private ImageView randomEnemiesImage  = new ImageView(); //NOPMD
    // suppressed as it is a false positive
    @FXML
    private ImageView aiEnemiesImage  = new ImageView(); //NOPMD
    // suppressed as it is a false positive
    @FXML
    private ImageView missingEnemiesImage  = new ImageView(); //NOPMD
    // suppressed as it is a false positive

    /**
     * Constructs a RoundInfoControllerImpl object with the specified Round instance.
     * 
     * @param round The Round instance that represent the current round of the game.
     */
    public RoundInfoControllerImpl(final Round round) {
        this.round = round;
        this.rdmEnemies = new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/brownEnemy.gif"));
        this.aiEnemies = new Image(ClassLoader.getSystemResourceAsStream("it/unibo/sprites/greyEnemy.gif"));
        this.missingEnemies = new Image(
                ClassLoader.getSystemResourceAsStream("it/unibo/sprites/enemyTankIcon.gif"));
        
    }

    /**
     * {@inheritDoc}
     * This method is called to refresh the display of round information in the game interface.
     */
    @Override
    public void updateGraphic() {
        randomEnemiesImage.setImage(this.rdmEnemies);
        aiEnemiesImage.setImage(this.aiEnemies);
        missingEnemiesImage.setImage(this.missingEnemies);
        roundLabel.setText(String.valueOf(this.round.getRound()));
        randomEnemiesLabel.setText("x " + this.round.getRandomEnemiesNum());
        aiEnemiesLabel.setText("x " + this.round.getAIEnemiesNum());
        missingEnemiesLabel.setText("x " + this.round.getTotalEnemies());
    }
}
