package co.edu.poli.WordShake.service;

import co.edu.poli.WordShake.controller.GameSetupLeagueController;
import co.edu.poli.WordShake.controller.GameSetupPositionController;
import co.edu.poli.WordShake.controller.GameSetupThreeLeagueController;
import co.edu.poli.WordShake.model.GameMode;
import co.edu.poli.WordShake.model.GameSettings;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.util.List;
import java.util.Map;

/**
 * Service that manages the user interface logic for the game setup process.
 * Handles button style updates, determines the next setup screen based on game mode,
 * initializes setup controllers, and displays alerts.
 */
public class GameSetupService {

    /** CSS style applied to the selected button */
    private static final String SELECTED_STYLE = "-fx-border-color: green; -fx-border-width: 3px; -fx-border-radius: 3px;";

    /** Mapping of game modes to their corresponding setup screen FXML paths */
    private static final Map<GameMode, String> SCREEN_PATHS = Map.of(
            GameMode.BY_LEAGUE, "fxml/GameSetupSelectLeague.fxml",
            GameMode.BY_POSITION, "fxml/GameSetupSelectPosition.fxml",
            GameMode.BY_THREE_LEAGUES, "fxml/GameSetupSelectThreeLeagues.fxml"
    );

    /**
     * Updates the visual styles of a group of buttons, highlighting the selected one.
     *
     * @param selectedButton The button that was selected
     * @param group List of all buttons in the group
     */
    public void updateButtonStyles(Button selectedButton, List<Button> group) {
        group.forEach(btn ->
                btn.setStyle(btn.getStyle().replaceAll("-fx-border.*?;", "")));
        selectedButton.setStyle(selectedButton.getStyle() + SELECTED_STYLE);
    }

    /**
     * Gets the path of the next setup screen according to the selected game mode.
     *
     * @param gameMode The selected game mode
     * @return The corresponding FXML file path
     */
    public String getNextScreenPath(GameMode gameMode) {
        return SCREEN_PATHS.getOrDefault(gameMode, "fxml/Game.fxml");
    }

    /**
     * Initializes the controller of the next setup screen with the current game settings.
     *
     * @param controller Instance of the controller to initialize
     * @param settings Current game settings
     */
    public void initializeController(Object controller, GameSettings settings) {
        if (controller instanceof GameSetupLeagueController) {
            ((GameSetupLeagueController) controller).initGame(
                    settings.getDifficulty(),
                    settings.getGameMode()
            );
        } else if (controller instanceof GameSetupPositionController) {
            ((GameSetupPositionController) controller).initGame(
                    settings.getDifficulty(),
                    settings.getGameMode()
            );
        } else if (controller instanceof GameSetupThreeLeagueController) {
            ((GameSetupThreeLeagueController) controller).initGame(
                    settings.getDifficulty(),
                    settings.getGameMode()
            );
        }
    }

    /**
     * Displays a warning alert with the provided message.
     *
     * @param message Message to display in the alert
     */
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}