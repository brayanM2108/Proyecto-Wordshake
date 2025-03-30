package co.edu.poli.WordShake.controller;

import co.edu.poli.WordShake.model.Player;
import co.edu.poli.WordShake.dao.PlayerDAOImpl;
import co.edu.poli.WordShake.model.PositionCategory;
import co.edu.poli.WordShake.model.Team;

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
        return playerDAO.findById(id);
    }

    //Método para obtener un jugador de todas las ligas
    public Player getByAllLeagues(String name) throws SQLException {
        return playerDAO.findByAllLeagues(name);
    }
    //Método para obtener un jugador de cualquier liga con una posición especificada
    public Player getByPosition(String name, String position) throws SQLException {
        return playerDAO.findByPosition(name, position);
    }
    //Método para obtener un jugador con un equipo especificado
    public Player getByTeamId(String name, Team team) throws SQLException {
        return playerDAO.findByTeamId(name, team);
    }
    //Método para obtener un jugador de una liga especificada
    public Player getByLeague(String name, int league) throws SQLException {
        return playerDAO.findByLeague(name, league);
    }

    //Método para obtener un jugador de tres ligas diferentes
    public Player getByThreeLeagues (String name, int league1, int league2, int league3) throws SQLException {
        return playerDAO.findByThreeLeagues(name, league1, league2, league3 );
    }
}
