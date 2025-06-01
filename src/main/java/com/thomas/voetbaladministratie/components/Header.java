package com.thomas.voetbaladministratie.components;

import com.thomas.voetbaladministratie.util.Session;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Header extends BorderPane {

    public Header(Stage stage, String titleText) {
        getStyleClass().add("header");
        setPadding(new Insets(10, 20, 10, 20));

        Label title = new Label(titleText);
        title.getStyleClass().add("header-title");

        Label userInfo = new Label("Ingelogd als: " + Session.getCurrentUser().getName());
        userInfo.getStyleClass().add("header-user");

        HBox left = new HBox(10, title, userInfo);

        Button logoutButton = new Button("Log uit");
        logoutButton.getStyleClass().add("header-button");
        logoutButton.setOnAction(e -> {
            Session.setCurrentUser(null);
            stage.setScene(new javafx.scene.Scene(new com.thomas.voetbaladministratie.screens.LoginScreen(stage), 800, 600));
        });

        setLeft(left);
        setRight(logoutButton);
    }
}
