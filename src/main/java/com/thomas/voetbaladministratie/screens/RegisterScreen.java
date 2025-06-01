package com.thomas.voetbaladministratie.screens;

import com.thomas.voetbaladministratie.dao.UserDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterScreen extends VBox {

    public RegisterScreen(Stage stage) {
        getStyleClass().add("main-layout");
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setPadding(new Insets(40));

        Label title = new Label("Account aanmaken");
        title.getStyleClass().add("title-label");

        TextField nameField = new TextField();
        nameField.setPromptText("Naam");
        nameField.getStyleClass().add("form-field");

        TextField emailField = new TextField();
        emailField.setPromptText("E-mailadres");
        emailField.getStyleClass().add("form-field");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Wachtwoord");
        passwordField.getStyleClass().add("form-field");

        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("trainer", "admin");
        roleBox.setPromptText("Rol");
        roleBox.getStyleClass().add("form-field");

        Label feedback = new Label();
        feedback.getStyleClass().add("subtext");

        Button registerButton = new Button("Registreren");
        registerButton.getStyleClass().add("primary-button");

        Button terugButton = new Button("Terug naar login");
        terugButton.getStyleClass().add("secondary-button");

        registerButton.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String role = roleBox.getValue();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || role == null) {
                feedback.setText("Vul alle velden in.");
                return;
            }

            boolean success = new UserDAO().register(name, email, password, role);
            if (success) {
                feedback.setText("Account aangemaakt! Je kunt nu inloggen.");
                Scene loginScene = new Scene(new LoginScreen(stage), 800, 600);
                loginScene.getStylesheets().add(
                        getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/homescreen.css").toExternalForm()
                );
                stage.setScene(loginScene);
            } else {
                feedback.setText("Registratie mislukt. Probeer het opnieuw.");
            }
        });

        terugButton.setOnAction(e -> {
            Scene loginScene = new Scene(new LoginScreen(stage), 800, 600);
            loginScene.getStylesheets().add(
                    getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/homescreen.css").toExternalForm()
            );
            stage.setScene(loginScene);
        });

        getChildren().addAll(title, nameField, emailField, passwordField, roleBox, registerButton, terugButton, feedback);
    }
}
