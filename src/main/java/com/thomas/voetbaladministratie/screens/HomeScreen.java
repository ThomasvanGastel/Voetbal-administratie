package com.thomas.voetbaladministratie.screens;

import com.thomas.voetbaladministratie.components.Header;
import com.thomas.voetbaladministratie.util.Session;
import com.thomas.voetbaladministratie.model.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeScreen extends VBox {

    public HomeScreen(Stage stage) {
        getStyleClass().add("main-layout");

        Header header = new Header(stage, "Dashboard");
        getChildren().add(header);

        // rest van je layout
        Label welkom = new Label("Welkom, " + Session.getCurrentUser().getName());
        welkom.getStyleClass().add("title-label");

        Button teamBtn = new Button("Teambeheer");
        teamBtn.getStyleClass().add("action-button");

        getChildren().addAll(welkom, teamBtn);
    }
}

