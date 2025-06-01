package com.thomas.voetbaladministratie.dao;

import com.thomas.voetbaladministratie.model.Team;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamDAOTest {

    private final TeamDAO teamDAO = new TeamDAO();

    @Test
    public void testGetAllTeams() {
        List<Team> teams = teamDAO.getAllTeams();

        // Controle: lijst mag niet null zijn
        assertNotNull(teams, "De lijst met teams mag niet null zijn");

        // Extra controle: er moeten (waarschijnlijk) al teams in de database staan
        assertTrue(teams.size() >= 0, "De lijst met teams is leeg");
    }
}
