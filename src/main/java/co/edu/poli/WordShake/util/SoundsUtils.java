package co.edu.poli.WordShake.util;
import javafx.scene.media.AudioClip;

import java.util.Objects;

/**
 * Utility class for managing game sound effects.
 * Provides methods to play different audio clips for game events like correct answers,
 * errors, game start and game end.
 */
public class SoundsUtils {

    /**
     * Inner class that handles the actual sound functionality.
     * Contains static audio clips and methods to play them.
     */
    public static class SoundUtils {
        /** Audio clip for correct answer sound effect */
        private static final AudioClip correctSound = new AudioClip(Objects.requireNonNull(SoundUtils.class.getResource("/sounds/correct.mp3")).toString());

        /** Audio clip for error/wrong answer sound effect */
        private static final AudioClip errorSound = new AudioClip(Objects.requireNonNull(SoundUtils.class.getResource("/sounds/error.mp3")).toString());

        /** Audio clip for game end sound effect */
        private static final AudioClip endSound = new AudioClip(Objects.requireNonNull(SoundUtils.class.getResource("/sounds/endGame.mp3")).toString());

        /** Audio clip for game start sound effect */
        private static final AudioClip playSound = new AudioClip(Objects.requireNonNull(SoundUtils.class.getResource("/sounds/initGame.mp3")).toString());

        /**
         * Plays the sound effect for correct answers.
         */
        public static void playCorrect() {
            correctSound.play();
        }

        /**
         * Plays the sound effect for errors or wrong answers.
         */
        public static void playError() {
            errorSound.play();
        }

        /**
         * Plays the sound effect for game end.
         */
        public static void playEnd() {
            endSound.play();
        }

        /**
         * Plays the sound effect for game initialization/start.
         */
        public static void playInit() {
            playSound.play();
        }
    }
}