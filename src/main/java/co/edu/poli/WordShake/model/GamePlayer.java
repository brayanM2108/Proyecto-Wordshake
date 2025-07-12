package co.edu.poli.WordShake.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the WordShake game.
 * Manages the player's score and found words.
 * All methods and attributes are static as this represents a single game instance.
 */
public class GamePlayer {
    /** Current score of the player */
    private static int score = 0;

    /** List of words successfully found by the player */
    private static List<String> foundWords = new ArrayList<>();

    /**
     * Calculates points for a given word.
     * Points are calculated based on the word length.
     *
     * @param word The word to calculate points for
     * @return The number of points (equal to word length)
     */
    public static int pointsObtained(String word) {
        return word.length();
    }

    /**
     * Adds a word to the found words list and updates the score.
     * Only adds the word if it hasn't been found before.
     *
     * @param word The word to add to the found words list
     */
    public static void addWord(String word) {
        if (!foundWords.contains(word)) {
            foundWords.add(word);
            score += pointsObtained(word);
        }
    }

    /**
     * Resets the player's score and clears the found words list.
     * Used when starting a new game.
     */
    public static void reset() {
        score = 0;
        foundWords.clear();
    }

    /**
     * Gets the current score of the player.
     *
     * @return The player's current score
     */
    public static int getScore() {
        return score;
    }

    /**
     * Gets the list of words found by the player.
     *
     * @return List of found words
     */
    public static List<String> getFoundWords() {
        return foundWords;
    }
}