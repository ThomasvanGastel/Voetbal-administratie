package screens;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class ResultaatScreen extends VBox {
    public ResultaatScreen(Stage stage) {
        setPadding(new Insets(20));
        setSpacing(10);

        Label label = new Label("Spelerbeheer scherm");
        Button backButton = new Button("Terug");

        backButton.setOnAction(e -> stage.setScene(new Scene(new HomeScreen(stage), 800, 600)));

        getChildren().addAll(label, backButton);
    }
}
