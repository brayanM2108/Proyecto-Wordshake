package co.edu.poli.WordShake.view;

import co.edu.poli.WordShake.controller.GameController;
import co.edu.poli.WordShake.controller.PlayerController;
import co.edu.poli.WordShake.model.Jugador;
import co.edu.poli.WordShake.model.Player;
import co.edu.poli.WordShake.model.PlayerDAO;
import co.edu.poli.WordShake.model.PlayerDAOImpl;
import co.edu.poli.WordShake.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static co.edu.poli.WordShake.model.Wordshake.obtenerPuntos;

public class Main {
    public static void main(String[] args) throws SQLException {

        /*  List<Character> letras = generarLetras(25, 3, 3);

        // Imprimir el resultado
          System.out.println(letras);
        */
        GameController game = new GameController();
        game.startGame();
        //int puntos = obtenerPuntos("feeeeeeee");
        //System.out.println(puntos);


       /* try (Connection myConn = DatabaseConnection.getInstance()) {
            PlayerController playerController = new PlayerController();
            // Obtener jugadores con su equipo
            System.out.println("🏆 Lista de jugadores:");

            Player player = new Player();
            String jugador = "gavi";


            System.out.println(playerController.getPlayerByName(jugador));

            //List<Player> players = playerController.getAllPlayers();


            //for (Player player : players) {
               // System.out.println("⚽ " + player.getName() + " - " + player.getPosition() + " (Equipo: " + player.getTeam().getName() + ")");
            }
        }
*/

    }
}