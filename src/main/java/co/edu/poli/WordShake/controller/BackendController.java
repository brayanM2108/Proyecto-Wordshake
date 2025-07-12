package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.*;
import co.edu.poli.WordShake.model.entity.Player;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static co.edu.poli.WordShake.util.GameUtils.generateLetters;

public class BackendController {
    private final Scanner scanner;
    private final PlayerController playerController;
    List<Character> letras = generateLetters(25, 3, 3);
    private DifficultyMode selectedMode;



    public void setDifficulty(DifficultyMode mode) {
        this.selectedMode = mode;
        System.out.println("🔧 Dificultad seleccionada: " + mode.name());
    }







    public BackendController() throws SQLException {
        this.playerController = new PlayerController();
        this.scanner = new Scanner(System.in);
    }

    public void startGame() throws SQLException {

        System.out.println("🎮 Bienvenido a WordShake");
        System.out.println("Elige el modo de dificultad:");
        System.out.println("1. FÁCIL");
        System.out.println("2. NORMAL");
        System.out.println("3. DIFÍCIL");
        System.out.print("Opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1:
                setDifficulty(DifficultyMode.EASY);
                break;
            case 2:
                setDifficulty(DifficultyMode.MEDIUM);
                break;
            case 3:
                setDifficulty(DifficultyMode.HARD);
                break;
            default:
                System.out.println("❌ Opción inválida. Intenta de nuevo.");
                return;
        }

        System.out.println("🎮 Juego Iniciado");

        //startTimer.startTimer(selectedMode.getTimeLimitInSeconds());

        for (int i = 0; i < letras.size(); i++) {
            System.out.print(letras.get(i) + "\t");
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }

        while (true) {

            System.out.print("Ingrese el nombre de un jugador: ");
            String input = scanner.nextLine().trim();

            // Se almacena el jugador de la BD
            Player player = playerController.getByLeague(input, LeagueCategory.PREMIER_LEAGUE.getId());

            if (player != null) {
                System.out.println("✅ Jugador encontrado en la BD: " + player.getName() + "/" + player.getTeam());
                GamePlayer.addWord(input);
            } else {
                System.out.println("❌ Jugador no encontrado");
            }

            System.out.println("Puntuación actual: " + GamePlayer.getScore());
            System.out.println("Palabras encontradas: " + GamePlayer.getFoundWords());
        }
    }


}
