package co.edu.poli.WordShake.model;

import javafx.beans.property.*;

/**
 * Represents a word and its associated points in the WordShake game.
 * Uses JavaFX properties for data binding capabilities.
 */
public class WordsPoints {
    /** The word as a StringProperty for binding */
    private final StringProperty palabra;

    /** The points as an IntegerProperty for binding */
    private final IntegerProperty puntos;

    /**
     * Creates a new WordsPoints instance with the specified word and points.
     *
     * @param palabra The word to store
     * @param puntos The points associated with the word
     */
    public WordsPoints(String palabra, int puntos) {
        this.palabra = new SimpleStringProperty(palabra);
        this.puntos = new SimpleIntegerProperty(puntos);
    }

    /**
     * Gets the stored word.
     *
     * @return The word as a String
     */
    public String getPalabra() {
        return palabra.get();
    }

    /**
     * Gets the word property for binding.
     *
     * @return The StringProperty containing the word
     */
    public StringProperty palabraProperty() {
        return palabra;
    }

    /**
     * Gets the points value.
     *
     * @return The points as an integer
     */
    public int getPuntos() {
        return puntos.get();
    }

    /**
     * Gets the points property for binding.
     *
     * @return The IntegerProperty containing the points
     */
    public IntegerProperty puntosProperty() {
        return puntos;
    }
}