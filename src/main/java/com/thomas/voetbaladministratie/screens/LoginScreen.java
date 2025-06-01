package com.thomas.voetbaladministratie.screens;

import com.thomas.voetbaladministratie.dao.UserDAO;
import com.thomas.voetbaladministratie.model.User;
import com.thomas.voetbaladministratie.util.Session;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen extends VBox {

    public LoginScreen(Stage stage) {
        getStyleClass().add("main-layout");
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setPadding(new Insets(40));

        Label title = new Label("Inloggen");
        title.getStyleClass().add("title-label");

        TextField emailField = new TextField();
        emailField.setPromptText("E-mailadres");
        emailField.getStyleClass().add("form-field");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Wachtwoord");
        passwordField.getStyleClass().add("form-field");

        Label feedback = new Label();
        feedback.getStyleClass().add("subtext");

        Button loginButton = new Button("Inloggen");
        loginButton.getStyleClass().add("primary-button");

        Button registerButton = new Button("Registreren");
        registerButton.getStyleClass().add("secondary-button");

        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            User user = new UserDAO().login(email, password);
            if (user != null) {
                Session.getInstance().getCurrentUser();
                Scene homeScene = new Scene(new HomeScreen(stage), 800, 600);
                homeScene.getStylesheets().add(
                        getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/style.css").toExternalForm()
                );
                stage.setScene(homeScene);
            } else {
                feedback.setText("Verkeerde inloggegevens.");
            }
        });

        registerButton.setOnAction(e -> {
            Scene registerScene = new Scene(new RegisterScreen(stage), 800, 600);
            registerScene.getStylesheets().add(
                    getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/style.css").toExternalForm()
            );
            stage.setScene(registerScene);
        });

        getChildren().addAll(title, emailField, passwordField, loginButton, registerButton, feedback);
    }
}
