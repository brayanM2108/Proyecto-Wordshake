package co.edu.poli.WordShake.controller;


import co.edu.poli.WordShake.model.entity.Player;
import co.edu.poli.WordShake.dao.PlayerDAOImpl;
import co.edu.poli.WordShake.model.entity.Team;

import java.sql.SQLException;
import java.util.List;

/**
 * Controller class that handles player-related operations.
 * Acts as an intermediary between the view and the data access layer.
 */
public class PlayerController {

    /** Data Access Object for Player entities */
    private final PlayerDAOImpl playerDAO;

    /**
     * Constructs a new PlayerController.
     *
     * @throws SQLException if a database access error occurs
     */
    public PlayerController() throws SQLException {
        this.playerDAO = new PlayerDAOImpl();
    }

    /**
     * Retrieves all players from the database.
     *
     * @return List of all players
     * @throws SQLException if a database access error occurs
     */
    public List<Player> getAllPlayers() throws SQLException {
        return playerDAO.findAll();
    }

    /**
     * Retrieves a player by their ID.
     *
     * @param id The player's unique identifier
     * @return The player with the specified ID
     * @throws SQLException if a database access error occurs
     */
    public Player getPlayerById(int id) throws SQLException {
        return playerDAO.findById(id);
    }

    /**
     * Searches for a player by name across all leagues.
     *
     * @param name The player's name to search for
     * @return The matching player
     * @throws SQLException if a database access error occurs
     */
    public Player getByAllLeagues(String name) throws SQLException {
        return playerDAO.findByAllLeagues(name);
    }

    /**
     * Searches for a player by name and position.
     *
     * @param name The player's name to search for
     * @param position The player's position
     * @return The matching player
     * @throws SQLException if a database access error occurs
     */
    public Player getByPosition(String name, String position) throws SQLException {
        return playerDAO.findByPosition(name, position);
    }

    /**
     * Searches for a player by name within a specific team.
     *
     * @param name The player's name to search for
     * @param team The team to search in
     * @return The matching player
     * @throws SQLException if a database access error occurs
     */
    public Player getByTeamId(String name, Team team) throws SQLException {
        return playerDAO.findByTeamId(name, team);
    }

    /**
     * Searches for a player by name within a specific league.
     *
     * @param name The player's name to search for
     * @param league The league ID to search in
     * @return The matching player
     * @throws SQLException if a database access error occurs
     */
    public Player getByLeague(String name, int league) throws SQLException {
        return playerDAO.findByLeague(name, league);
    }

    /**
     * Searches for a player by name within three specified leagues.
     *
     * @param name The player's name to search for
     * @param league1 First league ID to search in
     * @param league2 Second league ID to search in
     * @param league3 Third league ID to search in
     * @return The matching player
     * @throws SQLException if a database access error occurs
     */
    public Player getByThreeLeagues(String name, int league1, int league2, int league3) throws SQLException {
        return playerDAO.findByThreeLeagues(name, league1, league2, league3);
    }
}