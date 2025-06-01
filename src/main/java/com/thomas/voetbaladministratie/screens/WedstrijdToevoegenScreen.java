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
import java.time.LocalDateTime;
import java.util.List;

public class WedstrijdToevoegenScreen extends VBox {

    public WedstrijdToevoegenScreen(Stage stage) {
        getStyleClass().add("main-layout");
        setSpacing(20);
        setPadding(new Insets(0, 0, 40, 0)); // top, right, bottom, left
        setAlignment(Pos.TOP_CENTER);

        // Pagina-header
        Header header = new Header(stage, "Wedstrijd aanmaken");
        getChildren().add(header);

        // Titel van het formulier
        Label title = new Label("Nieuwe wedstrijd plannen");
        title.getStyleClass().add("title-label");

        // Ophalen van teams
        List<Team> teams = new TeamDAO().getAllTeams();

        // Selectievak voor thuisteam
        ComboBox<Team> team1Box = new ComboBox<>();
        team1Box.getItems().addAll(teams);
        team1Box.setPromptText("Thuisteam");
        team1Box.getStyleClass().add("form-field");

        // Selectievak voor uitteam
        ComboBox<Team> team2Box = new ComboBox<>();
        team2Box.getItems().addAll(teams);
        team2Box.setPromptText("Uitteam");
        team2Box.getStyleClass().add("form-field");

        // Datumkiezer
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Datum");
        datePicker.getStyleClass().add("form-field");

        // Tekstveld voor locatie
        TextField locationField = new TextField();
        locationField.setPromptText("Locatie");
        locationField.getStyleClass().add("form-field");

        // Label voor feedbackberichten
        Label feedback = new Label();
        feedback.getStyleClass().add("subtext");

        // Opslaan-knop
        Button saveButton = new Button("Wedstrijd opslaan");
        saveButton.getStyleClass().add("primary-button");

        // Opslaan-actie
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

            // Aanmaken en opslaan van wedstrijd
            LocalDateTime dateTime = date.atStartOfDay();
            Game game = new Game(0, team1, team2, dateTime, location);
            boolean success = new GameDAO().addGame(game);

            // Feedback aan gebruiker
            if (success) {
                feedback.setText("Wedstrijd opgeslagen!");
                team1Box.setValue(null);
                team2Box.setValue(null);
                datePicker.setValue(null);
                locationField.clear();
            } else {
                feedback.setText("Opslaan mislukt.");
            }
        });

        // Terugknop naar het overzicht
        Button backButton = new Button("Terug");
        backButton.getStyleClass().add("secondary-button");
        backButton.setOnAction(e -> {
            Scene scene = new Scene(new WedstrijdScreen(stage), 800, 600);
            scene.getStylesheets().add(
                    getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/style.css").toExternalForm()
            );
            stage.setScene(scene);
        });

        // Componenten toevoegen aan layout
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
