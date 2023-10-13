package by.book_aston.task2.db;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public class AbstractTestContainer {
    public static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:14-alpine")
                    .withInitScript("schema.sql");

    static {
        container.start();
    }


    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("hibernate.connection.url", container::getJdbcUrl);
        registry.add("hibernate.connection.username", container::getUsername);
        registry.add("hibernate.connection.passwrod", container::getPassword);
    }
}
