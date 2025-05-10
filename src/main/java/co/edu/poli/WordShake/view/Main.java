package co.edu.poli.WordShake.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

// src/main/java/co/edu/poli/WordShake/view/Main.java
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Cargar el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
        Parent root = loader.load();

        // Crear la escena y establecer el estilo
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        // Establecer la escena en el escenario
        stage.setTitle("WordShake");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        // Mostrar la ventana
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Inicia la aplicación JavaFX
    }
}
