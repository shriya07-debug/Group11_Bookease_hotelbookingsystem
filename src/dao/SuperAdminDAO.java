/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import model.SuperAdminModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SuperAdminDAO {
    MySqlConnection mysql = new MySqlConnection();
    
    // Create hotel admin (you already have this in UserDAO)
    public boolean createHotelAdmin(SuperAdminModel admin) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO users (email, password, role, hotel_id) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, admin.getEmail());
            pstm.setString(2, admin.getPassword());
            pstm.setString(3, admin.getRole());
            pstm.setInt(4, admin.getHotelId());
            
            int rows = pstm.executeUpdate();
            return rows > 0;
            
        } catch (Exception ex) {
            System.out.println("Error creating hotel admin: " + ex.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    // Get all hotel admins
    public List<SuperAdminModel> getAllHotelAdmins() {
        List<SuperAdminModel> admins = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users WHERE role = 'hotel_admin'";
        
        try (PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet result = pstm.executeQuery()) {
            
            while (result.next()) {
                SuperAdminModel admin = new SuperAdminModel();
                admin.setHotelId(result.getInt("hotel_id"));
                admin.setEmail(result.getString("email"));
                admin.setPassword(result.getString("password"));
                admin.setRole(result.getString("role"));
                admins.add(admin);
            }
            
        } catch (Exception ex) {
            System.out.println("Error getting hotel admins: " + ex.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return admins;
    }
    
    // Check if hotel exists
    public boolean hotelExists(int hotelId) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT COUNT(*) FROM hotels WHERE Hotel_id = ?";
        
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, hotelId);
            ResultSet result = pstm.executeQuery();
            
            if (result.next()) {
                return result.getInt(1) > 0;
            }
            
        } catch (Exception ex) {
            System.out.println("Error checking hotel: " + ex.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
}