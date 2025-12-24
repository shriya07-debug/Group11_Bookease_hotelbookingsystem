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
        
        try (Connection conn = mysqlconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, hotelId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return new AdminProfileModel(
                    rs.getString("hotel_id"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("phone")
                );
            }
        } catch (Exception e) {
            System.out.println("Get Admin Error: " + e.getMessage());
        }
        return null;
    }
    
    // Update admin
    public boolean updateAdmin(AdminProfileModel admin) {
        String sql = "UPDATE admin SET full_name=?, email=?, phone=? WHERE hotel_id=?";
        
        try (Connection conn = mysqlconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, admin.getFullName());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getPhone());
            ps.setString(4, admin.getHotelId());
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Update Error: " + e.getMessage());
            return false;
        }
    }
    
    // Delete admin
    public boolean deleteAdmin(String hotelId) {
        String sql = "DELETE FROM admin WHERE hotel_id=?";
        
        try (Connection conn = mysqlconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, hotelId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Delete Error: " + e.getMessage());
            return false;
        }
    }
    
    // Check if email exists for other admin
    public boolean isEmailTaken(String email, String currentHotelId) {
        String sql = "SELECT COUNT(*) FROM admin WHERE email=? AND hotel_id!=?";
        
        try (Connection conn = mysqlconnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ps.setString(2, currentHotelId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            System.out.println("Email Check Error: " + e.getMessage());
        }
        return false;
    }
    
    // Get all admins (optional)
    public List<AdminProfileModel> getAllAdmins() {
        List<AdminProfileModel> admins = new ArrayList<>();
        String sql = "SELECT * FROM admin";
        
        try (Connection conn = mysqlconnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                admins.add(new AdminProfileModel(
                    rs.getString("hotel_id"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("phone")
                ));
            }
        } catch (Exception e) {
            System.out.println("Get All Error: " + e.getMessage());
        }
        return admins;
    }
}