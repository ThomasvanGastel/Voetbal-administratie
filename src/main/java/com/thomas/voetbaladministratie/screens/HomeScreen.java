package com.thomas.voetbaladministratie.screens;

import com.thomas.voetbaladministratie.components.Header;
import com.thomas.voetbaladministratie.model.User;
import com.thomas.voetbaladministratie.util.Session;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeScreen extends VBox {

    public HomeScreen(Stage stage) {
        getStyleClass().add("main-layout");
        setSpacing(20);
        setPadding(new Insets(0, 0, 40, 0)); // top, right, bottom, left
        setAlignment(Pos.TOP_CENTER);

        User user = Session.getCurrentUser();

        // Voeg header toe (bovenaan)
        Header header = new Header(stage, "Dashboard");
        getChildren().add(header);

        // Welkomtekst
        Label welcome = new Label("Welkom, " + user.getName());
        welcome.getStyleClass().add("title-label");
        getChildren().add(welcome);

        // Knoppen op basis van rol
        if ("admin".equals(user.getRole()) || "trainer".equals(user.getRole())) {
            Button teambeheerButton = new Button("Teambeheer");
            teambeheerButton.getStyleClass().add("action-button");

            teambeheerButton.setOnAction(e -> {
                Scene teamScene = new Scene(new TeamScreen(stage), 800, 600);
                teamScene.getStylesheets().add(
                        getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/style.css").toExternalForm()
                );
                stage.setScene(teamScene);
            });

            getChildren().add(teambeheerButton);
        }


        if ("admin".equals(user.getRole())) {
            Button resultaatButton = new Button("Resultaten invoeren");
            resultaatButton.getStyleClass().add("action-button");

            resultaatButton.setOnAction(e -> {
                Scene resultScene = new Scene(new ResultaatScreen(stage), 800, 600);
                resultScene.getStylesheets().add(
                        getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/style.css").toExternalForm()
                );
                stage.setScene(resultScene);
            });

            getChildren().add(resultaatButton);
        }




        Button wedstrijdButton = new Button("Wedstrijden");
        wedstrijdButton.getStyleClass().add("action-button");

        wedstrijdButton.setOnAction(e -> {
            Scene wedstrijdScene = new Scene(new WedstrijdScreen(stage), 800, 600);
            wedstrijdScene.getStylesheets().add(
                    getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/style.css").toExternalForm()
            );
            stage.setScene(wedstrijdScene);
        });

        getChildren().add(wedstrijdButton);

    }
}
