package dao;

import model.UserModel;
import database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final MySqlConnection mysql;
    
    public UserDAO() {
        this.mysql = new MySqlConnection();
    }
    
    public UserModel login(String email, String password) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                UserModel user = new UserModel();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
                user.setHotelId(rs.getInt("hotel_id"));
                return user;
            }
            
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }
    
    public boolean signup(UserModel user) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO users (username, email, password, role, status) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole());
            pstmt.setString(5, user.getStatus());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Signup error: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    public boolean createHotelAdmin(int hotelId, String email, String password) {
        Connection conn = mysql.openConnection();
        
        // Generate username from email
        String username = email.split("@")[0];
        
        String sql = "INSERT INTO users (username, email, password, role, hotel_id, status) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, "hotel_admin");
            pstmt.setInt(5, hotelId);
            pstmt.setString(6, "active");
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Create hotel admin error: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    public List<UserModel> getAllHotelAdmins() {
        List<UserModel> admins = new ArrayList<>();
        Connection conn = mysql.openConnection();
        
        String sql = "SELECT * FROM users WHERE role = 'hotel_admin'";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                UserModel admin = new UserModel();
                admin.setUserId(rs.getInt("user_id"));
                admin.setUsername(rs.getString("username"));
                admin.setEmail(rs.getString("email"));
                admin.setRole(rs.getString("role"));
                admin.setHotelId(rs.getInt("hotel_id"));
                admin.setStatus(rs.getString("status"));
                admins.add(admin);
            }
            
        } catch (SQLException e) {
            System.out.println("Get hotel admins error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        
        return admins;
    }
}