package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.DifficultyMode;
import co.edu.poli.WordShake.model.GameMode;
import co.edu.poli.WordShake.model.GameSettings;
import co.edu.poli.WordShake.model.PositionCategory;
import co.edu.poli.WordShake.service.SetupPositionService;
import co.edu.poli.WordShake.util.SceneLoader;
import co.edu.poli.WordShake.util.SoundsUtils.SoundUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import java.util.List;

/**
 * Controller class for managing the position selection screen in the WordShake game.
 * Handles user interactions for selecting a player position and starting the game.
 */
public class GameSetupPositionController {

    @FXML
    private Button btnMode1, btnMode2, btnMode3, btnMode4;
    @FXML
    private Button btnStartGame;

    private final SetupPositionService positionService;
    private final GameSettings gameSettings;
    private List<Button> positionButtons;

    /**
     * Constructs a new GameSetupPositionController and initializes the position service and game settings.
     */
    public GameSetupPositionController() {
        this.positionService = new SetupPositionService();
        this.gameSettings = new GameSettings(null, null);
    }

    /**
     * Initializes the controller after FXML loading.
     * Sets up the position selection buttons.
     */
    @FXML
    public void initialize() {
        positionButtons = List.of(btnMode1, btnMode2, btnMode3, btnMode4);
        setupPositionButtons();
    }

    /**
     * Sets up the event handlers for each position selection button.
     */
    private void setupPositionButtons() {
        // Goalkeeper
        setupPositionButton(btnMode1, PositionCategory.GOALKEEPER);
        // Defender
        setupPositionButton(btnMode2, PositionCategory.DEFENDER);
        // Midfielder
        setupPositionButton(btnMode3, PositionCategory.MIDFIELDER);
        // Forward
        setupPositionButton(btnMode4, PositionCategory.FORWARD);
    }

    /**
     * Configures the action for a position button to update the selected position and button styles.
     *
     * @param button the position selection button
     * @param position the position category associated with the button
     */
    private void setupPositionButton(Button button, String position) {
        button.setOnAction(e -> {
            positionService.configurePositionMode(gameSettings, position);
            positionService.updatePositionButtonStyles(button, positionButtons);
        });
    }

    /**
     * Initializes the game settings with the selected difficulty and game mode.
     *
     * @param difficulty the selected difficulty mode
     * @param mode the selected game mode
     */
    public void initGame(DifficultyMode difficulty, GameMode mode) {
        gameSettings.setDifficulty(difficulty);
        gameSettings.setGameMode(mode);
    }

    /**
     * Handles the event when the user clicks the start game button.
     * Validates the selections and navigates to the game screen.
     *
     * @param event the action event
     */
    @FXML
    private void onStartGame(ActionEvent event) {
        if (!validateSettings()) {
            positionService.showAlert("Por favor selecciona dificultad, modo de juego y posición antes de comenzar.");
            return;
        }

        SoundUtils.playInit();
        navigateToGame(event);
    }

    /**
     * Validates that difficulty, game mode, and position have been selected.
     *
     * @return true if all settings are valid, false otherwise
     */
    private boolean validateSettings() {
        return gameSettings.getDifficulty() != null &&
                gameSettings.getGameMode() != null &&
                positionService.validatePositionSelection(gameSettings);
    }

    /**
     * Navigates to the main game screen, passing the current game settings.
     *
     * @param event the action event
     */
    private void navigateToGame(ActionEvent event) {
        SceneLoader.loadScene("fxml/Game.fxml",
                (Node) event.getSource(),
                (GameController controller) -> controller.initGame(gameSettings));
    }
}