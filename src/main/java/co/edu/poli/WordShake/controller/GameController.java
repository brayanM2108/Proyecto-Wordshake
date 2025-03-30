package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.Jugador;
import co.edu.poli.WordShake.model.Player;
import co.edu.poli.WordShake.dao.PlayerDAOImpl;
import co.edu.poli.WordShake.model.PositionCategory;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static co.edu.poli.WordShake.util.GameUtils.generarLetras;

public class GameController {
    private final Scanner scanner;
    private final PlayerDAOImpl playerDAO;
    List<Character> letras = generarLetras(25, 3, 3);




    public GameController() throws SQLException {
        this.playerDAO = new PlayerDAOImpl();
        this.scanner = new Scanner(System.in);
    }

    public void startGame() throws SQLException {
        System.out.println("🎮 Juego Iniciado");

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
            Player player = playerDAO.findByPosition(input, PositionCategory.GOALKEEPER);

            if (player != null) {
                System.out.println("✅ Jugador encontrado en la BD: " + player.getName() + "/" + player.getTeam());
                Jugador.addWord(input);
            } else {
                System.out.println("❌ Jugador no encontrado");
            }

            System.out.println("Puntuación actual: " + Jugador.getScore());
            System.out.println("Palabras encontradas: " + Jugador.getFoundWords());
        }
    }


}
