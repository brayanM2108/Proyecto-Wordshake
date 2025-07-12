package co.edu.poli.WordShake.model;

import co.edu.poli.WordShake.util.GameUtils;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

import static co.edu.poli.WordShake.util.GameUtils.generateLetters;

/**
 * Represents the game board for WordShake where letters are displayed in a grid.
 * Handles letter generation, display, and word validation.
 */
public class GameBoard {
    /** The size of the board (5x5 grid) */
    private static final int BOARD_SIZE = 5;

    /** List containing the currently displayed letters on the board */
    private List<Character> letters;

    /**
     * Creates a new empty game board.
     */
    public GameBoard() {
        letters = new ArrayList<>();
    }

    /**
     * Generates a new set of random letters for the board.
     * Uses predefined rules for vowel and consonant distribution.
     */
    public void generateRandomLetters() {
        letters = generateLetters(25, 4, 2);
    }

    /**
     * Displays the generated letters in a GridPane.
     * Creates styled labels for each letter and arranges them in a 5x5 grid.
     *
     * @param grid The GridPane where letters will be displayed
     */
    public void displayLettersInGrid(GridPane grid) {
        generateRandomLetters();
        System.out.println(letters);
        int index = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (index < letters.size()) {
                    char letter = letters.get(index++);
                    Label lbl = createLetterLabel(letter);
                    grid.add(lbl, col, row);
                }
            }
        }
    }

    /**
     * Creates a styled Label for a letter.
     *
     * @param letter The character to display in the label
     * @return A styled Label containing the letter
     */
    private Label createLetterLabel(char letter) {
        Label label = new Label(String.valueOf(letter));
        label.setPrefSize(70, 57);
        label.setStyle("-fx-background-color: #8BC7EA; " +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 10; " +
                "-fx-font-size: 27px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: #50595F; " +
                "-fx-alignment: center;");
        return label;
    }

    /**
     * Sets specific letters on the board.
     *
     * @param letters List of characters to set on the board
     * @throws IllegalArgumentException if the list size is not equal to BOARD_SIZE squared
     */
    public void setLetters(List<Character> letters) {
        if (letters.size() != BOARD_SIZE * BOARD_SIZE) {
            throw new IllegalArgumentException("El tablero debe tener 25 letras");
        }
        this.letters = new ArrayList<>(letters);
        System.out.println("copia: "+ letters);
    }

    /**
     * Resets the board by clearing current letters and generating new ones.
     */
    public void resetBoard() {
        letters.clear();
        generateRandomLetters();
    }

    /**
     * Checks if a word can be formed using the letters on the board.
     *
     * @param word The word to validate
     * @return true if the word can be formed using available letters, false otherwise
     */
    public boolean isValidWord(String word) {
        return GameUtils.isValidWordWithLetters(word, this.letters);
    }
}