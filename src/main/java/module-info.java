module com.thomas.voetbaladministratie {
    requires javafx.controls;
    requires java.sql;

    exports com.thomas.voetbaladministratie;
    exports com.thomas.voetbaladministratie.dao;
    exports com.thomas.voetbaladministratie.model;
    exports com.thomas.voetbaladministratie.util;
}
