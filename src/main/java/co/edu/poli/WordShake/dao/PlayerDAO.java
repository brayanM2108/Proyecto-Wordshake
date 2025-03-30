package co.edu.poli.WordShake.dao;

import co.edu.poli.WordShake.model.Player;
import co.edu.poli.WordShake.model.PositionCategory;
import co.edu.poli.WordShake.model.Team;

import java.sql.SQLException;
import java.util.List;

public interface PlayerDAO {
    List<Player> findAll()throws SQLException;

    Player findById(Integer id) throws SQLException;

    Player findByAllLeagues(String name) throws SQLException;

    Player findByPosition(String name, String position) throws SQLException;

    Player findByTeamId(String name, Team teamId) throws SQLException;

    Player findByLeague(String name, int league) throws SQLException;

    Player findByThreeLeagues(String name, int league1, int league2, int league3) throws SQLException;





}
