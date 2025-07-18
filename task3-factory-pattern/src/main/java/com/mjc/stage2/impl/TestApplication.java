package com.mjc.stage2.impl;

import com.mjc.stage2.Employee;
import java.sql.Connection;

public class TestApplication {

    public static void main(String[] args) {
        H2TestSetup.initializeSchema();

//        Connection conn = H2ConnectionFactory.getInstance().createConnection();

//        Employee user = new Employee.EmployeeBuilder();
    }
}
