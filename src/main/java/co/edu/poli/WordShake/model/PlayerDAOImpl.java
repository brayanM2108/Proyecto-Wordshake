package co.edu.poli.WordShake.model;
import co.edu.poli.WordShake.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PlayerDAOImpl implements PlayerDAO {

    public PlayerDAOImpl() throws SQLException {
    }

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    public List<Player> findAll() throws SQLException {
        List<Player> players = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.position, t.id AS team_id, t.name AS team_name " +
                       "FROM players p " +
                       "JOIN teams t ON p.teams_id = t.id";

        try (
                Statement myStamt = getConnection().createStatement();
                ResultSet myRes = myStamt.executeQuery(query);) {

            while (myRes.next()) {
                Player player = createPlayerWithTeam(myRes);
                players.add(player);

            }
        }
        return players;

    }

    public Player getById(Integer id) throws SQLException {
        Player player = null;
        String query = """
        SELECT p.id , p.name , p.position, 
               t.id AS team_id, t.name AS team_name
        FROM players p
        JOIN teams t ON p.teams_id = t.id
        WHERE p.id = ?
        """;
        try(PreparedStatement myStamt = getConnection().prepareStatement(query)) {
            myStamt.setInt(1, id);
            try (ResultSet myRes = myStamt.executeQuery()) {
                if (myRes.next()) {
                    player = createPlayerWithTeam(myRes);
                }
            }
        }
        return player;
    }


    private Player createPlayer(ResultSet myRes) throws SQLException {


        Player player = new Player();
        player.setId(myRes.getInt("id"));
        player.setName(myRes.getString("name"));
        player.setPosition(myRes.getString("position"));

        return player;
    }

    private Player createPlayerWithTeam(ResultSet myRes) throws SQLException {

        Team team = new Team();
        team.setId(myRes.getInt("team_id"));
        team.setName(myRes.getString("team_name"));


        Player player = new Player();
        player.setId(myRes.getInt("id"));
        player.setName(myRes.getString("name"));
        player.setPosition(myRes.getString("position"));
        player.setTeam(team);

        return player;
    }


}