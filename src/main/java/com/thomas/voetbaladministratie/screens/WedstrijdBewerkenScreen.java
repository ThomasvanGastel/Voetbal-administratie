package com.thomas.voetbaladministratie.screens;

import com.thomas.voetbaladministratie.components.Header;
import com.thomas.voetbaladministratie.dao.GameDAO;
import com.thomas.voetbaladministratie.dao.TeamDAO;
import com.thomas.voetbaladministratie.model.Game;
import com.thomas.voetbaladministratie.model.Team;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class WedstrijdBewerkenScreen extends VBox {

    public WedstrijdBewerkenScreen(Stage stage, Game existingGame) {
        getStyleClass().add("main-layout");
        setSpacing(20);
        setPadding(new Insets(0, 0, 40, 0));
        setAlignment(Pos.TOP_CENTER);

        // Pagina-header
        Header header = new Header(stage, "Wedstrijd bewerken");
        getChildren().add(header);

        // Titel boven formulier
        Label title = new Label("Wedstrijdgegevens bewerken");
        title.getStyleClass().add("title-label");

        // Teamselectie
        List<Team> teams = new TeamDAO().getAllTeams();

        ComboBox<Team> team1Box = new ComboBox<>();
        team1Box.getItems().addAll(teams);
        team1Box.setPromptText("Thuisteam");
        team1Box.getStyleClass().add("form-field");
        team1Box.setValue(existingGame.getHomeTeam());

        ComboBox<Team> team2Box = new ComboBox<>();
        team2Box.getItems().addAll(teams);
        team2Box.setPromptText("Uitteam");
        team2Box.getStyleClass().add("form-field");
        team2Box.setValue(existingGame.getAwayTeam());

        // Datumkiezer
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Datum");
        datePicker.getStyleClass().add("form-field");
        datePicker.setValue(existingGame.getDate().toLocalDate());

        // Locatieveld
        TextField locationField = new TextField();
        locationField.setPromptText("Locatie");
        locationField.getStyleClass().add("form-field");
        locationField.setText(existingGame.getLocation());

        // Label voor feedback
        Label feedback = new Label();
        feedback.getStyleClass().add("subtext");

        // Opslaan-knop
        Button saveButton = new Button("Wijzigingen opslaan");
        saveButton.getStyleClass().add("primary-button");

        // Actie bij opslaan
        saveButton.setOnAction(e -> {
            Team team1 = team1Box.getValue();
            Team team2 = team2Box.getValue();
            LocalDate date = datePicker.getValue();
            String location = locationField.getText();

            // Validatie
            if (team1 == null || team2 == null || date == null || location.isEmpty()) {
                feedback.setText("Vul alle velden in.");
                return;
            }

            if (team1.equals(team2)) {
                feedback.setText("Teams mogen niet gelijk zijn.");
                return;
            }

            // Gegevens bijwerken
            existingGame.setHomeTeam(team1);
            existingGame.setAwayTeam(team2);
            existingGame.setDate(date.atStartOfDay());
            existingGame.setLocation(location);

            boolean success = new GameDAO().updateGame(existingGame);

            // Feedback aan gebruiker
            if (success) {
                feedback.setText("Wedstrijd succesvol bijgewerkt.");
            } else {
                feedback.setText("Bijwerken mislukt.");
            }
        });

        // Terugknop naar overzicht
        Button backButton = new Button("Terug");
        backButton.getStyleClass().add("secondary-button");
        backButton.setOnAction(e -> {
            Scene scene = new Scene(new WedstrijdScreen(stage), 800, 600);
            scene.getStylesheets().add(
                    getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/style.css").toExternalForm()
            );
            stage.setScene(scene);
        });

        // Opbouw van het scherm
        getChildren().addAll(
                title,
                team1Box,
                team2Box,
                datePicker,
                locationField,
                saveButton,
                backButton,
                feedback
        );
    }
}
