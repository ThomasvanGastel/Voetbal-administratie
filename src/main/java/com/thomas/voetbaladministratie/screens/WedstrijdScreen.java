package com.thomas.voetbaladministratie.screens;

import com.thomas.voetbaladministratie.dao.GameDAO;
import com.thomas.voetbaladministratie.dao.TeamDAO;
import com.thomas.voetbaladministratie.model.Game;
import com.thomas.voetbaladministratie.model.Team;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.List;

public class WedstrijdScreen extends VBox {

    private final GameDAO gameDAO = new GameDAO();
    private final TeamDAO teamDAO = new TeamDAO();

    public WedstrijdScreen(Stage stage) {
        setSpacing(10);
        setPadding(new javafx.geometry.Insets(20));

        Label title = new Label("Wedstrijden");

        ListView<Game> list = new ListView<>();
        updateList(list);

        ChoiceBox<Team> homeBox = new ChoiceBox<>(FXCollections.observableArrayList(teamDAO.getAllTeams()));
        ChoiceBox<Team> awayBox = new ChoiceBox<>(FXCollections.observableArrayList(teamDAO.getAllTeams()));
        TextField locationField = new TextField();
        locationField.setPromptText("Locatie");

        Button add = new Button("Voeg wedstrijd toe");
        add.setOnAction(e -> {
            Team home = homeBox.getValue();
            Team away = awayBox.getValue();
            String location = locationField.getText();

            if (home != null && away != null && !home.equals(away) && !location.isBlank()) {
                Game game = new Game(0, home, away, LocalDateTime.now(), location);
                gameDAO.addGame(game);
                updateList(list);
            }
        });

        Button terug = new Button("Terug");
        terug.setOnAction(e -> stage.setScene(new Scene(new HomeScreen(stage), 800, 600)));

        getChildren().addAll(title, list, homeBox, awayBox, locationField, add, terug);
    }

    private void updateList(ListView<Game> list) {
        list.setItems(FXCollections.observableArrayList(gameDAO.getAllGames()));
    }
}
