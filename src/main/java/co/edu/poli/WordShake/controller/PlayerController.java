package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.Player;
import co.edu.poli.WordShake.dao.PlayerDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class PlayerController {

    private final PlayerDAOImpl playerDAO;

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

    //Método para obtener un jugador por Nombre
    public Player getPlayerByName(String name) throws SQLException {
        return playerDAO.getByName(name);
    }
}
