package com.thomas.voetbaladministratie.screens;

import com.thomas.voetbaladministratie.util.Session;
import com.thomas.voetbaladministratie.model.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeScreen extends VBox {

    public HomeScreen(Stage stage) {
        User user = Session.getCurrentUser();

        Label welcome = new Label("Welkom, " + user.getName());
        getChildren().add(welcome);

        // Voorbeeld: rolgebaseerde knoppen
        if ("admin".equals(user.getRole())) {
            Button beheerGebruikers = new Button("Gebruikersbeheer");
            getChildren().add(beheerGebruikers);
        }

        // Meer knoppen hier...
        Button teamButton = new Button("Teambeheer");
        teamButton.setOnAction(e ->
                stage.setScene(new javafx.scene.Scene(new TeamScreen(stage), 800, 600))
        );
        getChildren().add(teamButton);
    }
}
