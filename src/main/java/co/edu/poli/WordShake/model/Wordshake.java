package co.edu.poli.WordShake.model;

import java.util.*;

public class Wordshake {

    public static int obtenerPuntos( String name) {
        int puntos = 0;
        for (char c : name.toCharArray()) {
            if (Character.isLetter(c)) {
                puntos++;
            }
        }
        return puntos;
    }
}






