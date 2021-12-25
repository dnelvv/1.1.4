package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String dbURL = "jdbc:mysql://localhost:3306/user";
    private static String dbUsername = "root";
    private static String dbPassword = "root";


    public static Connection getConnection() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
