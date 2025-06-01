package com.thomas.voetbaladministratie;


import com.thomas.voetbaladministratie.screens.LoginScreen;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) {
        LoginScreen root = new LoginScreen(stage);
        Scene scene = new Scene(root, 800, 600);

        // ✅ CSS toevoegen
        scene.getStylesheets().add(
                getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/style.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.setTitle("Voetbalapplicatie");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

