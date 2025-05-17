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
import co.edu.poli.WordShake.util.SoundsUtils.SoundUtils;
public class GameController {


	@FXML
	public Button btnEnter;

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
	private int league1Id, league2Id, league3Id;


	public void initGame(DifficultyMode difficulty, GameMode gameMode) {
		this.difficulty = difficulty;
		this.gameMode = gameMode;
		this.selectedLeague = null; // Por seguridad

		System.out.println("Modo: " + gameMode);
		System.out.println("Dificultad: " + difficulty);

		if (difficulty.getTimeLimitInSeconds() > 0) {
			gameUtils.startTimer(difficulty.getTimeLimitInSeconds(), lblTiempo, this::finalizarJuego);
		} else {
			lblTiempo.setText("∞");
		}

		mostrarLetrasEnGrid(generateLetters(25, 4, 2));
		colPalabra.setCellValueFactory(cellData -> cellData.getValue().palabraProperty());
		colPuntos.setCellValueFactory(cellData -> cellData.getValue().puntosProperty().asObject());
		tblPalabras.setItems(palabrasEncontradas);
		System.out.println("Letras generadas: " + letrasGeneradas);
	}

	public void initGame(DifficultyMode difficulty, GameMode gameMode, LeagueCategory league) {
		this.difficulty = difficulty;
		this.gameMode = gameMode;
		this.selectedLeague = league;

		System.out.println("Modo: " + gameMode);
		System.out.println("Dificultad: " + difficulty);
		System.out.println("Liga: " + (league != null ? league.name() : "Ninguna"));

		if (difficulty.getTimeLimitInSeconds() > 0) {
			gameUtils.startTimer(difficulty.getTimeLimitInSeconds(), lblTiempo, this::finalizarJuego);
		} else {
			lblTiempo.setText("∞"); // Mostrar infinito o "Sin tiempo"
		}

		// Mostrar letras al iniciar juego
		mostrarLetrasEnGrid(generateLetters(25, 4, 2));
		colPalabra.setCellValueFactory(cellData -> cellData.getValue().palabraProperty());
		colPuntos.setCellValueFactory(cellData -> cellData.getValue().puntosProperty().asObject());
		tblPalabras.setItems(palabrasEncontradas);
		System.out.println("Letras generadas: " + letrasGeneradas);
	}

	public void initGame(DifficultyMode difficulty, GameMode gameMode, int league1Id, int league2Id, int league3Id) {
		this.difficulty = difficulty;
		this.gameMode = gameMode;
		this.league1Id = league1Id;
		this.league2Id = league2Id;
		this.league3Id = league3Id;
		System.out.println("Modo: " + gameMode);
		System.out.println("Dificultad: " + difficulty);


		if (difficulty.getTimeLimitInSeconds() > 0) {
			gameUtils.startTimer(difficulty.getTimeLimitInSeconds(), lblTiempo, this::finalizarJuego);
		} else {
			lblTiempo.setText("∞"); // Mostrar infinito o "Sin tiempo"
		}

		// Mostrar letras al iniciar juego
		mostrarLetrasEnGrid(generateLetters(25, 4, 2));
		colPalabra.setCellValueFactory(cellData -> cellData.getValue().palabraProperty());
		colPuntos.setCellValueFactory(cellData -> cellData.getValue().puntosProperty().asObject());
		tblPalabras.setItems(palabrasEncontradas);
		System.out.println("Letras generadas: " + letrasGeneradas);
	}

	public void initGame(DifficultyMode difficulty, GameMode gameMode, String selectedPosition) {
		this.difficulty = difficulty;
		this.gameMode = gameMode;
		this.selectedPosition = selectedPosition;

		System.out.println("Modo: " + gameMode);
		System.out.println("Dificultad: " + difficulty);
		System.out.println("Posicion: " + (selectedPosition != null  ));

		if (difficulty.getTimeLimitInSeconds() > 0) {
			gameUtils.startTimer(difficulty.getTimeLimitInSeconds(), lblTiempo, this::finalizarJuego);
		} else {
			lblTiempo.setText("∞"); // Mostrar infinito o "Sin tiempo"
		}

		// Mostrar letras al iniciar juego
		mostrarLetrasEnGrid(generateLetters(25, 4, 2));
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
			/*if (!GameUtils.palabraValidaConLetras(playerName, letrasGeneradas)) {
				mostrarAlerta("Letra inválida", "La palabra contiene letras no disponibles o en exceso.");
				txtPalabra.clear();
				return;
			}*/

			if (Jugador.getFoundWords().contains(playerName)) {
				mostrarAlerta("Palabra repetida", "Ya has ingresado esta palabra antes.");
				txtPalabra.clear();
				return;
			}

			Player player = buscarJugador(playerName);
			if (player != null && !gameOver) {
				System.out.println("✅ Jugador encontrado: " + player.getName());
				Jugador.addWord(playerName);
				int puntos = Jugador.pointsObtained(playerName);
				palabrasEncontradas.add(new WordsPoints(playerName, puntos));
				SoundUtils.playCorrect();

			} else {
				System.out.println("❌ Jugador no encontrado");
				SoundUtils.playError();
			}

			txtPalabra.clear();

			if (ultimaOportunidadActiva) {
                finalizarJuegoFinal();
            }
		}


	private Player buscarJugador(String playerName) throws SQLException {
		switch (gameMode) {
			case ALL_LEAGUES, TRAINING:
				return playerController.getByAllLeagues(playerName);
			case BY_LEAGUE:
				if (selectedLeague != null) {
					return playerController.getByLeague(playerName, selectedLeague.getId());
				}
				break;
			case BY_THREE_LEAGUES:
				return playerController.getByThreeLeagues(
						playerName,
						league1Id,
						league2Id,
						league3Id
				);
			case BY_POSITION:
				if (selectedPosition != null) {
					return playerController.getByPosition(playerName, selectedPosition);
				}
				break;
			case BY_TEAM:
				if (selectedTeam != null) {
					return playerController.getByTeamId(playerName, selectedTeam);
				}
				break;
		}
		return null;
	}


	// Esta funcion finaliza el juego cuando se ingresa la palabra de la "última oportunidad"
	private void finalizarJuegoFinal() {
		gameOver = true;  // Ahora sí termina completamente
		ultimaOportunidadActiva = false;
		btnEnter.setDisable(true);
		txtPalabra.setDisable(true);
		Alert finalAlert = new Alert(Alert.AlertType.INFORMATION);
		finalAlert.setTitle("Fin del juego");
		finalAlert.setHeaderText(null);
		finalAlert.setContentText("🏁 Juego terminado. Puntuación final: " + Jugador.getScore());
		System.out.println("Palabras encontradas: " + Jugador.getFoundWords());
		finalAlert.showAndWait();
		}

	// Esta funcion activa la funcionalidad de "última oportunidad"
	private void finalizarJuego() {
		ultimaOportunidadActiva = true;
		gameOver = false; // Para permitir ingresar una palabra más
		SoundUtils.playInit();

		Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
		alert1.setTitle("Última oportunidad");
		alert1.setHeaderText(null);
		alert1.setContentText("⏰ El tiempo ha terminado. Ingresa una última palabra.");
		alert1.showAndWait();

		txtPalabra.requestFocus();
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


	@FXML
	private void mostrarAlerta(String titulo, String mensaje) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.show();
}
	//Esta funcion reinicia la partida con nuevas letras en el tablero,
	//reinicia el puntaje, el listado de palabras y lo necesario para un nuevo juego
	@FXML
	public void onReiniciarPartidaClick(ActionEvent actionEvent) {
		mostrarLetrasEnGrid(generateLetters(25, 4, 2));

		// Resetea el modelo
		Jugador.reset();

		// Limpia UI
		palabrasEncontradas.clear();
		txtPalabra.clear();

		// Estado de juego
		gameOver = false;
		ultimaOportunidadActiva = false;
		txtPalabra.setDisable(false);
		btnEnter.setDisable(false);
		// Inicia de nuevo el temporizador
		gameUtils.startTimer(difficulty.getTimeLimitInSeconds(), lblTiempo, this::finalizarJuego);
	}


	public void onGoHome(ActionEvent event) {
		gameUtils.stopTimer();
		Jugador.reset();

		// Limpia UI
		palabrasEncontradas.clear();
		txtPalabra.clear();

		// Estado de juego
		gameOver = false;
		ultimaOportunidadActiva = false;
		SceneLoader.loadScene("fxml/MainMenu.fxml", (Node) event.getSource());

	}

	public void onNuevoJuego(ActionEvent event) {
		gameUtils.stopTimer();
		Jugador.reset();

		// Limpia UI
		palabrasEncontradas.clear();
		txtPalabra.clear();

		// Estado de juego
		gameOver = false;
		ultimaOportunidadActiva = false;
		SceneLoader.loadScene("fxml/GameSetup.fxml", (Node) event.getSource());



	}


}