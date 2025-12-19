package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;

public class mysqlconnection implements DBConnection {


@Override
public Connection openConnection() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // IMPORTANT LINE

        String username = "root";
        String password = "1234";
        String database = "forgotpassword";
        Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/" + database,
            username,
            password
        );

        System.out.println("Connected Successfully!");
        return connection;

    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("Connection Error: " + e.getMessage());
        return null;
    }
}

    
    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection Closed");
            }
        } catch (SQLException e) {
            System.out.println("Close Error: " + e.getMessage());
        }
    }




    
    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try {
            Statement stmp = conn.createStatement();
            ResultSet result = stmp.executeQuery(query);
            return result;
        } catch (SQLException e) {
            System.out.println("Query Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
        try {
            Statement stmp = conn.createStatement();
            int result = stmp.executeUpdate(query);
            return result;
        } catch (SQLException e) {
            System.out.println("Update Error: " + e.getMessage());
            return -1;
        }
    }
}