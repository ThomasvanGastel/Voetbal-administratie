package com.thomas.voetbaladministratie.dao;

import com.thomas.voetbaladministratie.model.Game;
import com.thomas.voetbaladministratie.model.Team;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameDAOTest {

    private final GameDAO gameDAO = new GameDAO();
    private final TeamDAO teamDAO = new TeamDAO();

    @Test
    public void testAddGame() {
        List<Team> teams = teamDAO.getAllTeams();
        assertTrue(teams.size() >= 2);

        Game game = new Game(0, teams.get(0), teams.get(1), LocalDateTime.now(), "Testlocatie");
        boolean result = gameDAO.addGame(game);

        assertTrue(result);
    }
}
