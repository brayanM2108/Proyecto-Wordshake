package co.edu.poli.WordShake.service;

import co.edu.poli.WordShake.controller.PlayerController;
import co.edu.poli.WordShake.model.*;
import co.edu.poli.WordShake.model.entity.Player;
import co.edu.poli.WordShake.util.GameUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.sql.SQLException;
import java.util.List;

/**
 * Service class that manages the game logic and coordinates between UI, models and database.
 * Handles game initialization, word processing, scoring and game state management.
 */
public class GameService {
    /** Controller for player database operations */
    private final PlayerController playerController;

    /** Current state of the game */
    private GameState gameState;

    /** Manages the game board and letter display */
    private final GameBoard gameBoard;

    /** Handles score calculations */
    private ScoreManager scoreManager;

    /** Utility class for game operations */
    private GameUtils gameUtils;

    /** Current game configuration settings */
    private GameSettings gameSettings;

    /**
     * Creates a new game service with default training mode settings.
     *
     * @throws SQLException if database connection fails
     */
    public GameService() throws SQLException {
        this.playerController = new PlayerController();
        this.gameState = new GameState(DifficultyMode.TRAINING);
        this.gameBoard = new GameBoard();
        this.gameUtils = new GameUtils();
        this.scoreManager = new ScoreManager(DifficultyMode.TRAINING);
    }

    /**
     * Initializes a new game with the specified settings.
     *
     * @param settings Game configuration settings
     * @param timerLabel Label to display remaining time
     * @param onTimeEnd Callback to execute when timer ends
     */
    public void initializeGame(GameSettings settings, Label timerLabel, Runnable onTimeEnd) {
        this.gameState = new GameState(settings.getDifficulty());
        this.scoreManager = new ScoreManager(settings.getDifficulty());
        this.gameSettings = settings;

        if (settings.getDifficulty().getTimeLimitInSeconds() > 0) {
            gameState.reset(timerLabel, onTimeEnd);
        } else {
            timerLabel.setText("∞");
        }
    }

    /**
     * Displays letters in the game board grid.
     *
     * @param grid The GridPane to display letters in
     */
    public void displayLettersInGrid(GridPane grid) {
        gameBoard.displayLettersInGrid(grid);
    }

    /**
     * Processes a player name entered by the user.
     * Validates the name and awards points if valid.
     *
     * @param word The player name to process
     * @return Result containing success status, message and points awarded
     * @throws SQLException if database query fails
     */
    public ProcessWordResult processWord(String word) throws SQLException {
        Player player = findPlayer(word);
        if (word.isEmpty()) {
            return new ProcessWordResult(false, "Por favor ingresa un nombre válido.");
        }

        if (!gameBoard.isValidWord(word)) {
            return new ProcessWordResult(false, "La palabra contiene letras no disponibles o en exceso.");
        }

        if (gameState.hasWord(word)) {
            return new ProcessWordResult(false, "Ya has ingresado esta palabra antes.");
        }

        if (player != null) {
            System.out.println("✅ Jugador encontrado: " + player.getName());
            int points = scoreManager.calculatePoints(word);
            gameState.addWord(word);
            return new ProcessWordResult(true, points);
        }

        return new ProcessWordResult(false, "Jugador no encontrado");
    }

    /**
     * Finds a player in the database based on current game mode and settings.
     *
     * @param playerName Name of the player to find
     * @return Player if found, null otherwise
     * @throws SQLException if database query fails
     */
    private Player findPlayer(String playerName) throws SQLException {
        return switch (gameSettings.getGameMode()) {
            case ALL_LEAGUES, TRAINING -> playerController.getByAllLeagues(playerName);
            case BY_LEAGUE -> gameSettings.getSelectedLeague() != null ?
                    playerController.getByLeague(playerName, gameSettings.getSelectedLeague().getId()) :
                    null;
            case BY_THREE_LEAGUES -> {
                int[] ids = gameSettings.getThreeLeagueIds();
                yield playerController.getByThreeLeagues(playerName, ids[0], ids[1], ids[2]);
            }
            case BY_POSITION -> gameSettings.getSelectedPosition() != null ?
                    playerController.getByPosition(playerName, gameSettings.getSelectedPosition()) :
                    null;
        };
    }

    /**
     * Shows an alert dialog with the specified parameters.
     *
     * @param title Alert window title
     * @param message Alert message content
     * @param type Type of alert to display
     */
    public void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    /**
     * Stops the current game and timer.
     */
    public void stopGame() {
        if (gameState != null) {
            gameState.stopTimer();
            gameState.setGameOver(true);
        }
    }

    /**
     * Resets the game board and timer.
     *
     * @param timerLabel Label displaying the timer
     * @param grid Game board grid to reset
     */
    public void resetGame(Label timerLabel, GridPane grid) {
        gameBoard.resetBoard();
        displayLettersInGrid(grid);
        gameState.reset(timerLabel, () -> {});
    }

    /**
     * Gets the final score for the current game.
     *
     * @return Current game score
     */
    public int getFinalScore() {
        return gameState.getScore();
    }

    /**
     * Gets list of words found in current game.
     *
     * @return List of found words
     */
    public List<String> getFoundWords() {
        return gameState.getFoundWords();
    }

    /**
     * Inner class representing the result of processing a word.
     */
    public static class ProcessWordResult {
        private final boolean success;
        private final String message;
        private final int points;

        /**
         * Creates a failed result with a message.
         *
         * @param success Whether processing succeeded
         * @param message Error or info message
         */
        public ProcessWordResult(boolean success, String message) {
            this.success = success;
            this.message = message;
            this.points = 0;
        }

        /**
         * Creates a successful result with points awarded.
         *
         * @param success Whether processing succeeded
         * @param points Points awarded
         */
        public ProcessWordResult(boolean success, int points) {
            this.success = success;
            this.message = "";
            this.points = points;
        }

        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public int getPoints() { return points; }
    }
}