package co.edu.poli.WordShake.model;

/**
 * Manages the score calculation logic for the WordShake game.
 * Calculates points based on word length and difficulty level.
 */
public class ScoreManager {
    /** The difficulty level used for score calculation */
    private final DifficultyMode difficulty;

    /**
     * Creates a new ScoreManager with the specified difficulty level.
     *
     * @param difficulty The difficulty level to use for score calculation
     */
    public ScoreManager(DifficultyMode difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Calculates points for a given word based on its length and the difficulty level.
     * - EASY: 1 point per letter
     * - MEDIUM: 1.5 points per letter
     * - HARD: 2 points per letter
     * - TRAINING: 0 points
     *
     * @param word The word to calculate points for
     * @return The calculated points for the word
     */
    public int calculatePoints(String word) {
        int length = word.length();
        return switch (difficulty) {
            case EASY -> length;
            case MEDIUM -> (int)(length * 1.5);
            case HARD -> length * 2;
            case TRAINING -> 0;
        };
    }
}