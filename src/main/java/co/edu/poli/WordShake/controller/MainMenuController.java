package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.util.SceneLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * Controller class for managing the main menu in the WordShake game.
 * Handles user interactions for starting the game or quitting the application.
 */
public class MainMenuController {

    @FXML
    private Button playButton;

    /**
     * Handles the event when the user clicks the play button.
     * Loads the game setup scene.
     *
     * @param actionEvent the action event triggered by the button click
     */
    @FXML
    public void onPlayClick(ActionEvent actionEvent) {
        SceneLoader.loadScene("fxml/GameSetup.fxml", (Node) actionEvent.getSource());
    }

    /**
     * Handles the event when the user clicks the quit button.
     * Exits the application.
     *
     * @param event the action event triggered by the button click
     */
    public void onQuitClick(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}