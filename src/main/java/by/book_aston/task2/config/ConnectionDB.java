package by.book_aston.task2.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static Connection getConnection(){
        try {
            Class.forName(Config.getProperty(Config.DB_DRIVER));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            return  DriverManager.getConnection(
                    Config.getProperty(Config.DB_URL),
                    Config.getProperty(Config.DB_LOGIN),
                    Config.getProperty(Config.DB_PASSWORD));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
