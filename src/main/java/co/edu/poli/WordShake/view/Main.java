package co.edu.poli.WordShake.view;


import co.edu.poli.WordShake.controller.PlayerController;
import co.edu.poli.WordShake.model.Player;
import co.edu.poli.WordShake.model.PlayerDAO;
import co.edu.poli.WordShake.model.PlayerDAOImpl;
import co.edu.poli.WordShake.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        /*  List<Character> letras = generarLetras(25, 3, 3);

        // Imprimir el resultado
          System.out.println(letras);
        */


        try (Connection myConn = DatabaseConnection.getInstance()) {
            PlayerController playerController = new PlayerController();
            // Obtener jugadores con su equipo
            System.out.println("🏆 Lista de jugadores:");

            Player player = new Player();

            System.out.println(playerController.getPlayerById(1));

            //List<Player> players = playerController.getAllPlayers();


            //for (Player player : players) {
               // System.out.println("⚽ " + player.getName() + " - " + player.getPosition() + " (Equipo: " + player.getTeam().getName() + ")");
            }
        }


    }
