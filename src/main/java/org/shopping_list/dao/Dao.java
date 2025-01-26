package org.shopping_list.dao;

import org.shopping_list.api.IConfigService;
import org.shopping_list.service.ConfigService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Dao {
    protected final IConfigService configService;
    Dao() {
        this.configService = new ConfigService();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                configService.getProperty("database.url"),
                configService.getProperty("database.user"),
                configService.getProperty("database.password")
        );
    }


}
