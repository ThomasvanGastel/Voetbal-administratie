package com.thomas.voetbaladministratie.screens;

import com.thomas.voetbaladministratie.dao.UserDAO;
import com.thomas.voetbaladministratie.model.User;
import com.thomas.voetbaladministratie.util.Session;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen extends VBox {

    public LoginScreen(Stage stage) {
        setSpacing(10);
        setPadding(new Insets(20));

        Label title = new Label("Inloggen");

        TextField emailField = new TextField();
        emailField.setPromptText("E-mail");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Wachtwoord");

        Label feedback = new Label();
        Button registerButton = new Button("Account aanmaken");
        registerButton.setOnAction(e -> stage.setScene(new Scene(new RegisterScreen(stage), 800, 600)));


        Button loginButton = new Button("Log in");
        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            User user = new UserDAO().login(email, password);
            if (user != null) {
                Session.setCurrentUser(user); // zet gebruiker in sessie
                stage.setScene(new Scene(new HomeScreen(stage), 800, 600));
            }

        });

        getChildren().addAll(title, emailField, passwordField, loginButton, registerButton, feedback);
    }
}
