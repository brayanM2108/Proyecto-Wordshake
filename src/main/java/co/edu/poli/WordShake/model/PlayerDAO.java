package co.edu.poli.WordShake.model;

import java.sql.SQLException;
import java.util.List;

public interface PlayerDAO {
    List<Player> findAll()throws SQLException;

    Player getById(Integer id) throws SQLException;

    Player getByName(String name) throws SQLException;

    Player getByPosition(String name) throws SQLException;

    Player getByTeamId(String name) throws SQLException;

    Player getByLeagueId(String name) throws SQLException;


}
