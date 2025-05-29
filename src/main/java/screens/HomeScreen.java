package com.thomas.voetbaladministratie.screens;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeScreen extends VBox {
    public HomeScreen(Stage stage) {
        setSpacing(15);
        setPadding(new Insets(30));

        Label title = new Label("Welkom bij de Voetbal Administratie");

        Button btnTeams = new Button("Teambeheer");
        Button btnWedstrijden = new Button("Wedstrijden");
        Button btnSpelers = new Button("Spelers");

        btnTeams.setOnAction(e -> {
            // voorbeeld: later echte TeamScreen toevoegen
            System.out.println("Naar Teambeheer");
        });

        getChildren().addAll(title, btnTeams, btnWedstrijden, btnSpelers);
    }
}
