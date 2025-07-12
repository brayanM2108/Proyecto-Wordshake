package co.edu.poli.WordShake.service;

import co.edu.poli.WordShake.model.GameSettings;
import co.edu.poli.WordShake.model.LeagueCategory;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.util.List;

/**
 * Service class for managing the selection of three leagues in the WordShake game.
 */
public class SetupThreeLeagueService {
    private static final String SELECTED_STYLE = "-fx-border-color: #0be40b; -fx-border-width: 3px; -fx-border-radius: 3px;";
    private static final int REQUIRED_LEAGUES = 3;

    /**
     * Toggles the selection of a league for the three-league mode in the game settings.
     *
     * @param settings the game settings object
     * @param league the league category to toggle
     */
    public void toggleLeagueSelection(GameSettings settings, LeagueCategory league) {
        settings.setThreeLeagues(league);
    }

    /**
     * Retrieves the IDs of the leagues selected for the three-league mode.
     *
     * @param settings the game settings object
     * @return an array of selected league IDs
     */
    public int[] getSelectedLeaguesIds(GameSettings settings) {
        return settings.getThreeLeagueIds();
    }

    /**
     * Updates the styles of league selection buttons, allowing up to three selections.
     * Highlights selected buttons and removes highlight if deselected.
     *
     * @param selectedButton the button that was selected or deselected
     * @param allButtons the list of all league selection buttons
     */
    public void updateButtonStyles(Button selectedButton, List<Button> allButtons) {
        if (selectedButton.getStyle().contains("-fx-border-color")) {
            selectedButton.setStyle(selectedButton.getStyle().replaceAll("-fx-border.*?;", ""));
        } else {
            long selectedCount = allButtons.stream()
                    .filter(btn -> btn.getStyle().contains("-fx-border-color"))
                    .count();

            if (selectedCount < REQUIRED_LEAGUES) {
                selectedButton.setStyle(selectedButton.getStyle() + SELECTED_STYLE);
            }
        }
    }

    /**
     * Displays a warning alert with the specified message.
     *
     * @param message the message to display in the alert
     */
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}