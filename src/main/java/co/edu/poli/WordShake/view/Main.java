package co.edu.poli.WordShake.view;

import co.edu.poli.WordShake.controller.GameController;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        /*  List<Character> letras = generarLetras(25, 3, 3);

        // Imprimir el resultado
          System.out.println(letras);
        */
        GameController game = new GameController();
        game.startGame();
        int puntos = obtenerPuntos("feeeeeeee");
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