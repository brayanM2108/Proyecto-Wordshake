package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.DifficultyMode;
import co.edu.poli.WordShake.model.GameMode;
import co.edu.poli.WordShake.model.LeagueCategory;
import co.edu.poli.WordShake.util.SceneLoader;
import co.edu.poli.WordShake.util.SoundsUtils.SoundUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.util.List;


public class GameSetupThreeLeagueController {
    @FXML
    private Button btnMode1, btnMode2, btnMode3, btnMode4, btnMode5;
    public Button btnStartGame;

    private DifficultyMode selectedDifficulty;
    private GameMode selectedGameMode;
    private LeagueCategory league1;
    private LeagueCategory league2;
    private LeagueCategory league3;

    private int selectedCount = 0;

    @FXML
    private void initialize() {

        List<Button> modeButtons = List.of(btnMode1, btnMode2, btnMode3, btnMode4, btnMode5);


        btnMode1.setOnAction(e -> {
            toggleLeagueSelection(LeagueCategory.PREMIER_LEAGUE);
            setSelectedStyle(btnMode1, modeButtons);
        });

        btnMode2.setOnAction(e -> {
            toggleLeagueSelection(LeagueCategory.LA_LIGA);
            setSelectedStyle(btnMode2, modeButtons);
        });

        btnMode3.setOnAction(e -> {
            toggleLeagueSelection(LeagueCategory.SERIE_A);
            setSelectedStyle(btnMode3, modeButtons);
        });

        btnMode4.setOnAction(e -> {
            toggleLeagueSelection(LeagueCategory.BUNDESLIGA);
            setSelectedStyle(btnMode4, modeButtons);
        });

        btnMode5.setOnAction(e -> {
            toggleLeagueSelection(LeagueCategory.LIGUE_1);
            setSelectedStyle(btnMode5, modeButtons);
        });
    }

    private void toggleLeagueSelection(LeagueCategory league) {
        if (isLeagueSelected(league)) {
            removeLeague(league);
        } else if (selectedCount < 3) {
            addLeague(league);
        }
    }

    private boolean isLeagueSelected(LeagueCategory league) {
        return league == league1 || league == league2 || league == league3;
    }

    private void addLeague(LeagueCategory league) {
        if (league1 == null) {
            league1 = league;
        } else if (league2 == null) {
            league2 = league;
        } else {
            league3 = league;
        }
        selectedCount++;
    }

    private void removeLeague(LeagueCategory league) {
        if (league == league1) {
            league1 = league2;
            league2 = league3;
            league3 = null;
        } else if (league == league2) {
            league2 = league3;
            league3 = null;
        } else if (league == league3) {
            league3 = null;
        }
        selectedCount--;
    }

    @FXML
    private void onStartGame(ActionEvent event) {
        if (league1 == null || league2 == null || league3 == null) {
            showAlert("Debes seleccionar exactamente 3 ligas antes de comenzar.");
            return;
        }

        SoundUtils.playInit();

        SceneLoader.loadScene("fxml/Game.fxml", (Node) event.getSource(), (GameController controller) -> {
            controller.initGame(selectedDifficulty, selectedGameMode,
                    league1.getId(), league2.getId(), league3.getId());
        });
    }

    public void initGame(DifficultyMode difficulty, GameMode mode) {
        this.selectedDifficulty = difficulty;
        this.selectedGameMode = mode;
    }


    private void setSelectedStyle(Button selectedButton, List<Button> group) {
        String selectedStyle = "-fx-border-color: #0be40b; -fx-border-width: 3px; -fx-border-radius: 3px;";

        // Si el botón ya está seleccionado (tiene el estilo), lo desmarcamos
        if (selectedButton.getStyle().contains("-fx-border-color")) {
            selectedButton.setStyle(selectedButton.getStyle().replaceAll("-fx-border.*?;", ""));
        } else {
            // Si aún no tiene estilo y hay menos de 3 seleccionados, lo marcamos
            long selectedCount = group.stream()
                    .filter(btn -> btn.getStyle().contains("-fx-border-color"))
                    .count();

            if (selectedCount < 3) {
                selectedButton.setStyle(selectedButton.getStyle() + selectedStyle);
            }
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }



}
