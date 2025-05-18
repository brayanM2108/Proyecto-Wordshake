package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.util.SceneLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class MainMenuController {

    @FXML
    private Button playButton;

    @FXML
    public void onPlayClick(ActionEvent actionEvent) {
        SceneLoader.loadScene("fxml/GameSetup.fxml", (Node) actionEvent.getSource());
    }


    public void onQuitClick(ActionEvent event) {
        Platform.exit();
        System.exit(0); 
    }
}
