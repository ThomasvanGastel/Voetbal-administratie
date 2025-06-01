package com.thomas.voetbaladministratie.dao;

import com.thomas.voetbaladministratie.model.Game;
import com.thomas.voetbaladministratie.model.Team;
import com.thomas.voetbaladministratie.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class GameDAO {
    private final TeamDAO teamDAO = new TeamDAO();

    // Haalt alle wedstrijden op uit de database
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM game";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("gameId");
                Team home = teamDAO.getById(rs.getInt("homeTeamId"));
                Team away = teamDAO.getById(rs.getInt("awayTeamId"));
                LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
                String location = rs.getString("location");

                games.add(new Game(id, home, away, date, location));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }

    // Voegt een nieuwe wedstrijd toe aan de database
    public boolean addGame(Game game) {
        String sql = "INSERT INTO game (homeTeamId, awayTeamId, date, location) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, game.getHomeTeam().getTeamId());
            stmt.setInt(2, game.getAwayTeam().getTeamId());
            stmt.setTimestamp(3, Timestamp.valueOf(game.getDate()));
            stmt.setString(4, game.getLocation());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteGame(int gameId) {
        String sql = "DELETE FROM game WHERE gameId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, gameId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Werkt een bestaande wedstrijd bij in de database
    public boolean updateGame(Game game) {
        String sql = "UPDATE game SET homeTeamId = ?, awayTeamId = ?, date = ?, location = ? WHERE gameId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, game.getHomeTeam().getTeamId());
            stmt.setInt(2, game.getAwayTeam().getTeamId());
            stmt.setTimestamp(3, Timestamp.valueOf(game.getDate()));
            stmt.setString(4, game.getLocation());
            stmt.setInt(5, game.getGameId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
