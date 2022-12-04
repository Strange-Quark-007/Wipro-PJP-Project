package com.store.sales.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    Connection conn = null;

    public static Connection getDBConnection() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory_sales_mgmt","root","MySQL");
            return conn;
        } catch (SQLException e) {
            System.out.println("Connection could not be established");
            e.printStackTrace();
            return null;
        }
    }
}
