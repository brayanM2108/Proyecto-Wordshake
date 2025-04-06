package co.edu.poli.WordShake.controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HistorialDePalabras extends Application {
    private ObservableList<String> historialPalabras = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        
        TextField textField = new TextField();
        textField.setPromptText("Escribe una palabra...");
        
        Button agregarBtn = new Button("Agregar al Historial");
        agregarBtn.setOnAction(event -> {
            String palabra = textField.getText().trim();
            if (!palabra.isEmpty()) {
                historialPalabras.add(palabra); 
                textField.clear(); 
            }
        });

        
        ListView<String> listViewHistorial = new ListView<>(historialPalabras);
        VBox layout = new VBox(10, textField, agregarBtn, listViewHistorial);
        layout.setSpacing(10);

        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setTitle("Historial de Palabras");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
