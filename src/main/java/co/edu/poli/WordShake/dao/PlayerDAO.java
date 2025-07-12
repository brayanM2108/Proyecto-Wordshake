package co.edu.poli.WordShake.dao;

import co.edu.poli.WordShake.model.entity.Player;
import co.edu.poli.WordShake.model.entity.Team;
import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object interface for Player entities.
 * Provides methods to retrieve player data from the database.
 */
public interface PlayerDAO {
    /**
     * Retrieves all players from the database.
     *
     * @return List of all players
     * @throws SQLException if a database access error occurs
     */
    List<Player> findAll() throws SQLException;

    /**
     * Finds a player by their ID.
     *
     * @param id The player's unique identifier
     * @return The player with the specified ID
     * @throws SQLException if a database access error occurs
     */
    Player findById(Integer id) throws SQLException;

    /**
     * Finds a player by name across all leagues.
     *
     * @param name The player's name to search for
     * @return The matching player
     * @throws SQLException if a database access error occurs
     */
    Player findByAllLeagues(String name) throws SQLException;

    /**
     * Finds a player by name and position.
     *
     * @param name The player's name to search for
     * @param position The player's position
     * @return The matching player
     * @throws SQLException if a database access error occurs
     */
    Player findByPosition(String name, String position) throws SQLException;

    /**
     * Finds a player by name within a specific team.
     *
     * @param name The player's name to search for
     * @param teamId The team to search in
     * @return The matching player
     * @throws SQLException if a database access error occurs
     */
    Player findByTeamId(String name, Team teamId) throws SQLException;

    /**
     * Finds a player by name within a specific league.
     *
     * @param name The player's name to search for
     * @param league The league ID to search in
     * @return The matching player
     * @throws SQLException if a database access error occurs
     */
    Player findByLeague(String name, int league) throws SQLException;

    /**
     * Finds a player by name within three specified leagues.
     *
     * @param name The player's name to search for
     * @param league1 First league ID to search in
     * @param league2 Second league ID to search in
     * @param league3 Third league ID to search in
     * @return The matching player
     * @throws SQLException if a database access error occurs
     */
    Player findByThreeLeagues(String name, int league1, int league2, int league3) throws SQLException;
}