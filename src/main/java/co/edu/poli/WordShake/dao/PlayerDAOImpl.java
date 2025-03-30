package co.edu.poli.WordShake.dao;
import co.edu.poli.WordShake.model.*;
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

    @Override
    public List<Player> findAll() throws SQLException {
        List<Player> players = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.position, t.id AS team_id, t.name AS team_name " +
                "FROM players p " +
                "JOIN teams t ON p.teams_id = t.id";

        try (
                Statement myStamt = getConnection().createStatement();
                ResultSet myRes = myStamt.executeQuery(query)) {

            while (myRes.next()) {
                Player player = createPlayerWithTeam(myRes);
                players.add(player);

            }
        }
        return players;

    }

    @Override
    public Player findById(Integer id) throws SQLException {
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

    @Override
    public Player findByAllLeagues(String name) throws SQLException {
        Player player = null;
        String query =
                """
        SELECT p.id , p.name , p.position, t.id AS team_id, t.name AS team_name
        FROM players p
                 JOIN teams t ON p.teams_id = t.id
        WHERE p.name = ? OR p.name LIKE ? OR p.name LIKE ?
        """;
        try(PreparedStatement myStamt = getConnection().prepareStatement(query)) {
            myStamt.setString(1, name);
            myStamt.setString(2, "% " + name);
            myStamt.setString(3, name + " %");// Busca solo nombres o apellidos completos
            try (ResultSet myRes = myStamt.executeQuery()) {
                if (myRes.next()) {
                    player = createPlayerWithTeam(myRes);
                }
            }
        }
        return player;
    }

    @Override
    public Player findByPosition(String name, String position) throws SQLException {
        Player player = null;
        String query = """
        
        SELECT p.id , p.name , p.position, t.id AS team_id,
                t.name AS team_name
        FROM
                players p
        JOIN teams t ON p.teams_id = t.id
        WHERE (p.name = ? OR p. name LIKE ? OR p.name LIKE ?) AND p.position = ?
        """;
        try(PreparedStatement myStamt = getConnection().prepareStatement(query)) {
            myStamt.setString(1, name);
            myStamt.setString(2, "% " + name);
            myStamt.setString(3, name + "%");// Busca solo nombres o apellidos completos
            myStamt.setString(4, position);
            try (ResultSet myRes = myStamt.executeQuery()) {
                if (myRes.next()) {
                    player = createPlayerWithTeam(myRes);
                }
            }
        }
        return player;
    }

    @Override
    public Player findByTeamId(String name, Team teamId) throws SQLException {
        Player player = null;

        String query =
                """
                SELECT p.id , p.name , p.position,
                       t.id AS team_id, t.name AS team_name
                FROM players p
                JOIN teams t ON p.teams_id = t.id
                WHERE (p.name = ? OR p.name LIKE ? OR p.name LIKE ?) AND t.id = ?
                """; // 🔹 Cambié teams_id a t.id para mayor claridad

        try (PreparedStatement myStamt = getConnection().prepareStatement(query)) {
            myStamt.setString(1, name);
            myStamt.setString(2, "% " + name);
            myStamt.setString(3, name + "%"); // Busca solo nombres o apellidos completos
            myStamt.setInt(4, teamId.getId()); //  Asignar el teamId correctamente

            try (ResultSet myRes = myStamt.executeQuery()) {
                if (myRes.next()) {
                    player = createPlayerWithTeam(myRes);
                }
            }
        }
        return player;
    }


    @Override
    public Player findByLeague(String name, int league) throws SQLException {
        Player player = null;

        String query =
        """
        SELECT
            p.id,
            p.name,
            p.position,
            t.id AS team_id,
            t.name AS team_name,
            l.id AS league_id,
            l.name AS league_name
        FROM players p
        JOIN teams t ON p.teams_id = t.id
        JOIN leagues l ON t.leagues_id = l.id
        WHERE (p.name = ? OR p. name LIKE ? OR p.name LIKE ?) AND t.leagues_id = ?;
        """;
        try(PreparedStatement myStamt = getConnection().prepareStatement(query)) {
            myStamt.setString(1, name);
            myStamt.setString(2, "% " + name);
            myStamt.setString(3, name + "%");
            myStamt.setInt(4, league);
            try (ResultSet myRes = myStamt.executeQuery()) {
                if (myRes.next()) {
                    player = createPlayerWithTeam(myRes);
                }
            }
        }
        return player;
    }

    @Override
    public Player findByThreeLeagues(String name, int league1, int league2, int league3) throws SQLException {
        Player player = null;

        String query =
                """
                SELECT
                    p.id,
                    p.name,
                    p.position,
                    t.id AS team_id,
                    t.name AS team_name,
                    l.id AS league_id,
                    l.name AS league_name
                FROM players p
                JOIN teams t ON p.teams_id = t.id
                JOIN leagues l ON t.leagues_id = l.id
                WHERE (p.name = ? OR p. name LIKE ? OR p.name LIKE ?)
                AND (t.leagues_id = ? OR t.leagues_id = ? OR t.leagues_id = ?);
                """;
        try(PreparedStatement myStamt = getConnection().prepareStatement(query)) {
            myStamt.setString(1, name);
            myStamt.setString(2, "% " + name);
            myStamt.setString(3, name + "%");
            myStamt.setInt(4, league1);
            myStamt.setInt(5, league2);
            myStamt.setInt(6, league3);

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