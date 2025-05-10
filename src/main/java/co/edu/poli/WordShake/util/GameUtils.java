package co.edu.poli.WordShake.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;


import java.util.*;

public class GameUtils {
    private Timer timer;
    private TimerTask taskTimer;
    private int tiempoRestante;
    private List<Character> letrasGeneradas;


    static public List<Character> generateLetters(int totalLetters, int numVocalSelected, int repetiticonVocals) {
        String vocales = "AEIOU";
        String consonantes = "BCDFGHJKLMNÑPQRSTVWXYZ";
        Map<Character, Integer> contadorLetras = new HashMap<>();
        Random r = new Random();
        List<Character> letras = new ArrayList<>();

        // Seleccionar 3 vocales aleatorias sin repetir
        Set<Character> vocalesSeleccionadas = new HashSet<>();
        while (vocalesSeleccionadas.size() < numVocalSelected) {
            char v = vocales.charAt(r.nextInt(vocales.length()));
            vocalesSeleccionadas.add(v);
        }

        // Agregar cada vocal seleccionada 3 veces a la lista
        for (char v : vocalesSeleccionadas) {
            for (int i = 0; i < repetiticonVocals; i++) {
                letras.add(v);
            }
        }

        // Calcular cuántas consonantes se necesitan para completar el total de letras
        int numConsonantes = totalLetters - (numVocalSelected * repetiticonVocals);

        // Agregar consonantes aleatorias hasta completar el total de letras
        while (letras.size() < totalLetters) {
            char c = consonantes.charAt(r.nextInt(consonantes.length()));

            // Asegurar que la consonante no se repita más de 3 veces
            if (contadorLetras.getOrDefault(c, 0) < 3) {
                letras.add(c);
                contadorLetras.put(c, contadorLetras.getOrDefault(c, 0) + 1);
            }
        }

        // Mezclar las letras para evitar patrones predecibles
        Collections.shuffle(letras);

        return letras;
    }

    public static boolean palabraValidaConLetras(String palabra, List<Character> letrasGeneradas) {
        List<Character> disponibles = new ArrayList<>(letrasGeneradas); // Copia mutable

        for (char c : palabra.toUpperCase().toCharArray()) {
            if (!disponibles.contains(c)) {
                return false; // ❌ Letra no disponible (o ya usada más veces de lo permitido)
            } else {
                disponibles.remove((Character) c); // ✅ Se "consume" una letra disponible
            }
        }
        return true; // ✔ Todas las letras están en la lista y en cantidad válida
    }

    public void startTimer(int seconds, Label labelTiempo, Runnable onTimeEnd) {
        if (timer != null) {
            timer.cancel();
        }

        tiempoRestante = seconds;
        timer = new Timer();

        taskTimer = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (tiempoRestante <= 0) {
                        timer.cancel();
                        labelTiempo.setText("00:00");
                        onTimeEnd.run();  // Ejecutar acción al terminar el tiempo
                    } else {
                        int minutes = tiempoRestante / 60;
                        int seconds = tiempoRestante % 60;
                        labelTiempo.setText(String.format("%02d:%02d", minutes, seconds));
                        tiempoRestante--;
                    }
                });
            }
        };

        timer.scheduleAtFixedRate(taskTimer, 0, 1000); // Cada 1 segundo
    }



}
