package com.thomas.voetbaladministratie.screens;

import com.thomas.voetbaladministratie.components.Header;
import com.thomas.voetbaladministratie.dao.GameDAO;
import com.thomas.voetbaladministratie.dao.ResultDAO;
import com.thomas.voetbaladministratie.model.Game;
import com.thomas.voetbaladministratie.model.Result;
import javafx.collections.FXCollections;
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
        setSpacing(20);
        setPadding(new Insets(0, 0, 40, 0)); // top, right, bottom, left
        setAlignment(Pos.TOP_CENTER);

        // Header
        Header header = new Header(stage, "Resultaat invoeren");
        getChildren().add(header);

        // Titel
        Label title = new Label("Voer een wedstrijdresultaat in");
        title.getStyleClass().add("title-label");

        // Velden
        ComboBox<Game> gameBox = new ComboBox<>();
        List<Game> games = new GameDAO().getAllGames();
        gameBox.setItems(FXCollections.observableArrayList(games));
        gameBox.setPromptText("Selecteer een wedstrijd");
        gameBox.getStyleClass().add("form-field");

        TextField homeScoreField = new TextField();
        homeScoreField.setPromptText("Score thuisteam");
        homeScoreField.getStyleClass().add("form-field");

        TextField awayScoreField = new TextField();
        awayScoreField.setPromptText("Score uitteam");
        awayScoreField.getStyleClass().add("form-field");

        Label feedback = new Label();
        feedback.getStyleClass().add("subtext");

        // Knoppen
        Button saveButton = new Button("Opslaan");
        saveButton.getStyleClass().add("primary-button");

        Button backButton = new Button("Terug naar dashboard");
        backButton.getStyleClass().add("secondary-button");

        // Save-actie
        saveButton.setOnAction(e -> {
            Game selectedGame = gameBox.getValue();
            String homeScoreText = homeScoreField.getText();
            String awayScoreText = awayScoreField.getText();

            if (selectedGame == null || homeScoreText.isEmpty() || awayScoreText.isEmpty()) {
                feedback.setText("Vul alle velden in.");
                return;
            }

            try {
                int homeScore = Integer.parseInt(homeScoreText);
                int awayScore = Integer.parseInt(awayScoreText);

                Result result = new Result(0, selectedGame, homeScore, awayScore);
                boolean success = new ResultDAO().addResult(result);


                if (success) {
                    feedback.setText("Resultaat opgeslagen.");
                    gameBox.setValue(null);
                    homeScoreField.clear();
                    awayScoreField.clear();
                } else {
                    feedback.setText("Opslaan mislukt.");
                }

            } catch (NumberFormatException ex) {
                feedback.setText("Voer geldige nummers in.");
            }
        });

        // Terug-actie
        backButton.setOnAction(e -> {
            Scene scene = new Scene(new HomeScreen(stage), 800, 600);
            scene.getStylesheets().add(
                    getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/style.css").toExternalForm()
            );
            stage.setScene(scene);
        });

        // Layout
        getChildren().addAll(
                title,
                gameBox,
                homeScoreField,
                awayScoreField,
                saveButton,
                backButton,
                feedback
        );
    }
}
