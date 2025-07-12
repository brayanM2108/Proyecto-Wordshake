package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.DifficultyMode;
import co.edu.poli.WordShake.model.GameMode;
import co.edu.poli.WordShake.model.GameSettings;
import co.edu.poli.WordShake.service.GameSetupService;
import co.edu.poli.WordShake.util.SceneLoader;
import co.edu.poli.WordShake.util.SoundsUtils.SoundUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import java.util.List;
import java.util.Map;

/**
 * Controller class for managing the game setup screen in the WordShake game.
 * Handles user interactions for selecting difficulty and game mode, and navigation to the next screen.
 */
public class GameSetupController {

    @FXML
    private Button btnEasy, btnNormal, btnHard;
    @FXML
    private Button btnMode1, btnMode2, btnMode4, btnMode5, btnMode6;
    @FXML
    private Button btnStartGame;

    private GameSettings gameSettings;
    private GameSetupService setupService;
    private Map<Button, DifficultyMode> difficultyButtonMap;
    private Map<Button, GameMode> modeButtonMap;

    /**
     * Constructs a new GameSetupController and initializes game settings and setup service.
     */
    public GameSetupController() {
        // Only initialize what does not depend on FXML
        this.gameSettings = new GameSettings(null, null);
        this.setupService = new GameSetupService();
    }

    /**
     * Initializes the controller after FXML loading.
     * Sets up button maps and event handlers.
     */
    @FXML
    public void initialize() {
        initializeMaps();
        setupDifficultyButtons();
        setupGameModeButtons();
    }

    /**
     * Initializes the mapping between buttons and their corresponding difficulty and game modes.
     */
    private void initializeMaps() {
        this.difficultyButtonMap = Map.of(
                btnEasy, DifficultyMode.EASY,
                btnNormal, DifficultyMode.MEDIUM,
                btnHard, DifficultyMode.HARD
        );

        this.modeButtonMap = Map.of(
                btnMode1, GameMode.ALL_LEAGUES,
                btnMode2, GameMode.BY_POSITION,
                btnMode4, GameMode.BY_LEAGUE,
                btnMode5, GameMode.BY_THREE_LEAGUES,
                btnMode6, GameMode.TRAINING
        );
    }

    /**
     * Sets up event handlers for difficulty selection buttons.
     * Updates the selected difficulty in game settings and button styles.
     */
    private void setupDifficultyButtons() {
        List<Button> difficultyButtons = List.of(btnEasy, btnNormal, btnHard);

        difficultyButtonMap.forEach((button, difficulty) ->
                button.setOnAction(e -> {
                    gameSettings.setDifficulty(difficulty);
                    updateButtonStyles(button, difficultyButtons);
                })
        );
    }

    /**
     * Sets up event handlers for game mode selection buttons.
     * Updates the selected game mode in game settings and button styles.
     */
    private void setupGameModeButtons() {
        List<Button> modeButtons = List.of(btnMode1, btnMode2, btnMode4, btnMode5, btnMode6);

        modeButtonMap.forEach((button, mode) ->
                button.setOnAction(e -> {
                    gameSettings.setGameMode(mode);
                    if (mode == GameMode.TRAINING) {
                        gameSettings.setDifficulty(DifficultyMode.TRAINING);
                    }
                    updateButtonStyles(button, modeButtons);
                })
        );
    }

    /**
     * Updates the styles of the selected button and resets others in the group.
     *
     * @param selectedButton the button that was selected
     * @param group the list of buttons in the group
     */
    private void updateButtonStyles(Button selectedButton, List<Button> group) {
        setupService.updateButtonStyles(selectedButton, group);
    }

    /**
     * Handles the event when the user clicks the start game button.
     * Validates selections and navigates to the next screen.
     *
     * @param event the action event
     */
    @FXML
    private void onStartGame(ActionEvent event) {
        if (!gameSettings.isValid()) {
            showAlert("Please select difficulty and game mode before starting.");
            return;
        }

        SoundUtils.playInit();
        navigateToNextScreen(event);
    }

    /**
     * Navigates to the next screen based on the selected game mode.
     *
     * @param event the action event
     */
    private void navigateToNextScreen(ActionEvent event) {
        String nextScreen = setupService.getNextScreenPath(gameSettings.getGameMode());

        switch (gameSettings.getGameMode()) {
            case BY_LEAGUE:
                navigateWithController(event, nextScreen, GameSetupLeagueController.class);
                break;
            case BY_POSITION:
                navigateWithController(event, nextScreen, GameSetupPositionController.class);
                break;
            case BY_THREE_LEAGUES:
                navigateWithController(event, nextScreen, GameSetupThreeLeagueController.class);
                break;
            default:
                navigateToGame(event);
        }
    }

    /**
     * Loads a new scene and initializes its controller with the current game settings.
     *
     * @param event the action event
     * @param fxmlPath the path to the FXML file
     * @param controllerClass the controller class to initialize
     * @param <T> the type of the controller
     */
    private <T> void navigateWithController(ActionEvent event, String fxmlPath, Class<T> controllerClass) {
        SceneLoader.loadScene(fxmlPath, (Node) event.getSource(),
                (T controller) -> setupService.initializeController(controller, gameSettings));
    }

    /**
     * Loads the main game scene and initializes it with the current game settings.
     *
     * @param event the action event
     */
    private void navigateToGame(ActionEvent event) {
        SceneLoader.loadScene("fxml/Game.fxml", (Node) event.getSource(),
                (GameController controller) -> controller.initGame(gameSettings));
    }

    /**
     * Displays a warning alert with the specified message.
     *
     * @param message the message to display
     */
    private void showAlert(String message) {
        setupService.showAlert(message);
    }
}