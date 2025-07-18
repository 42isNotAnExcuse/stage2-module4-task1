package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.sql.Connection;

public class H2ConnectionFactory implements ConnectionFactory {

    private static volatile H2ConnectionFactory instance;
    private final String driver;
    private final String url;
    private final String name;
    private final String password;

    private final CustomConnector connector = new CustomConnector();

    private H2ConnectionFactory(String driver, String url, String password, String name) {
        this.driver = driver;
        this.url = url;
        this.password = password;
        this.name = name;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database Driver not found", e);
        }
    }

    public static H2ConnectionFactory getInstance() {
        if (instance == null) {
            synchronized (H2ConnectionFactory.class) {
                if (instance == null) {
                    AppProperties properties = new AppProperties();
                    instance = new H2ConnectionFactory(
                            properties.getDriver(),
                            properties.getUrl(),
                            properties.getPassword(),
                            properties.getUserName()
                    );
                }
            }
        }
        return instance;
    }

    public Connection createConnection() {
        return connector.getConnection(url, name, password);
    }
}

