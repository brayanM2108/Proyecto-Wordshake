package co.edu.poli.WordShake.model;

import co.edu.poli.WordShake.util.GameUtils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the current state of a WordShake game session.
 * Handles score tracking, word collection, timer management and game over state.
 */
public class GameState {
    /** Current game score as an observable property */
    private final IntegerProperty score = new SimpleIntegerProperty(0);

    /** List of words successfully found by the player */
    private final List<String> foundWords = new ArrayList<>();

    /** Handles score calculation based on difficulty */
    private final ScoreManager scoreManager;

    /** Current letters available on the game board */
    private List<Character> currentLetters;

    /** Flag indicating if the game has ended */
    private boolean isGameOver;

    /** Current difficulty level of the game */
    private final DifficultyMode difficulty;

    /** Utility class for game functionality */
    private final GameUtils gameUtils = new GameUtils();

    /**
     * Creates a new game state with specified difficulty.
     *
     * @param difficulty The difficulty level for this game
     */
    public GameState(DifficultyMode difficulty) {
        this.difficulty = difficulty;
        this.scoreManager = new ScoreManager(difficulty);
    }

    /**
     * Adds a word to the found words list and updates score.
     * Only adds if the word hasn't been found before.
     *
     * @param word The word to add
     */
    public void addWord(String word) {
        if (!foundWords.contains(word)) {
            foundWords.add(word);
            score.set(score.get() + scoreManager.calculatePoints(word));
        }
    }

    /**
     * Checks if a word has already been found.
     *
     * @param word The word to check
     * @return true if the word was previously found
     */
    public boolean hasWord(String word) {
        return foundWords.contains(word);
    }

    /**
     * Stops the game timer.
     */
    public void stopTimer() {
        gameUtils.stopTimer();
    }

    /**
     * Resets the game state and starts a new timer.
     * Does nothing if the game is already over.
     *
     * @param timerLabel Label to display remaining time
     * @param onTimeEnd Callback to execute when timer ends
     */
    public void reset(Label timerLabel, Runnable onTimeEnd) {
        if (isGameOver) {
            return;
        }
        score.set(0);
        foundWords.clear();
        isGameOver = false;
        gameUtils.startTimer(difficulty.getTimeLimitInSeconds(), timerLabel, onTimeEnd);
    }

    /**
     * Gets the current score.
     *
     * @return Current game score
     */
    public int getScore() {
        return score.get();
    }

    /**
     * Gets the score property for binding.
     *
     * @return Score as IntegerProperty
     */
    public IntegerProperty scoreProperty() {
        return score;
    }

    /**
     * Gets the list of found words.
     *
     * @return List of found words
     */
    public List<String> getFoundWords() {
        return foundWords;
    }

    /**
     * Gets current letters on the game board.
     *
     * @return List of current letters
     */
    public List<Character> getCurrentLetters() {
        return currentLetters;
    }

    /**
     * Sets the current letters on the game board.
     *
     * @param currentLetters New list of letters
     */
    public void setCurrentLetters(List<Character> currentLetters) {
        this.currentLetters = currentLetters;
    }

    /**
     * Checks if the game is over.
     *
     * @return true if game has ended
     */
    public boolean isGameOver() {
        return isGameOver;
    }

    /**
     * Sets the game over state.
     *
     * @param gameOver true to end the game
     */
    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}