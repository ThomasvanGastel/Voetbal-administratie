package com.thomas.voetbaladministratie;


import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) {
        // Start met LoginScreen
        com.thomas.voetbaladministratie.screens.LoginScreen loginScreen = new com.thomas.voetbaladministratie.screens.LoginScreen(primaryStage);
        Scene scene = new Scene(loginScreen, 800, 600);

        primaryStage.setTitle("Voetbal Administratie");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
