package db.utils;

import aquality.selenium.core.utilities.JsonSettingsFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static JsonSettingsFile configs = new JsonSettingsFile("DBconfigs.json");

    private static final String dataBaseUrl = "jdbc:mysql://localhost:3306/";

    private static Connection conn;

    public DataBaseConnection() {
        try {
            conn = DriverManager.getConnection(dataBaseUrl + configs.getValue("/db_name").toString(),
                    configs.getValue("/user_name").toString(), configs.getValue("/password").toString());
            if(conn != null) {
                System.out.println("Connected to the database");
            } else {
                System.out.println("Failed to make connection");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if(conn == null) {
            new DataBaseConnection();
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
