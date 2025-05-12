package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.*;
import co.edu.poli.WordShake.util.GameUtils;
import co.edu.poli.WordShake.util.SceneLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static co.edu.poli.WordShake.util.GameUtils.generateLetters;

public class GameController {

	@FXML
	private Button btnNuevoJuego;

	@FXML
	private GridPane letterGrid;
	@FXML
	private TextField txtPalabra;

	@FXML
	private TableView<WordsPoints> tblPalabras;

	@FXML
	private TableColumn<WordsPoints, String> colPalabra;

	@FXML
	private TableColumn<WordsPoints, Integer> colPuntos;

	private final ObservableList<WordsPoints> palabrasEncontradas = FXCollections.observableArrayList();
	@FXML
	private Label lblTiempo;

	private final GameUtils gameUtils = new GameUtils();
	private GameMode selectedGameMode;
	private LeagueCategory selectedLeague;
	private String selectedPosition;
	private Team selectedTeam;
	private final PlayerController playerController ;
	private DifficultyMode difficulty;
	private GameMode gameMode;
	private boolean gameOver;
	private List<Character> letrasGeneradas;
	private boolean ultimaOportunidadActiva = false;

	public void initGame(DifficultyMode difficulty, GameMode gameMode) {
		this.difficulty = difficulty;
		this.gameMode = gameMode;

		System.out.println("Modo: " + gameMode);
		System.out.println("Dificultad: " + difficulty);
		if (difficulty.getTimeLimitInSeconds() > 0) {
			gameUtils.startTimer(difficulty.getTimeLimitInSeconds(), lblTiempo, this::finalizarJuego);
		} else {
			lblTiempo.setText("∞"); // Mostrar infinito o "Sin tiempo"
		}

		// Mostrar letras al iniciar juego
		mostrarLetrasEnGrid(generateLetters(25, 4, 2)); //
		colPalabra.setCellValueFactory(cellData -> cellData.getValue().palabraProperty());
		colPuntos.setCellValueFactory(cellData -> cellData.getValue().puntosProperty().asObject());
		tblPalabras.setItems(palabrasEncontradas);
		System.out.println("Letras generadas: " + letrasGeneradas);

	}


	public GameController() throws SQLException {
		this.playerController = new PlayerController();
	}


	@FXML
	private void VerificarPalabra() throws SQLException {
		String playerName = txtPalabra.getText().trim();
		if (playerName.isEmpty()) {
			System.out.println("Por favor ingresa un nombre válido.");
			return;
		}
		// Validar letras del tablero
		if (!GameUtils.palabraValidaConLetras(playerName, letrasGeneradas)) {
			System.out.println("❌ La palabra contiene letras no disponibles en el tablero.");
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Letra inválida");
			alert.setHeaderText(null);
			alert.setContentText("La palabra contiene letras no disponibles o en exceso.");
			alert.show();
			txtPalabra.clear();
			return;
		}
		Player player = null;

		switch (gameMode) {
			case ALL_LEAGUES, TRAINING:
				player = playerController.getByAllLeagues(playerName);
				break;
            case BY_LEAGUE:
				if (selectedLeague != null) {
					player = playerController.getByLeague(playerName, selectedLeague.getId());
				}
				break;
			case BY_THREE_LEAGUES:
				player = playerController.getByThreeLeagues(
						playerName,
						LeagueCategory.PREMIER_LEAGUE.getId(),
						LeagueCategory.LA_LIGA.getId(),
						LeagueCategory.SERIE_A.getId()
				);
				break;
			case BY_POSITION:
				if (selectedPosition != null) {
					player = playerController.getByPosition(playerName, selectedPosition);
				}
				break;
			case BY_TEAM:
				if (selectedTeam != null) {
					player = playerController.getByTeamId(playerName, selectedTeam);
				}
				break;
			default:

				return;
		}

		if (player != null && !gameOver) {
			System.out.println("✅ Jugador encontrado: " + player.getName());
			Jugador.addWord(playerName);

			// Añadir al TableView
			int puntos = Jugador.pointsObtained(playerName);
			palabrasEncontradas.add(new WordsPoints(playerName, puntos));
		} else {
			System.out.println("❌ Jugador no encontrado");
		}
		txtPalabra.clear();  // Limpiar el campo
		// Verificar si era la última oportunidad
		if (ultimaOportunidadActiva) {
			gameOver = true;  // Ahora sí termina completamente
			ultimaOportunidadActiva = false;

			Alert finalAlert = new Alert(Alert.AlertType.INFORMATION);
			finalAlert.setTitle("Fin del juego");
			finalAlert.setHeaderText(null);
			finalAlert.setContentText("🏁 Juego terminado. Puntuación final: " + Jugador.getScore());
			finalAlert.showAndWait();
		}
	}


	private void mostrarLetrasEnGrid(List<Character> letras) {

		this.letrasGeneradas = new ArrayList<>(letras);  // Copia de seguridad

		int index = 0;
			for (int row = 0; row < 5; row++) {
				for (int col = 0; col < 5; col++) {
					if (index < letras.size()) {
						char letra = letras.get(index++);
						Label lbl = new Label(String.valueOf(letra));
						lbl.setPrefSize(70, 57);
						lbl.setStyle("-fx-background-color: #8BC7EA; -fx-border-radius: 5; -fx-background-radius: 10; " +
								"-fx-font-size: 27px; -fx-font-weight: bold; -fx-text-fill: #50595F; " +
								"-fx-alignment: center;");
						letterGrid.add(lbl, col, row);
					}
				}
			}
		}

	private void finalizarJuego() {
		ultimaOportunidadActiva = true;
		gameOver = false; // Para permitir ingresar una palabra más

		Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
		alert1.setTitle("Última oportunidad");
		alert1.setHeaderText(null);
		alert1.setContentText("⏰ El tiempo ha terminado. Ingresa una última palabra.");
		alert1.showAndWait();

		txtPalabra.requestFocus();
	}

	@FXML
		public void onReiniciarPartidaClick(ActionEvent actionEvent) {
			mostrarLetrasEnGrid(generateLetters(25, 4, 2));
			Jugador.reset();
			palabrasEncontradas.clear();
			txtPalabra.clear();
			gameUtils.startTimer(difficulty.getTimeLimitInSeconds(), lblTiempo, this::finalizarJuego);
		}

	public void onGoHome(ActionEvent event) {
		SceneLoader.loadScene("fxml/MainMenu.fxml", (Node) event.getSource());
	}

	public void onNuevoJuego(ActionEvent event) {
		SceneLoader.loadScene("fxml/GameSetup.fxml", (Node) event.getSource());


	}

}

