package co.edu.poli.WordShake.util;
import javafx.scene.media.AudioClip;
public class SoundsUtils {


    public class SoundUtils {

        private static final AudioClip correctSound = new AudioClip(SoundUtils.class.getResource("/sounds/correct.mp3").toString());
        private static final AudioClip errorSound = new AudioClip(SoundUtils.class.getResource("/sounds/error.mp3").toString());
        private static final AudioClip endSound = new AudioClip(SoundUtils.class.getResource("/sounds/endGame.mp3").toString());
        private static final AudioClip playSound = new AudioClip(SoundUtils.class.getResource("/sounds/initGame.mp3").toString());

        public static void playCorrect() {
            correctSound.play();
        }

        public static void playError() {
            errorSound.play();
        }

        public static void playEnd() {
            endSound.play();
        }
        public static void playInit() {
            playSound.play();
        }
    }

}
