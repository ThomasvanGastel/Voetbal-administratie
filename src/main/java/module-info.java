module com.thomas.voetbaladministratie {
    requires javafx.controls;
    requires java.sql;
    requires java.desktop;
    requires jdk.httpserver;

    exports com.thomas.voetbaladministratie;
    exports com.thomas.voetbaladministratie.dao;
    exports com.thomas.voetbaladministratie.model;
    exports com.thomas.voetbaladministratie.util;
}
