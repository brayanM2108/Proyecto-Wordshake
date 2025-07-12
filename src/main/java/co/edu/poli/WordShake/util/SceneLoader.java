package co.edu.poli.WordShake.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class SceneLoader {
    public static <T> void loadScene(String fxmlPath, Node sourceNode, Consumer<T> controllerConsumer) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneLoader.class.getClassLoader().getResource(fxmlPath));
            Parent root = loader.load();
            T controller = loader.getController();

            if (controllerConsumer != null) {
                controllerConsumer.accept(controller);
            }

            Stage stage = (Stage) sourceNode.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Versión original para cuando no se necesitan pasar datos
    public static void loadScene(String fxmlPath, Node sourceNode) {
        try {
            Parent root = FXMLLoader.load(SceneLoader.class.getClassLoader().getResource(fxmlPath));
            Stage stage = (Stage) sourceNode.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
