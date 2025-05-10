package co.edu.poli.WordShake.model;

import javafx.beans.property.*;

public class WordsPoints {
    private final StringProperty palabra;
    private final IntegerProperty puntos;

    public WordsPoints(String palabra, int puntos) {
        this.palabra = new SimpleStringProperty(palabra);
        this.puntos = new SimpleIntegerProperty(puntos);
    }

    public String getPalabra() {
        return palabra.get();
    }

    public StringProperty palabraProperty() {
        return palabra;
    }

    public int getPuntos() {
        return puntos.get();
    }

    public IntegerProperty puntosProperty() {
        return puntos;
    }
}
