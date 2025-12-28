/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import model.AdminDashboardModel;
import java.sql.*;

public class AdminDashboardDAO {
    
    // Get data from database
    public AdminDashboardModel getDashboardData() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = new MySqlConnection().openConnection();

            String sql = "SELECT image, text FROM admindashboard LIMIT 1";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                String image = rs.getString("image");
                String text = rs.getString("text");
                return new AdminDashboardModel(image, text);
            }
            
        } catch (SQLException e) {
        } finally {
            // Close connections
            try { if (rs != null) rs.close(); } catch (SQLException e) {}
            try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
        
        return null; // No data found
    }
    
    // Update database
    public boolean updateDashboard(String image, String text) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = new MySqlConnection().openConnection();
            
            // Check if any data exists
            String checkSql = "SELECT COUNT(*) FROM admindashboard";
            Statement checkStmt = conn.createStatement();
            ResultSet rs = checkStmt.executeQuery(checkSql);
            rs.next();
            int count = rs.getInt(1);
            
            if (count == 0) {
                // Insert
                String sql = "INSERT INTO admindashboard (image, text) VALUES (?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, image);
                pstmt.setString(2, text);
            } else {
                // Update
                String sql = "UPDATE admindashboard SET image = ?, text = ? LIMIT 1";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, image);
                pstmt.setString(2, text);
            }
            
            int result = pstmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            return false;
        } finally {
            // Close connections
            try { if (pstmt != null) pstmt.close(); } catch (SQLException e) {}
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
    }
}