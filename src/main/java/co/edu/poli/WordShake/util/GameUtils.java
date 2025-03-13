package co.edu.poli.WordShake.util;

import java.util.*;

public class GameUtils {

    static public List<Character> generarLetras (int totalLetras, int numVocalesSeleccionadas, int repeticionesVocales) {
        String vocales = "AEIOU";
        String consonantes = "BCDFGHJKLMNÑPQRSTVWXYZ";
        Map<Character, Integer> contadorLetras = new HashMap<>();
        Random r = new Random();
        List<Character> letras = new ArrayList<>();

        // Seleccionar 3 vocales aleatorias sin repetir
        Set<Character> vocalesSeleccionadas = new HashSet<>();
        while (vocalesSeleccionadas.size() < numVocalesSeleccionadas) {
            char v = vocales.charAt(r.nextInt(vocales.length()));
            vocalesSeleccionadas.add(v);
        }

        // Agregar cada vocal seleccionada 3 veces a la lista
        for (char v : vocalesSeleccionadas) {
            for (int i = 0; i < repeticionesVocales; i++) {
                letras.add(v);
            }
        }

        // Calcular cuántas consonantes se necesitan para completar el total de letras
        int numConsonantes = totalLetras - (numVocalesSeleccionadas * repeticionesVocales);

        // Agregar consonantes aleatorias hasta completar el total de letras
        while (letras.size() < totalLetras) {
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


}
