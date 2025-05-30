package com.thomas.voetbaladministratie.screens;

import com.thomas.voetbaladministratie.model.User;
import com.thomas.voetbaladministratie.util.Session;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class WedstrijdScreen extends VBox {
    public WedstrijdScreen(Stage stage) {
        setPadding(new Insets(20));
        setSpacing(10);
        User user = Session.getCurrentUser();


        Label label = new Label("Wedstrijdbeheer scherm");
        Button backButton = new Button("Terug");

        backButton.setOnAction(e -> stage.setScene(new Scene(new HomeScreen(stage), 800, 600)));

        getChildren().addAll(label, backButton);
    }
}

