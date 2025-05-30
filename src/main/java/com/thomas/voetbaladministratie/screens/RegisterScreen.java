package com.thomas.voetbaladministratie.screens;

import com.thomas.voetbaladministratie.dao.UserDAO;
import com.thomas.voetbaladministratie.model.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterScreen extends VBox {

    public RegisterScreen(Stage stage) {
        setSpacing(10);
        setPadding(new Insets(20));

        Label title = new Label("Account aanmaken");

        TextField nameField = new TextField();
        nameField.setPromptText("Naam");

        TextField emailField = new TextField();
        emailField.setPromptText("E-mail");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Wachtwoord");

        ChoiceBox<String> roleBox = new ChoiceBox<>();
        roleBox.getItems().addAll("trainer", "admin");
        roleBox.setValue("trainer");

        Label feedback = new Label();

        Button registerButton = new Button("Registreer");
        registerButton.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String role = roleBox.getValue();

            User nieuweUser = new User(0, name, email, password, role);
            boolean success = new UserDAO().register(nieuweUser);

            if (success) {
                feedback.setText("Account aangemaakt! Je kunt nu inloggen.");
                stage.setScene(new Scene(new LoginScreen(stage), 800, 600));
            } else {
                feedback.setText("Registratie mislukt. E-mail bestaat mogelijk al.");
            }
        });

        Button terugButton = new Button("Terug naar login");
        terugButton.setOnAction(e -> stage.setScene(new Scene(new LoginScreen(stage), 800, 600)));

        getChildren().addAll(title, nameField, emailField, passwordField, roleBox, registerButton, terugButton, feedback);
    }
}
