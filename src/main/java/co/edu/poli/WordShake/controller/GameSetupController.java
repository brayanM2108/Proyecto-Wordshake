package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.DifficultyMode;
import co.edu.poli.WordShake.model.GameMode;
import co.edu.poli.WordShake.util.SceneLoader;
import co.edu.poli.WordShake.util.SoundsUtils.SoundUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.util.List;


public class GameSetupController {

    @FXML
    private Button btnEasy, btnNormal, btnHard;
    @FXML
    private Button btnMode1, btnMode2, btnMode4, btnMode5, btnMode6;
    @FXML
    private Button btnStartGame;

    private DifficultyMode selectedDifficulty;
    private GameMode selectedGameMode;

    @FXML
    public void initialize() {
        // Grupo de botones
        List<Button> difficultyButtons = List.of(btnEasy, btnNormal, btnHard);
        List<Button> modeButtons = List.of(btnMode1, btnMode2,  btnMode4, btnMode5, btnMode6);

        // Asignar acciones a botones de dificultad
        btnEasy.setOnAction(e -> {
            selectedDifficulty = DifficultyMode.EASY;
            setSelectedStyle(btnEasy, difficultyButtons);
        });

        btnNormal.setOnAction(e -> {
            selectedDifficulty = DifficultyMode.MEDIUM;
            setSelectedStyle(btnNormal, difficultyButtons);
        });

        btnHard.setOnAction(e -> {
            selectedDifficulty = DifficultyMode.HARD;
            setSelectedStyle(btnHard, difficultyButtons);
        });

        // Asignar acciones a botones de modo de juego
        btnMode1.setOnAction(e -> {
            selectedGameMode = GameMode.ALL_LEAGUES;
            setSelectedStyle(btnMode1, modeButtons);
        });

        btnMode2.setOnAction(e -> {
            selectedGameMode = GameMode.BY_POSITION;
            setSelectedStyle(btnMode2, modeButtons);
        });


        btnMode4.setOnAction(e -> {
            selectedGameMode = GameMode.BY_LEAGUE;
            setSelectedStyle(btnMode4, modeButtons);
        });

        btnMode5.setOnAction(e -> {
            selectedGameMode = GameMode.BY_THREE_LEAGUES;
            setSelectedStyle(btnMode5, modeButtons);
        });

        btnMode6.setOnAction(e -> {
            selectedGameMode = GameMode.ALL_LEAGUES;
            selectedDifficulty = DifficultyMode.TRAINING;
            setSelectedStyle(btnMode6, modeButtons);
        });
    }
    private void setSelectedStyle(Button selectedButton, List<Button> group) {
        for (Button btn : group) {
            btn.setStyle(btn.getStyle().replaceAll("-fx-border.*?;", "")); // limpia borde anterior
        }
        selectedButton.setStyle(selectedButton.getStyle() + "-fx-border-color: green; -fx-border-width: 3px; -fx-border-radius: 3px;");
    }


    @FXML
    private void onStartGame(ActionEvent event) {
        if (selectedDifficulty == null || selectedGameMode == null) {
            showAlert("Por favor selecciona dificultad y modo de juego antes de comenzar.");
            return;
        }

        SoundUtils.playInit();

        if (selectedGameMode == GameMode.BY_LEAGUE) {
            // Redirigir a selección de liga
            SceneLoader.loadScene("fxml/GameSetupSelectLeague.fxml", (Node) event.getSource(), (GameSetupLeagueController controller) -> {
                controller.initGame(selectedDifficulty, selectedGameMode);
            });
        } else if (selectedGameMode == GameMode.BY_POSITION) {
            // Redirigir a selección de posición
            SceneLoader.loadScene("fxml/GameSetupSelectPosition.fxml", (Node) event.getSource(), (GameSetupPositionController controller) -> {
                controller.initGame(selectedDifficulty, selectedGameMode);
            });
        }else if (selectedGameMode == GameMode.BY_THREE_LEAGUES) {
            // Redirigir a selección de Liga
            SceneLoader.loadScene("fxml/GameSetupSelectThreeLeagues.fxml", (Node) event.getSource(), (GameSetupThreeLeagueController controller) -> {
                controller.initGame(selectedDifficulty, selectedGameMode);
            });
        } else {
            // Otros modos: ir directamente al juego
            SceneLoader.loadScene("fxml/Game.fxml", (Node) event.getSource(), (GameController controller) -> {
                controller.initGame(selectedDifficulty, selectedGameMode);
            });
        }
    }



    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }


}
