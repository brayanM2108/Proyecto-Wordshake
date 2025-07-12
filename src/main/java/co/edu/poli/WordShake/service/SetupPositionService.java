package co.edu.poli.WordShake.service;
import co.edu.poli.WordShake.model.GameSettings;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.util.List;

/**
 * Service class for managing position setup operations in the WordShake game.
 */
public class SetupPositionService {

    private static final String SELECTED_STYLE = "-fx-border-color: #0be40b; -fx-border-width: 3px; -fx-border-radius: 3px;";

    /**
     * Updates the styles of position selection buttons, highlighting the selected button.
     *
     * @param selectedButton the button that was selected
     * @param allButtons the list of all position selection buttons
     */
    public void updatePositionButtonStyles(Button selectedButton, List<Button> allButtons) {
        allButtons.forEach(btn ->
                btn.setStyle(btn.getStyle().replaceAll("-fx-border.*?;", "")));
        selectedButton.setStyle(selectedButton.getStyle() + SELECTED_STYLE);
    }

    /**
     * Configures the selected position in the game settings.
     *
     * @param settings the game settings object
     * @param position the selected position
     */
    public void configurePositionMode(GameSettings settings, String position) {
        settings.setSelectedPosition(position);
    }

    /**
     * Validates if a position has been selected in the game settings.
     *
     * @param settings the game settings object
     * @return true if a position is selected, false otherwise
     */
    public boolean validatePositionSelection(GameSettings settings) {
        return settings.getSelectedPosition() != null;
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
