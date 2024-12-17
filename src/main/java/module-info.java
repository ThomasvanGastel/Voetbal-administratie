module com.thomas.voetbaladministratie {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.thomas.voetbaladministratie to javafx.fxml;
    exports com.thomas.voetbaladministratie;
}