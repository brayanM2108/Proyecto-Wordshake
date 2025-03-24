package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.Jugador;
import co.edu.poli.WordShake.model.Player;
import co.edu.poli.WordShake.dao.PlayerDAOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static co.edu.poli.WordShake.util.GameUtils.generarLetras;

public class GameController {
    private Scanner scanner;
    private PlayerDAOImpl playerDAO;
    private Player player; // Se almacena el jugador de la BD
    List<Character> letras = generarLetras(25, 3, 3);




    public GameController() throws SQLException {
        this.playerDAO = new PlayerDAOImpl();
        this.scanner = new Scanner(System.in);
    }

    public void startGame() throws SQLException {
        System.out.println("🎮 Juego Iniciado");
        for (int i = 0; i < letras.size(); i++) {
            System.out.print(letras.get(i) + "\t"); // "\t" añade tabulación

            if ((i + 1) % 5 == 0) { // Salto de línea cada 5 palabras
                System.out.println();
            }
        }
        while (true) {

        System.out.print("Ingrese el nombre de un jugador: ");

        String input = scanner.nextLine().trim();

        Player wordValidation = playerDAO.getByAllLeagues(input);

        player = playerDAO.getByAllLeagues(input);

        if (player != null) {
            System.out.println("✅ Jugador encontrado en la BD: " + player.getName() + " " + player.getTeam());
            Jugador.addWord(input);
        }else {
            System.out.println("❌ Jugador no encontrado");

        }

        if (wordValidation != null) {
        Jugador.addWord(input);
        System.out.println("Puntuacion actual " + Jugador.getScore());
        }else {
        System.out.println("Puntuacion actual " + Jugador.getScore());
        }
        System.out.println("Palabras encontradas: " + Jugador.getFoundWords());

        }

    }

}
