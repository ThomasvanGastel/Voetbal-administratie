package com.thomas.voetbaladministratie.screens;

import com.thomas.voetbaladministratie.components.Header;
import com.thomas.voetbaladministratie.dao.GameDAO;
import com.thomas.voetbaladministratie.model.Game;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class WedstrijdScreen extends VBox {
    private final Stage stage;
    private final GameDAO gameDAO = new GameDAO();
    private TableView<Game> tableView;

    public WedstrijdScreen(Stage stage) {
        this.stage = stage;

        // Layoutinstellingen
        setSpacing(10);
        setPadding(new Insets(20));
        getStyleClass().add("screen");

        // Pagina-header
        getChildren().add(new Header(stage, "Wedstrijden"));

        Button backButton = new Button("Terug");
        backButton.getStyleClass().add("secondary-button");
        backButton.setOnAction(e -> {
            Scene scene = new Scene(new HomeScreen(stage), 800, 600);
            scene.getStylesheets().add(
                    getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/style.css").toExternalForm()
            );
            stage.setScene(scene);
        });

        // Knop om nieuwe wedstrijd toe te voegen
        Button addButton = new Button("Wedstrijd toevoegen");
        addButton.getStyleClass().add("primary-button");
        addButton.setOnAction(e -> {
            Scene scene = new Scene(new WedstrijdToevoegenScreen(stage), 800, 600);
            scene.getStylesheets().add(getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/style.css").toExternalForm());
            stage.setScene(scene);
        });

        // Topbalk met knop rechts
        HBox topBar = new HBox();
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        topBar.getChildren().addAll(backButton,spacer, addButton);
        topBar.setAlignment(Pos.CENTER_LEFT);

        // Tabel instellen en vullen
        setupTable();

        // Scherm opbouwen
        getChildren().addAll(topBar, tableView);
        loadWedstrijden();
    }

    // Tabelstructuur instellen
    private void setupTable() {
        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // voorkomt lege kolom

        // Kolom: Thuisteam
        TableColumn<Game, String> homeTeamCol = new TableColumn<>("Thuisteam");
        homeTeamCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHomeTeam().getTeamName()));

        // Kolom: Uitteam
        TableColumn<Game, String> awayTeamCol = new TableColumn<>("Uitteam");
        awayTeamCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAwayTeam().getTeamName()));

        // Kolom: Datum
        TableColumn<Game, String> dateCol = new TableColumn<>("Datum");
        dateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate().toLocalDate().toString()));

        // Kolom: Locatie
        TableColumn<Game, String> locationCol = new TableColumn<>("Locatie");
        locationCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLocation()));

        // Kolom: Acties (bewerken/verwijderen)
        TableColumn<Game, Void> actionCol = new TableColumn<>("Actie");
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final HBox actionBox = new HBox(10);
            private final Button editButton = new Button("Bewerk");
            private final Button deleteButton = new Button("Verwijder");

            {
                // Bewerken
                editButton.getStyleClass().add("secondary-button");
                editButton.setOnAction(e -> {
                    Game selected = getTableView().getItems().get(getIndex());
                    Scene scene = new Scene(new WedstrijdBewerkenScreen(stage, selected), 800, 600);
                    scene.getStylesheets().add(getClass().getResource("/com/thomas/voetbaladministratie/stylesheet/style.css").toExternalForm());
                    stage.setScene(scene);
                });

                // Verwijderen
                deleteButton.getStyleClass().add("danger-button");
                deleteButton.setOnAction(e -> {
                    Game selected = getTableView().getItems().get(getIndex());

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Bevestiging");
                    alert.setHeaderText("Weet je zeker dat je deze wedstrijd wilt verwijderen?");
                    alert.setContentText(selected.toString());

                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            boolean success = gameDAO.deleteGame(selected.getGameId());
                            if (success) {
                                tableView.getItems().remove(selected);
                            } else {
                                Alert error = new Alert(Alert.AlertType.ERROR);
                                error.setTitle("Fout");
                                error.setHeaderText("Verwijderen mislukt");
                                error.show();
                            }
                        }
                    });
                });

                actionBox.getChildren().addAll(editButton, deleteButton);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : actionBox);
            }
        });

        // Kolommen toevoegen aan de tabel
        tableView.getColumns().addAll(homeTeamCol, awayTeamCol, dateCol, locationCol, actionCol);
    }

    // Laad alle wedstrijden in de tabel
    private void loadWedstrijden() {
        tableView.getItems().setAll(gameDAO.getAllGames());
    }
}
