package co.edu.poli.WordShake.dao;

import co.edu.poli.WordShake.model.LeagueCategory;
import co.edu.poli.WordShake.model.Player;
import co.edu.poli.WordShake.model.PositionCategory;
import co.edu.poli.WordShake.model.Team;

import java.sql.SQLException;
import java.util.List;

public interface PlayerDAO {
    List<Player> findAll()throws SQLException;

    Player getById(Integer id) throws SQLException;

    Player getByAllLeagues(String name) throws SQLException;

    Player getByPosition(String name, PositionCategory position) throws SQLException;

    Player getByTeamId(String name, Team teamId) throws SQLException;

    Player getByLeague(String name, LeagueCategory league) throws SQLException;

    Player getByThreeLeagues(String name,LeagueCategory league1, LeagueCategory league2, LeagueCategory league3) throws SQLException;





}
