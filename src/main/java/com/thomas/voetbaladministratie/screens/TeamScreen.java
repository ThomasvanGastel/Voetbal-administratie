package com.thomas.voetbaladministratie.screens;

import com.thomas.voetbaladministratie.components.Header;
import com.thomas.voetbaladministratie.dao.TeamDAO;
import com.thomas.voetbaladministratie.model.Team;
import com.thomas.voetbaladministratie.model.User;
import com.thomas.voetbaladministratie.util.Session;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TeamScreen extends VBox {

    public TeamScreen(Stage stage) {
        getStyleClass().add("main-layout");
        setSpacing(20);
        setPadding(new Insets(0, 0, 40, 0)); // top, right, bottom, left
        setAlignment(Pos.TOP_CENTER);
        User trainer = Session.getInstance().getCurrentUser();


        // Header
        Header header = new Header(stage, "Teambeheer");
        getChildren().add(header);

        // Titel
        Label title = new Label("Nieuw team toevoegen");
        title.getStyleClass().add("title-label");

        // Formuliervelden
        TextField naamField = new TextField();
        naamField.setPromptText("Teamnaam");
        naamField.getStyleClass().add("form-field");

        TextField leeftijdField = new TextField();
        leeftijdField.setPromptText("Leeftijdscategorie");
        leeftijdField.getStyleClass().add("form-field");

        Label feedback = new Label();
        feedback.getStyleClass().add("subtext");

        // Knoppen
        Button opslaanButton = new Button("Team opslaan");
        opslaanButton.getStyleClass().add("primary-button");

        Button terugButton = new Button("Terug");
        terugButton.getStyleClass().add("secondary-button");

        // Opslaanactie
        opslaanButton.setOnAction(e -> {
            String naam = naamField.getText();
            String leeftijd = leeftijdField.getText();

            if (naam.isEmpty() || leeftijd.isEmpty()) {
                feedback.setText("Vul alle velden in.");
                return;
            }

            Team nieuwTeam = new Team(0, naam, leeftijd, trainer);
            boolean success = new TeamDAO().addTeam(nieuwTeam);

            if (success) {
                feedback.setText("Team succesvol toegevoegd!");
                naamField.clear();
                leeftijdField.clear();
            } else {
                feedback.setText("Opslaan mislukt.");
            }
        });

        // Terugactie
        terugButton.setOnAction(e -> {
            Scene scene = new Scene(new HomeScreen(stage), 800, 600);
            scene.getStylesheets().add(
                    getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/style.css").toExternalForm()
            );
            stage.setScene(scene);
        });

        getChildren().addAll(title, naamField, leeftijdField, opslaanButton, terugButton, feedback);
    }
}
