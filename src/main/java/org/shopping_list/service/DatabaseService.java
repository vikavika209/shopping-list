package org.shopping_list.service;

import org.shopping_list.api.IConfigService;
import org.shopping_list.api.IDatabaseService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService implements IDatabaseService {
    private final IConfigService configService;

    public DatabaseService() {
        this.configService = new ConfigService();
    }

    @Override
    public void initializeDatabase() {
        String initializationQueries = "CREATE TABLE IF NOT EXISTS shopping_list (" +
                "id SERIAL PRIMARY KEY," +
                "item_name VARCHAR(50) NOT NULL," +
                "quantity BIGINT  NOT NULL," +
                "price BIGINT  NOT NULL" +
                ");";

            String url = configService.getProperty("database.url");
            String user = configService.getProperty("database.user");
            String password = configService.getProperty("database.password");

            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            try (Connection connection = DriverManager.getConnection(url, user, password);
                 Statement stmt = connection.createStatement()) {
                stmt.execute(initializationQueries);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }
}
