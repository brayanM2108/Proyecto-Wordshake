package co.edu.poli.WordShake.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class Seleccion_categoria {

    @FXML
    private Button btnPorPosicion;
    @FXML
    private Button btnEquipo;
    @FXML
    private Button btnLiga;
    @FXML
    private Button btnTresLigas;
    @FXML
    private Label lblSeleccion;

    private String seleccionUsuario; // Variable para almacenar la selección

    @FXML
    public void initialize() {
        // Asignar eventos a los botones
        btnPorPosicion.setOnAction(e -> guardarSeleccion("Jugadores por posición de todas las ligas"));
        btnEquipo.setOnAction(e -> guardarSeleccion("Jugadores de un equipo específico"));
        btnLiga.setOnAction(e -> guardarSeleccion("Jugadores de una liga específica"));
        btnTresLigas.setOnAction(e -> guardarSeleccion("Jugadores de tres ligas diferentes"));
    }

    private void guardarSeleccion(String seleccion) {
        this.seleccionUsuario = seleccion;
        System.out.println("Selección guardada: " + seleccionUsuario); // Para verificar en consola
        if (lblSeleccion != null) {
            lblSeleccion.setText("Seleccionado: " + seleccionUsuario);
        }
    }

    public String getSeleccionUsuario() {
        return seleccionUsuario;
    }
    
}
