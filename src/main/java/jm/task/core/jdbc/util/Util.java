package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static Util util;
    private static String logFile = "this is log file \n\n";

    public static synchronized Util getUtil() {
        if (util == null) {
           util = new Util();
        }
        return util;
    }

    private Util() {

    }

    public void addLogInfo(String logInfo) {
        logFile += logInfo + "\n";
    }

    public void showLogFile() {
        System.out.println(logFile);
    }

    // реализуйте настройку соеденения с БД
    private static String dbURL = "jdbc:mysql://localhost:3306/user";
    private static String dbUsername = "root";
    private static String dbPassword = "root";
    private static SessionFactory sessionFactory;
    private static Connection connection =null;




    public static Connection getConnection() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
            try {
                Configuration configuration = new Configuration();
                configuration.setProperty("connection.driver_class", "com.mysql.jdbc.Driver")
                        .setProperty("hibernate.connection.url", dbURL)
                        .setProperty("hibernate.connection.username",dbUsername)
                        .setProperty("hibernate.connection.password", dbPassword)
                        .setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect")
                        .setProperty("hibernate.show_sql","true")
                        .addAnnotatedClass(jm.task.core.jdbc.model.User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exception" + e);
            }
        return sessionFactory;
    }

    public static void shutdown() {
        if (connection != null) {
            try {
                getConnection().close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (sessionFactory != null) {
            getSessionFactory().close();
        }
    }
}
