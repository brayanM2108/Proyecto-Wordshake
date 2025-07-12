package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.DifficultyMode;
import co.edu.poli.WordShake.model.GameMode;
import co.edu.poli.WordShake.model.GameSettings;
import co.edu.poli.WordShake.model.LeagueCategory;
import co.edu.poli.WordShake.service.SetupThreeLeagueService;
import co.edu.poli.WordShake.util.SceneLoader;
import co.edu.poli.WordShake.util.SoundsUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.List;

/**
 * Controller class for managing the three-league selection screen in the WordShake game.
 * Handles user interactions for selecting exactly three leagues and starting the game.
 */
public class GameSetupThreeLeagueController {
    @FXML
    private Button btnMode1, btnMode2, btnMode3, btnMode4, btnMode5;
    @FXML
    private Button btnStartGame;

    private final SetupThreeLeagueService leagueService;
    private final GameSettings gameSettings;
    private List<Button> leagueButtons;

    /**
     * Constructs a new GameSetupThreeLeagueController and initializes the league service and game settings.
     */
    public GameSetupThreeLeagueController() {
        this.leagueService = new SetupThreeLeagueService();
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
        setupLeagueButton(btnMode1, LeagueCategory.PREMIER_LEAGUE);
        setupLeagueButton(btnMode2, LeagueCategory.LA_LIGA);
        setupLeagueButton(btnMode3, LeagueCategory.SERIE_A);
        setupLeagueButton(btnMode4, LeagueCategory.BUNDESLIGA);
        setupLeagueButton(btnMode5, LeagueCategory.LIGUE_1);
    }

    /**
     * Configures the action for a league button to toggle its selection and update button styles.
     *
     * @param button the league selection button
     * @param league the league category associated with the button
     */
    private void setupLeagueButton(Button button, LeagueCategory league) {
        button.setOnAction(e -> {
            leagueService.toggleLeagueSelection(gameSettings, league);
            leagueService.updateButtonStyles(button, leagueButtons);
        });
    }

    /**
     * Handles the event when the user clicks the start game button.
     * Validates that exactly three leagues are selected and navigates to the game screen.
     *
     * @param event the action event
     */
    @FXML
    private void onStartGame(ActionEvent event) {
        if (!gameSettings.hasThreeLeagues()) {
            leagueService.showAlert("Debes seleccionar exactamente 3 ligas antes de comenzar.");
            return;
        }

        SoundsUtils.SoundUtils.playInit();
        navigateToGame(event);
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