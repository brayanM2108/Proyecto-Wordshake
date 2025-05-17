package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.*;
import co.edu.poli.WordShake.util.SceneLoader;
import co.edu.poli.WordShake.util.SoundsUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.util.List;

import static co.edu.poli.WordShake.model.PositionCategory.*;

public class GameSetupPositionController {
    @FXML
    private Button btnMode1, btnMode2, btnMode3, btnMode4;
    public Button btnStartGame;

    private DifficultyMode selectedDifficulty;
    private GameMode selectedGameMode;
    private String selectedPositionCategory;

    public void initialize() {
        List<Button> modeButtons = List.of(btnMode1, btnMode2, btnMode3, btnMode4);
        btnMode1.setOnAction(e -> selectedPositionCategory = GOALKEEPER);
        btnMode2.setOnAction(e -> selectedPositionCategory = DEFENDER);
        btnMode3.setOnAction(e -> selectedPositionCategory = MIDFIELDER);
        btnMode4.setOnAction(e -> selectedPositionCategory = FORWARD);
        btnMode1.setOnAction(e -> {
            selectedPositionCategory = GOALKEEPER;
            setSelectedStyle(btnMode1, modeButtons);
        });

        btnMode2.setOnAction(e -> {
            selectedPositionCategory = DEFENDER;
            setSelectedStyle(btnMode2, modeButtons);
        });

        btnMode3.setOnAction(e -> {
            selectedPositionCategory = MIDFIELDER;
            setSelectedStyle(btnMode3, modeButtons);
        });

        btnMode4.setOnAction(e -> {
            selectedPositionCategory = FORWARD;
            setSelectedStyle(btnMode4, modeButtons);
        });
    }

    public void initGame(DifficultyMode difficulty, GameMode mode) {
        this.selectedDifficulty = difficulty;
        this.selectedGameMode = mode;
    }


    private void setSelectedStyle(Button selectedButton, List<Button> group) {
        for (Button btn : group) {
            btn.setStyle(btn.getStyle().replaceAll("-fx-border.*?;", "")); // limpia borde anterior
        }
        selectedButton.setStyle(selectedButton.getStyle() + "-fx-border-color: #0be40b; -fx-border-width: 3px; -fx-border-radius: 3px;");
    }


    @FXML
    private void onStartGame(ActionEvent event) {
        if (selectedDifficulty == null || selectedGameMode == null || selectedPositionCategory == null) {
            showAlert("Por favor selecciona dificultad, modo de juego y posicion antes de comenzar.");
            return;
        }

        SoundsUtils.SoundUtils.playInit();
        SceneLoader.loadScene("fxml/Game.fxml", (Node) event.getSource(), (GameController controller) -> {
            controller.initGame(selectedDifficulty, selectedGameMode, selectedPositionCategory);
        });

    }


    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
