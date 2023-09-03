package by.book_aston.task2.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class Config {

    public static final String DB_URL = "db.url";
    public static final String DB_LOGIN = "db.login";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_DRIVER = "db.driver";
    private static Properties properties = new Properties();

    public static String getProperty(String name) {
        if (properties.isEmpty()) {
            try (InputStream stream = Config.class.getClassLoader()
                    .getResourceAsStream("db.properties")) {
                properties.load(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties.getProperty(name);
    }
}
