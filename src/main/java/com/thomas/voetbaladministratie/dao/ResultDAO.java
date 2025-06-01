package com.thomas.voetbaladministratie.dao;

import com.thomas.voetbaladministratie.model.Game;
import com.thomas.voetbaladministratie.model.Result;
import com.thomas.voetbaladministratie.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAO {

    private final GameDAO gameDAO = new GameDAO();

    // Sla een nieuw resultaat op in de database
    public boolean addResult(Result result) {
        String sql = "INSERT INTO result (gameId, scoreHome, scoreAway) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, result.getGame().getGameId());
            stmt.setInt(2, result.getScoreHome());
            stmt.setInt(3, result.getScoreAway());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // ✅ Haal alle resultaten op, inclusief gekoppelde Game
    public List<Result> getAllResults() {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT * FROM result";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int gameId = rs.getInt("gameId");

                // Zoek bijpassende Game via GameDAO
                Game game = gameDAO.getAllGames().stream()
                        .filter(g -> g.getGameId() == gameId)
                        .findFirst()
                        .orElse(null);

                if (game != null) {
                    Result result = new Result(
                            rs.getInt("resultId"),
                            game,
                            rs.getInt("scoreHome"),
                            rs.getInt("scoreAway")
                    );
                    results.add(result);
                }
            }

        } catch (SQLException e) {
            System.err.println("Fout bij ophalen van resultaten: " + e.getMessage());
        }

        return results;
    }
}
