package dao;

import database.MySqlConnection;
import model.SuperAdminModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuperAdminDAO {
    MySqlConnection mysql = new MySqlConnection();
    
    public boolean createHotelAdmin(SuperAdminModel admin) {
        Connection conn = mysql.openConnection();
        
        try {
            System.out.println("DEBUG: Creating hotel admin...");
            System.out.println("Hotel ID: " + admin.getHotelId());
            System.out.println("Email: " + admin.getEmail());
            System.out.println("Hotel Name: " + admin.getHotelName());
        
            String username = admin.getEmail().split("@")[0];
            System.out.println("Generated username: " + username);
            
           
            if (!hotelExists(admin.getHotelId())) {
                String hotelSql = "INSERT INTO hotels (Hotel_id, Hotel_name) VALUES (?, ?)";
                
                try (PreparedStatement hotelStmt = conn.prepareStatement(hotelSql)) {
                    hotelStmt.setInt(1, admin.getHotelId());
                    hotelStmt.setString(2, admin.getHotelName());
                    hotelStmt.executeUpdate();
                    System.out.println("DEBUG: Hotel created in hotels table");
                }
            }
            
            else {
                System.out.println("DEBUG: Hotel already exists, updating hotel name");
                String updateHotelSql = "UPDATE hotels SET Hotel_name = ? WHERE Hotel_id = ?";
                
                try (PreparedStatement updateStmt = conn.prepareStatement(updateHotelSql)) {
                    updateStmt.setString(1, admin.getHotelName());
                    updateStmt.setInt(2, admin.getHotelId());
                    updateStmt.executeUpdate();
                }
            }
            
       
            String userSql = "INSERT INTO users (username, email, password, role, hotel_id) VALUES (?, ?, ?, ?, ?)";
            
            try (PreparedStatement pstm = conn.prepareStatement(userSql)) {
                pstm.setString(1, username);              
                pstm.setString(2, admin.getEmail());      
                pstm.setString(3, admin.getPassword());   
                pstm.setString(4, admin.getRole());       
                pstm.setInt(5, admin.getHotelId());       
                
                int rows = pstm.executeUpdate();
                System.out.println("DEBUG: Rows affected in users table: " + rows);
                return rows > 0;
            }
        } 
        
        catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        
        finally {
            mysql.closeConnection(conn);
        }
    }
    
  
    public boolean emailExists(String email) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, email);
            ResultSet result = pstm.executeQuery();
            
            if (result.next()) {
                int count = result.getInt(1);
                System.out.println("DEBUG: Email '" + email + "' count: " + count);
                return count > 0;
            }
            
        } 
        
        catch (Exception ex) {
            System.out.println(ex);
        }
        
        finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    

    public boolean hotelExists(int hotelId) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT COUNT(*) FROM hotels WHERE Hotel_id = ?";
        
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, hotelId);
            ResultSet result = pstm.executeQuery();
            
            if (result.next()) {
                return result.getInt(1) > 0;
            }
            
        } 
        
        catch (Exception ex) {
            System.out.println(ex);
        }
        
        finally {
            mysql.closeConnection(conn);
        }
        return false;
    }

    
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
            
        }
        
        catch (Exception ex) {
            System.out.println(ex);
        } 
        
        finally {
            mysql.closeConnection(conn);
        }
        return admins;
    }
}