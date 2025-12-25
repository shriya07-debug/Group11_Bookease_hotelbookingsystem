/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;


import database.Database;
import database.mysqlconnection;
import java.sql.*;

public class ForgotPasswordDao {

    Database db = new mysqlconnection();

    // Check if email exists
    public boolean emailExists(String email) {

        Connection conn = db.openConnection();
        String query = "SELECT * FROM users WHERE email='" + email + "'";

        ResultSet rs = db.runQuery(conn, query);

        try {
            if (rs != null && rs.next()) {
                db.closeConnection(conn);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.closeConnection(conn);
        return false;
    }

    // Update password
    public boolean updatePassword(String email, String newPassword) {

        Connection conn = db.openConnection();
        String query =
            "UPDATE users SET password='" + newPassword + "' WHERE email='" + email + "'";

        int result = db.executeUpdate(conn, query);
        db.closeConnection(conn);

        return result > 0;
    }
}