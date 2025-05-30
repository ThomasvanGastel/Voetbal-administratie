package com.thomas.voetbaladministratie.screens;


import com.thomas.voetbaladministratie.util.Session;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import com.thomas.voetbaladministratie.dao.TeamDAO;
import com.thomas.voetbaladministratie.model.Team;
import javafx.collections.FXCollections;
import javafx.scene.control.*;


import java.util.List;

public class TeamScreen extends VBox {

    private final TeamDAO teamDAO = new TeamDAO();

    public TeamScreen(Stage stage) {
        setSpacing(10);
        setPadding(new javafx.geometry.Insets(20));

        Label title = new Label("Teambeheer");
        ListView<Team> teamList = new ListView<>();
        updateList(teamList);

        TextField naamField = new TextField();
        naamField.setPromptText("Teamnaam");

        TextField leeftijdField = new TextField();
        leeftijdField.setPromptText("Leeftijdscategorie");

        Button addButton = new Button("Team toevoegen");
        addButton.setOnAction(e -> {
            String naam = naamField.getText();
            String leeftijd = leeftijdField.getText();

            if (!naam.isEmpty() && !leeftijd.isEmpty()) {
                Team nieuwTeam = new Team(0, naam, leeftijd, Session.getCurrentUser());
                teamDAO.addTeam(nieuwTeam);
                updateList(teamList);
                naamField.clear();
                leeftijdField.clear();
            }
        });

        Button terugButton = new Button("Terug");
        terugButton.setOnAction(e -> stage.setScene(new Scene(new HomeScreen(stage), 800, 600)));

        getChildren().addAll(title, teamList, naamField, leeftijdField, addButton, terugButton);
    }

    private void updateList(ListView<Team> list) {
        List<Team> teams = teamDAO.getAllTeams();
        list.setItems(FXCollections.observableArrayList(teams));
    }
}

