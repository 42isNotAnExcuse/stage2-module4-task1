package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.sql.Connection;

public class H2ConnectionFactory implements ConnectionFactory {
    private final String driver;
    private final String url;
    private final String name;
    private final String password;

    private final CustomConnector connector;

    public H2ConnectionFactory() {
        AppProperties properties = new AppProperties();
        this.driver = properties.getDriver();
        this.url = properties.getUrl();
        this.password = properties.getPassword();
        this.name = properties.getUserName();
        this.connector = new CustomConnector();

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database Driver not found", e);
        }
    }

    @Override
    public Connection createConnection() {
        return connector.getConnection(url, name, password);
    }
}
