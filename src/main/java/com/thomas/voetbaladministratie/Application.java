package com.thomas.voetbaladministratie;

import com.thomas.voetbaladministratie.screens.HomeScreen;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) {
        HomeScreen homeScreen = new HomeScreen(primaryStage);
        Scene scene = new Scene(homeScreen, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Voetbal Administratie");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
