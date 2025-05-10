package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.DifficultyMode;
import co.edu.poli.WordShake.model.GameMode;
import co.edu.poli.WordShake.util.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;



public class GameSetupController {

    @FXML
    private Button btnEasy, btnNormal, btnHard;
    @FXML
    private Button btnMode1, btnMode2, btnMode3, btnMode4, btnMode5, btnMode6;
    @FXML
    private Button btnStartGame;

    private DifficultyMode selectedDifficulty;
    private GameMode selectedGameMode;

    @FXML
    public void initialize() {
        // Dificultad
        btnEasy.setOnAction(e -> selectedDifficulty = DifficultyMode.EASY);
        btnNormal.setOnAction(e -> selectedDifficulty = DifficultyMode.MEDIUM);
        btnHard.setOnAction(e -> selectedDifficulty = DifficultyMode.HARD);

        // Modo de juego
        btnMode1.setOnAction(e -> selectedGameMode = GameMode.ALL_LEAGUES);
        btnMode2.setOnAction(e -> selectedGameMode = GameMode.BY_POSITION);
        btnMode3.setOnAction(e -> selectedGameMode = GameMode.BY_TEAM);
        btnMode4.setOnAction(e -> selectedGameMode = GameMode.BY_LEAGUE);
        btnMode5.setOnAction(e -> selectedGameMode = GameMode.BY_THREE_LEAGUES);
        btnMode6.setOnAction(e -> selectedGameMode = GameMode.ALL_LEAGUES);



    }

    @FXML
    private void onStartGame(ActionEvent event) {
        if (selectedDifficulty == null || selectedGameMode == null) {
            showAlert("Por favor selecciona dificultad y modo de juego antes de comenzar.");
            return;
        }


        SceneLoader.loadScene("fxml/Game.fxml", (Node) event.getSource(), (GameController controller) -> {
            controller.initGame(selectedDifficulty, selectedGameMode);
        });
    }


    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
