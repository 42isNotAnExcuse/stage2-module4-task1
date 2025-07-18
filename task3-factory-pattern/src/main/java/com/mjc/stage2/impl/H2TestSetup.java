package com.mjc.stage2.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class H2TestSetup {

    public static void initializeSchema() {
        String createTableSQL = """
                    CREATE TABLE IF NOT EXISTS myusers (
                        id IDENTITY PRIMARY KEY,
                        firstname VARCHAR(255),
                        lastname VARCHAR(255),
                        age INT
                    )
                """;

        try (Connection conn = H2ConnectionFactory.getInstance().createConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createTableSQL);
            System.out.println("Table 'myusers' created successfully.");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize schema", e);
        }
    }
}

