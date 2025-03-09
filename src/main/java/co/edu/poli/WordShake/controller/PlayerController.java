package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.Player;
import co.edu.poli.WordShake.model.PlayerDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class PlayerController {

    private PlayerDAOImpl playerDAO;

    public PlayerController() throws SQLException {
        this.playerDAO = new PlayerDAOImpl();
    }

    // Método para obtener todos los jugadores
    public List<Player> getAllPlayers() throws SQLException {
        return playerDAO.findAll();
    }

    // Método para obtener un jugador por ID
    public Player getPlayerById(int id) throws SQLException {
        return playerDAO.getById(id);
    }
}
