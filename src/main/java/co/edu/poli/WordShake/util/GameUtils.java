package co.edu.poli.WordShake.util;

import javafx.application.Platform;
import javafx.scene.control.Label;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

/**
 * Utility class for managing game mechanics like letter generation, word validation and timer.
 */
public class GameUtils {
    /** Timer object to control countdown */
    private  Timer timer;

    /** Stores the remaining time in seconds */
    private int remainingTime;


    /**
     * Generates a random list of letters with specific rules for vowels and consonants.
     * The method ensures there are specific number of vowels repeated a certain number of times,
     * and consonants with a maximum of 3 repetitions each.
     *
     * @param totalLetters Total number of letters to generate
     * @param numVowelsSelected Number of different vowels to select
     * @param vowelRepetitions How many times each selected vowel should be repeated
     * @return A shuffled list of characters following the specified rules
     */
    static public List<Character> generateLetters(int totalLetters, int numVowelsSelected, int vowelRepetitions) {
        String vowels = "AEIOU";
        String consonants = "BCDFGHJKLMNÑPQRSTVWXYZ";
        Map<Character, Integer> letterCounter = new HashMap<>();
        Random r = new Random();
        List<Character> letters = new ArrayList<>();

        // Select 3 random vowels without repetition
        Set<Character> selectedVowels = new HashSet<>();
        while (selectedVowels.size() < numVowelsSelected) {
            char v = vowels.charAt(r.nextInt(vowels.length()));
            selectedVowels.add(v);
        }

        // Add each selected vowel 3 times to the list
        for (char v : selectedVowels) {
            for (int i = 0; i < vowelRepetitions; i++) {
                letters.add(v);
            }
        }

        // Calculate how many consonants are needed to complete total letters
        int numConsonants = totalLetters - (numVowelsSelected * vowelRepetitions);

        // Add random consonants until total letters is reached
        while (letters.size() < totalLetters) {
            char c = consonants.charAt(r.nextInt(consonants.length()));

            // Ensure the consonant is not repeated more than 3 times
            if (letterCounter.getOrDefault(c, 0) < 3) {
                letters.add(c);
                letterCounter.put(c, letterCounter.getOrDefault(c, 0) + 1);
            }
        }

        // Shuffle letters to avoid predictable patterns
        Collections.shuffle(letters);

        return letters;
    }

    /**
     * Validates if a word can be formed using the available generated letters.
     * Each letter from the generated list can only be used once.
     *
     * @param word The word to validate
     * @param generatedLetters The list of available letters
     * @return true if the word can be formed with the available letters, false otherwise
     */
    public static boolean isValidWordWithLetters(String word, List<Character> generatedLetters) {
        List<Character> available = new ArrayList<>(generatedLetters); // Mutable copy

        for (char c : word.toUpperCase().toCharArray()) {
            if (!available.contains(c)) {
                return false; // Letter not available (or already used more times than allowed)
            } else {
                available.remove((Character) c); // "Consume" an available letter
            }
        }
        return true; // All letters are in the list and in valid quantity
    }

    /**
     * Starts a countdown timer that updates a label with the remaining time.
     * If a timer is already running, it will be cancelled before starting the new one.
     * The timer updates every second and displays time in MM:SS format.
     *
     * @param seconds Initial number of seconds for countdown
     * @param timeLabel Label component to display the remaining time
     * @param onTimeEnd Callback to execute when timer reaches zero
     */
    public void startTimer(int seconds, Label timeLabel, Runnable onTimeEnd) {
        if (timer != null) {
            System.out.println("Timer anterior cancelado: " + timer);
            timer.cancel();
        }

        remainingTime = seconds;
        timer = new Timer();
        System.out.println("Nuevo timer iniciado: " + timer);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    System.out.println("Tiempo restante: " + remainingTime + " segundos");
                    if (remainingTime <= 0) {
                        System.out.println("Timer finalizado");
                        timer.cancel();
                        timeLabel.setText("00:00");
                        onTimeEnd.run();
                    } else {
                        int minutes = remainingTime / 60;
                        int seconds = remainingTime % 60;
                        timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
                        remainingTime--;
                    }
                });
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
    /**
     * Stops and cancels the currently running timer if it exists.
     */
    public void stopTimer() {
        if (timer != null) {
            System.out.println("Deteniendo timer: " + timer);
            timer.cancel();
            System.out.println("Timer cancelado");
        }
    }


}