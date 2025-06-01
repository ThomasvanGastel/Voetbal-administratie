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

public class RegisterScreen extends VBox {

    public RegisterScreen(Stage stage) {
        getStyleClass().add("main-layout");
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setPadding(new Insets(0, 0, 40, 0)); // top, right, bottom, left

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
                // Gebruiker ophalen en sessie instellen
                User newUser = new UserDAO().getUserByEmail(email);
                Session.getInstance().setCurrentUser(newUser);

                // Doorsturen naar dashboard
                Scene scene = new Scene(new HomeScreen(stage), 800, 600);
                scene.getStylesheets().add(
                        getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/style.css").toExternalForm()
                );
                stage.setScene(scene);
            } else {
                feedback.setText("Registratie mislukt. Probeer het opnieuw.");
            }

        });

        terugButton.getStyleClass().add("secondary-button");
        terugButton.setOnAction(e -> {
            Scene scene = new Scene(new LoginScreen(stage), 800, 600);
            scene.getStylesheets().add(
                    getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/style.css").toExternalForm()
            );
            stage.setScene(scene);
        });

        getChildren().addAll(title, nameField, emailField, passwordField, roleBox, registerButton, terugButton, feedback);
    }
}
