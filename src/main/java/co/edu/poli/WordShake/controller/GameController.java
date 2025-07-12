package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.*;
import co.edu.poli.WordShake.service.GameService;
import co.edu.poli.WordShake.util.SceneLoader;
import co.edu.poli.WordShake.util.SoundsUtils.SoundUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;

/**
 * Controller class for managing the main game logic and UI interactions in the WordShake game.
 */
public class GameController {
	@FXML private Button btnEnter;
	@FXML private GridPane letterGrid;
	@FXML private TextField txtPalabra;
	@FXML private TableView<WordsPoints> tblPalabras;
	@FXML private TableColumn<WordsPoints, String> colPalabra;
	@FXML private TableColumn<WordsPoints, Integer> colPuntos;
	@FXML private Label lblTiempo;

	private final GameService gameService;
	private final ObservableList<WordsPoints> palabrasEncontradas;
	private GameSettings gameSettings;

	/**
	 * Constructs a new GameController and initializes the game service and found words list.
	 * @throws SQLException if a database access error occurs
	 */
	public GameController() throws SQLException {
		this.gameService = new GameService();
		this.palabrasEncontradas = FXCollections.observableArrayList();
	}

	/**
	 * Initializes the game with the provided settings and sets up the game and UI.
	 * @param settings the game settings object
	 */
	public void initGame(GameSettings settings) {
		this.gameSettings = settings;
		setupGame();
		setupUI();
	}

	/**
	 * Sets up the game logic, initializes the timer, and displays the letter grid.
	 */
	private void setupGame() {
		gameService.initializeGame(gameSettings, lblTiempo, this::handleTimeEnd);
		gameService.displayLettersInGrid(letterGrid);
	}

	/**
	 * Configures the UI components, including table columns and focus.
	 */
	private void setupUI() {
		colPalabra.setCellValueFactory(cellData -> cellData.getValue().palabraProperty());
		colPuntos.setCellValueFactory(cellData -> cellData.getValue().puntosProperty().asObject());
		tblPalabras.setItems(palabrasEncontradas);
		txtPalabra.requestFocus();
	}

	/**
	 * Handles the event when the user verifies a word.
	 * Processes the word, updates the list, and plays corresponding sounds.
	 * @throws SQLException if a database access error occurs
	 */
	@FXML
	private void onVerificarPalabra() throws SQLException {
		String palabra = txtPalabra.getText().trim();
		GameService.ProcessWordResult resultado = gameService.processWord(palabra);

		if (resultado.isSuccess()) {
			palabrasEncontradas.add(new WordsPoints(palabra, resultado.getPoints()));
			SoundUtils.playCorrect();
		} else {
			gameService.showAlert("Error", resultado.getMessage(), Alert.AlertType.WARNING);
			SoundUtils.playError();
		}

		txtPalabra.clear();
		txtPalabra.requestFocus();
	}

	/**
	 * Handles the end of the game timer, disables input, shows the final result, and plays a sound.
	 */
	private void handleTimeEnd() {
		deshabilitarEntrada();
		mostrarResultadoFinal();
		SoundUtils.playInit();
	}

	/**
	 * Displays the final result alert with the final score and number of found words.
	 */
	private void mostrarResultadoFinal() {
		String mensaje = String.format(
				"Juego terminado!\nPuntaje final: %d\nPalabras encontradas: %d",
				gameService.getFinalScore(),
				gameService.getFoundWords().size()
		);
		gameService.showAlert("Fin del juego", mensaje, Alert.AlertType.INFORMATION);
	}

	/**
	 * Handles the event to return to the main menu.
	 * @param event the action event
	 */
	@FXML
	private void onGoHome(ActionEvent event) {
		gameService.stopGame();
		SceneLoader.loadScene("fxml/MainMenu.fxml", (Node) event.getSource());
	}

	/**
	 * Handles the event to start a new game.
	 * @param event the action event
	 */
	@FXML
	private void onNuevoJuego(ActionEvent event) {
		gameService.stopGame();
		SceneLoader.loadScene("fxml/GameSetup.fxml", (Node) event.getSource());
	}

	/**
	 * Disables the word input and enter button.
	 */
	private void deshabilitarEntrada() {
		btnEnter.setDisable(true);
		txtPalabra.setDisable(true);
	}

	/**
	 * Handles the event to restart the current game.
	 * Stops the current game, clears the UI, and restarts the game logic.
	 * @param event the action event
	 */
	public void onReiniciarPartidaClick(ActionEvent event) {
		gameService.stopGame();
		palabrasEncontradas.clear();
		txtPalabra.clear();
		txtPalabra.setDisable(false);
		btnEnter.setDisable(false);
		gameService.resetGame(lblTiempo, letterGrid);
		setupGame();
	}
}