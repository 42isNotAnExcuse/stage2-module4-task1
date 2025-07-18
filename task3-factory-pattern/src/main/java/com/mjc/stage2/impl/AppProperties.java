package com.mjc.stage2.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {
    private final Properties properties = new Properties();

    public AppProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("h2database.properties")) {
            if (input == null) {
                throw new RuntimeException("Cannot find app.properties in resources folder");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load h2database.properties", e);
        }
    }

    public String getDriver() {
        return properties.getProperty("jdbc_driver");
    }

    public String getUrl() {
        return properties.getProperty("db_url");
    }

    public String getUserName() {
        return properties.getProperty("user");     // Assuming typo in 'postgres.name' earlier
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
}
