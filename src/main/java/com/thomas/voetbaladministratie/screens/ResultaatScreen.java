package com.thomas.voetbaladministratie.screens;

import com.thomas.voetbaladministratie.components.Header;
import com.thomas.voetbaladministratie.dao.GameDAO;
import com.thomas.voetbaladministratie.dao.ResultDAO;
import com.thomas.voetbaladministratie.model.Game;
import com.thomas.voetbaladministratie.model.Result;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ResultaatScreen extends VBox {

    public ResultaatScreen(Stage stage) {
        getStyleClass().add("main-layout");

        // Header
        Header header = new Header(stage, "Resultaat invoeren");
        getChildren().add(header);

        // Componenten
        Label title = new Label("Voer wedstrijdresultaat in");
        title.getStyleClass().add("title-label");

        ComboBox<Game> gameBox = new ComboBox<>();
        gameBox.getStyleClass().add("form-field");

        TextField scoreHomeField = new TextField();
        scoreHomeField.setPromptText("Score thuisteam");
        scoreHomeField.getStyleClass().add("form-field");

        TextField scoreAwayField = new TextField();
        scoreAwayField.setPromptText("Score uitteam");
        scoreAwayField.getStyleClass().add("form-field");

        Label feedback = new Label();
        feedback.getStyleClass().add("subtext");

        Button saveButton = new Button("Resultaat opslaan");
        saveButton.getStyleClass().add("primary-button");

        Button backButton = new Button("Terug");
        backButton.getStyleClass().add("secondary-button");

        // Data inladen
        List<Game> games = new GameDAO().getAllGames();
        gameBox.getItems().addAll(games);

        // Acties
        saveButton.setOnAction(e -> {
            Game selectedGame = gameBox.getValue();
            String scoreHome = scoreHomeField.getText();
            String scoreAway = scoreAwayField.getText();

            if (selectedGame == null || scoreHome.isEmpty() || scoreAway.isEmpty()) {
                feedback.setText("Vul alle velden in.");
                return;
            }

            try {
                int home = Integer.parseInt(scoreHome);
                int away = Integer.parseInt(scoreAway);

                Result result = new Result(0, selectedGame, home, away);
                new ResultDAO().addResult(result);
                feedback.setText("Resultaat opgeslagen!");
                scoreHomeField.clear();
                scoreAwayField.clear();

            } catch (NumberFormatException ex) {
                feedback.setText("Voer geldige numerieke scores in.");
            }
        });

        backButton.setOnAction(e -> {
            Scene homeScene = new Scene(new HomeScreen(stage), 800, 600);
            homeScene.getStylesheets().add(
                    getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/homescreen.css").toExternalForm()
            );
            stage.setScene(homeScene);
        });

        getChildren().addAll(title, gameBox, scoreHomeField, scoreAwayField, saveButton, backButton, feedback);
        setSpacing(20);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
    }
}
