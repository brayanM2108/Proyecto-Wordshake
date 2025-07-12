package co.edu.poli.WordShake.service;

import co.edu.poli.WordShake.model.GameSettings;
import co.edu.poli.WordShake.model.LeagueCategory;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.util.List;

/**
 * Service class for managing league setup operations in the WordShake game.
 */
public class SetupLeagueService {

    private static final String SELECTED_STYLE = "-fx-border-color: #0be40b; -fx-border-width: 3px; -fx-border-radius: 3px;";

    /**
     * Updates the styles of league selection buttons, highlighting the selected button.
     *
     * @param selectedButton the button that was selected
     * @param allButtons the list of all league selection buttons
     */
    public void updateLeagueButtonStyles(Button selectedButton, List<Button> allButtons) {
        allButtons.forEach(btn ->
                btn.setStyle(btn.getStyle().replaceAll("-fx-border.*?;", "")));
        selectedButton.setStyle(selectedButton.getStyle() + SELECTED_STYLE);
    }

    /**
     * Configures the selected league in the game settings.
     *
     * @param settings the game settings object
     * @param league the selected league category
     */
    public void configureLeagueMode(GameSettings settings, LeagueCategory league) {
        settings.setSelectedLeague(league);
    }

    /**
     * Validates if a league has been selected in the game settings.
     *
     * @param settings the game settings object
     * @return true if a league is selected, false otherwise
     */
    public boolean validateLeagueSelection(GameSettings settings) {
        return settings.getSelectedLeague() != null;
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