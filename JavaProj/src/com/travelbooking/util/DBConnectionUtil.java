package com.travelbooking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
//    private static final String URL = "jdbc:mysql://localhost:3306/travelbooking";
//    private static final String USER = "root";
//    private static final String PASSWORD = "test";
    private static final String URL = "jdbc:mysql://localhost:3306/travel_booking_system";
    private static final String USER = "root";
    private static final String PASSWORD = "@Aniket23";
    public static Connection getConnection() {
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
