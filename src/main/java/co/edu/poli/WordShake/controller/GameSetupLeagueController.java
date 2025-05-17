package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.DifficultyMode;
import co.edu.poli.WordShake.model.GameMode;
import co.edu.poli.WordShake.model.League;
import co.edu.poli.WordShake.model.LeagueCategory;
import co.edu.poli.WordShake.util.SceneLoader;
import co.edu.poli.WordShake.util.SoundsUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.util.List;

public class GameSetupLeagueController {
    @FXML
    private Button btnMode1, btnMode2, btnMode3, btnMode4, btnMode5;
    public Button btnStartGame;

    private DifficultyMode selectedDifficulty;
    private GameMode selectedGameMode;
    private LeagueCategory selectedLeague;

    public void initialize() {
        List<Button> modeButtons = List.of(btnMode1, btnMode2, btnMode3, btnMode4, btnMode5);


        btnMode1.setOnAction(e -> {
            selectedLeague = LeagueCategory.PREMIER_LEAGUE;
            setSelectedStyle(btnMode1, modeButtons);
        });

        btnMode2.setOnAction(e -> {
            selectedLeague = LeagueCategory.LA_LIGA;
            setSelectedStyle(btnMode2, modeButtons);
        });

        btnMode3.setOnAction(e -> {
            selectedLeague = LeagueCategory.SERIE_A;
            setSelectedStyle(btnMode3, modeButtons);
        });

        btnMode4.setOnAction(e -> {
            selectedLeague = LeagueCategory.BUNDESLIGA;
            setSelectedStyle(btnMode4, modeButtons);
        });

        btnMode5.setOnAction(e -> {
            selectedLeague = LeagueCategory.LIGUE_1;
            setSelectedStyle(btnMode5, modeButtons);
        });



    }

    public void initGame(DifficultyMode difficulty, GameMode mode) {
        this.selectedDifficulty = difficulty;
        this.selectedGameMode = mode;
    }

    @FXML
    private void onStartGame(ActionEvent event) {
        if (selectedDifficulty == null || selectedGameMode == null || selectedLeague == null) {
            showAlert("Por favor selecciona dificultad, modo de juego y liga antes de comenzar.");
            return;
        }

        SoundsUtils.SoundUtils.playInit();
        SceneLoader.loadScene("fxml/Game.fxml", (Node) event.getSource(), (GameController controller) -> {
            controller.initGame(selectedDifficulty, selectedGameMode, selectedLeague);
        });

    }
    private void setSelectedStyle(Button selectedButton, List<Button> group) {
        for (Button btn : group) {
            btn.setStyle(btn.getStyle().replaceAll("-fx-border.*?;", "")); // limpia borde anterior
        }
        selectedButton.setStyle(selectedButton.getStyle() + "-fx-border-color: #0be40b; -fx-border-width: 3px; -fx-border-radius: 3px;");
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
