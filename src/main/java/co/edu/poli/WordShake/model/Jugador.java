package co.edu.poli.WordShake.model;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private static int score = 0;  // Inicializamos en 0
    private static List<String> foundWords = new ArrayList<>();

    public static int pointsObtained(String word) {
        return word.length();  // Se suma un punto por cada letra
    }

    public static void addWord(String word) {
        if (!foundWords.contains(word)) { // Evita palabras repetidas
            foundWords.add(word);
            score += pointsObtained(word); // Suma los puntos solo si es válida
        }
    }

    public static int getScore() {
        return score;
    }

    public static List<String> getFoundWords() {
        return foundWords;
    }
}
