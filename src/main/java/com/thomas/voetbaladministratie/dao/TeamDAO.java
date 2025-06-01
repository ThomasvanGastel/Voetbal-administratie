package com.thomas.voetbaladministratie.dao;

import com.thomas.voetbaladministratie.model.Team;
import com.thomas.voetbaladministratie.model.User;
import com.thomas.voetbaladministratie.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {

    private final UserDAO userDAO = new UserDAO();

    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM team";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int teamId = rs.getInt("teamId");
                String name = rs.getString("teamName");
                String category = rs.getString("ageCategory");
                int trainerId = rs.getInt("trainerId");

                User trainer = userDAO.getUserById(trainerId);

                teams.add(new Team(teamId, name, category, trainer));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teams;
    }

    public void addTeam(Team team) {
        String sql = "INSERT INTO team (teamName, ageCategory, trainerId) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, team.getTeamName());
            stmt.setString(2, team.getAgeCategory());
            stmt.setInt(3, team.getTrainer().getUserId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Team getById(int id) {
        String sql = "SELECT * FROM team WHERE teamId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Team(
                        rs.getInt("teamId"),
                        rs.getString("teamName"),
                        rs.getString("ageCategory"),
                        new UserDAO().getUserById(rs.getInt("trainerId"))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
