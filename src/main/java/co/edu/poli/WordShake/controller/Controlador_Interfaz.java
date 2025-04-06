package co.edu.poli.WordShake.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.beans.property.SimpleStringProperty;

public class Controlador_Interfaz {

	@FXML
	private Label lblTiempo;

	@FXML
	private Label lblPuntaje; 

	@FXML
	private TableView<String> tableViewPalabras;

	@FXML
	private TableColumn<String, String> colPalabra;

	@FXML
	private TextField txtPalabra;

	@FXML
	private Button btnAgregar, btnPausar, btnReanudar, btnReiniciar;

	private int tiempoRestante = 150;
	private Timeline timeline;
	private ObservableList<String> listaPalabras = FXCollections.observableArrayList();
	private boolean enPausa = false;

	@FXML
	public void initialize() {
	    iniciarTemporizador();
	    configurarTabla();
	    btnAgregar.setOnAction(event -> agregarPalabra());
	    btnPausar.setOnAction(event -> pausarTemporizador());
	    btnReanudar.setOnAction(event -> reanudarTemporizador());
	    btnReiniciar.setOnAction(event -> reiniciarPartida());
	}

	public void iniciarTemporizador() {
	    if (timeline != null) {
	        timeline.stop();
	    }

	    timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
	        if (!enPausa) {
	            tiempoRestante--;
	            lblTiempo.setText("Tiempo: " + formatearTiempo(tiempoRestante));

	            if (tiempoRestante <= 0) {
	                timeline.stop();
	                finalizarPartida();
	            }
	        }
	    }));

	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.play();
	}

	@FXML
	private void pausarTemporizador() {
	    enPausa = true;
	    btnPausar.setDisable(true);
	    btnReanudar.setDisable(false);
	}

	@FXML
	private void reanudarTemporizador() {
	    enPausa = false;
	    btnPausar.setDisable(false);
	    btnReanudar.setDisable(true);
	}

	private void configurarTabla() {
	    colPalabra.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
	    tableViewPalabras.setItems(listaPalabras);
	}

	private void agregarPalabra() {
	    String palabra = txtPalabra.getText().trim();
	    if (!palabra.isEmpty()) {
	        listaPalabras.add(palabra);
	        txtPalabra.clear();
	        actualizarPuntaje(); // Cada vez que se agrega una palabra, actualizar la puntuación
	    }
	}

	private String formatearTiempo(int segundos) {
	    int minutos = segundos / 60;
	    int segRestantes = segundos % 60;
	    return String.format("%02d:%02d", minutos, segRestantes);
	}

	private void finalizarPartida() {
	    lblTiempo.setText("¡Tiempo terminado!");
	    int puntajeFinal = calcularPuntaje();
	    lblPuntaje.setText("Puntuación: " + puntajeFinal); // Mostrar el puntaje en la interfaz

	    mostrarAlerta("Fin de la ronda", "El tiempo ha terminado. Se ha finalizado la partida.\nPuntaje final: " + puntajeFinal);

	    btnAgregar.setDisable(true);
	    btnPausar.setDisable(true);
	    btnReanudar.setDisable(true);
	    txtPalabra.setDisable(true);
	}

	private int calcularPuntaje() {
	    return listaPalabras.size() * 10; // Se otorgan 10 puntos por cada palabra ingresada
	}

	private void actualizarPuntaje() {
	    int puntajeActual = calcularPuntaje();
	    lblPuntaje.setText("Puntuación: " + puntajeActual); // Actualizar el puntaje en tiempo real
	}

	@FXML
	private void reiniciarPartida() {
	    tiempoRestante = 150;
	    lblTiempo.setText("Tiempo: " + formatearTiempo(tiempoRestante));
	    listaPalabras.clear();
	    lblPuntaje.setText("Puntuación: 0"); 
	    btnAgregar.setDisable(false);
	    btnPausar.setDisable(false);
	    btnReanudar.setDisable(false);
	    txtPalabra.setDisable(false);
	    iniciarTemporizador();
	}

	private void mostrarAlerta(String titulo, String mensaje) {
	    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
	    alerta.setTitle(titulo);
	    alerta.setHeaderText(null);
	    alerta.setContentText(mensaje);
	    alerta.showAndWait();
	}
}