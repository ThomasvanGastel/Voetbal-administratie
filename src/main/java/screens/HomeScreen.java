package screens;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class HomeScreen extends VBox {
    public HomeScreen(Stage stage) {
        setPadding(new Insets(20));
        setSpacing(10);

        Label title = new Label("Welkom bij de Voetbal Administratie");

        Button btnTeams = new Button("Teambeheer");
        Button btnSpelers = new Button("Spelerbeheer");
        Button btnWedstrijden = new Button("Wedstrijdbeheer");

        btnTeams.setOnAction(e -> stage.setScene(new Scene(new TeamScreen(stage), 800, 600)));
        btnSpelers.setOnAction(e -> stage.setScene(new Scene(new SpelerScreen(stage), 800, 600)));
        btnWedstrijden.setOnAction(e -> stage.setScene(new Scene(new WedstrijdScreen(stage), 800, 600)));

        getChildren().addAll(title, btnTeams, btnSpelers, btnWedstrijden);
    }
}
