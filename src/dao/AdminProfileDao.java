package dao;

import database.mysqlconnection;
import model.AdminProfileModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminProfileDao {
    
    // Get admin by ID
    public AdminProfileModel getAdminById(String hotelId) {
        String sql = "SELECT * FROM admin WHERE hotel_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = mysqlconnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection is null!");
                return null;
            }
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, hotelId);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return new AdminProfileModel(
                    rs.getString("hotel_id"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            System.err.println("Get Admin Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources
            closeResources(conn, ps, rs);
        }
        return null;
    }
    
    // Update admin
    public boolean updateAdmin(AdminProfileModel admin) {
        String sql = "UPDATE admin SET full_name = ?, email = ?, phone = ? WHERE hotel_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = mysqlconnection.getConnection();
            if (conn == null) {
                System.out.println("❌ Database connection is null!");
                return false;
            }
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, admin.getFullName());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getPhone());
            ps.setString(4, admin.getHotelId());
            
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
            
        } catch (SQLException e) {
            System.err.println("Update Error: " + e.getMessage());
            return false;
        } finally {
            closeResources(conn, ps, null);
        }
    }
    
    // Delete admin
    public boolean deleteAdmin(String hotelId) {
        String sql = "DELETE FROM admin WHERE hotel_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = mysqlconnection.getConnection();
            if (conn == null) {
                System.out.println("❌ Database connection is null!");
                return false;
            }
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, hotelId);
            
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
            
        } catch (SQLException e) {
            System.err.println("Delete Error: " + e.getMessage());
            return false;
        } finally {
            closeResources(conn, ps, null);
        }
    }
    
    // Check if email exists for other admin
    public boolean isEmailTaken(String email, String currentHotelId) {
        String sql = "SELECT COUNT(*) as count FROM admin WHERE email = ? AND hotel_id != ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = mysqlconnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection is null!");
                return false;
            }
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, currentHotelId);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        } catch (SQLException e) {
            System.err.println("Email Check Error: " + e.getMessage());
        } finally {
            closeResources(conn, ps, rs);
        }
        return false;
    }
    
    // Get all admins (optional)
    public List<AdminProfileModel> getAllAdmins() {
        List<AdminProfileModel> admins = new ArrayList<>();
        String sql = "SELECT * FROM admin";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = mysqlconnection.getConnection();
            if (conn == null) {
                System.out.println("Database connection is null!");
                return admins;
            }
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                admins.add(new AdminProfileModel(
                    rs.getString("hotel_id"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("phone")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Get All Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        return admins;
    }
    
    // Helper method to close resources
    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }
}