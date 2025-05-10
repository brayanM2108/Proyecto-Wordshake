package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.util.SceneLoader;
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
}
