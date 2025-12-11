package org.daw.examenlimpio.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {
    private static String url = "jdbc:mysql://db:3306/blackjack";
    private static String user = "root";
    private static String password = "root";

    static {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex) {

            throw new RuntimeException("No mysql jdbc driver found");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user,password);
    }
}
