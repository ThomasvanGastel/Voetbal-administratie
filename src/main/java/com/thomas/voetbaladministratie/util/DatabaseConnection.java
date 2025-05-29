package com.thomas.voetbaladministratie.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/voetbal";
    private static final String USER = "root";
    private static final String PASSWORD = "jouw_wachtwoord";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

