package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.DifficultyMode;
import co.edu.poli.WordShake.model.GameMode;
import co.edu.poli.WordShake.model.GameSettings;
import co.edu.poli.WordShake.model.LeagueCategory;
import co.edu.poli.WordShake.service.SetupLeagueService;
import co.edu.poli.WordShake.util.SceneLoader;
import co.edu.poli.WordShake.util.SoundsUtils.SoundUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import java.util.List;

/**
 * Controller class for managing the league selection screen in the WordShake game.
 * Handles user interactions for selecting a league and starting the game.
 */
public class GameSetupLeagueController {

    @FXML
    private Button btnMode1, btnMode2, btnMode3, btnMode4, btnMode5;
    @FXML
    private Button btnStartGame;

    private final SetupLeagueService leagueService;
    private final GameSettings gameSettings;
    private List<Button> leagueButtons;

    /**
     * Constructs a new GameSetupLeagueController and initializes the league service and game settings.
     */
    public GameSetupLeagueController() {
        this.leagueService = new SetupLeagueService();
        this.gameSettings = new GameSettings(null, null);
    }

    /**
     * Initializes the controller after FXML loading.
     * Sets up the league selection buttons.
     */
    @FXML
    public void initialize() {
        leagueButtons = List.of(btnMode1, btnMode2, btnMode3, btnMode4, btnMode5);
        setupLeagueButtons();
    }

    /**
     * Sets up the event handlers for each league selection button.
     */
    private void setupLeagueButtons() {
        // Premier League
        setupLeagueButton(btnMode1, LeagueCategory.PREMIER_LEAGUE);
        // LaLiga
        setupLeagueButton(btnMode2, LeagueCategory.LA_LIGA);
        // Serie A
        setupLeagueButton(btnMode3, LeagueCategory.SERIE_A);
        // Bundesliga
        setupLeagueButton(btnMode4, LeagueCategory.BUNDESLIGA);
        // Ligue 1
        setupLeagueButton(btnMode5, LeagueCategory.LIGUE_1);
    }

    /**
     * Configures the action for a league button to update the selected league and button styles.
     *
     * @param button the league selection button
     * @param league the league category associated with the button
     */
    private void setupLeagueButton(Button button, LeagueCategory league) {
        button.setOnAction(e -> {
            leagueService.configureLeagueMode(gameSettings, league);
            leagueService.updateLeagueButtonStyles(button, leagueButtons);
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
            leagueService.showAlert("Por favor selecciona dificultad, modo de juego y liga antes de comenzar.");
            return;
        }

        SoundUtils.playInit();
        navigateToGame(event);
    }

    /**
     * Validates that difficulty, game mode, and league have been selected.
     *
     * @return true if all settings are valid, false otherwise
     */
    private boolean validateSettings() {
        return gameSettings.getDifficulty() != null &&
                gameSettings.getGameMode() != null &&
                leagueService.validateLeagueSelection(gameSettings);
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