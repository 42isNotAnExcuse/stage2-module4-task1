package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.sql.Connection;

public class H2ConnectionFactory implements ConnectionFactory {

    private static volatile H2ConnectionFactory instance;
    private final String driver;
    private final String url;
    private final String name;
    private final String password;


    AppProperties properties = new AppProperties();
    private final CustomConnector connector = new CustomConnector();

    private H2ConnectionFactory() {
        this.driver = properties.getDriver();
        this.url = properties.getUrl();
        this.password = properties.getPassword();
        this.name = properties.getUserName();

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
                    instance = new H2ConnectionFactory();
                }
            }
        }
        return instance;
    }

    public Connection createConnection() {
        return connector.getConnection(url, name, password);
    }
}

