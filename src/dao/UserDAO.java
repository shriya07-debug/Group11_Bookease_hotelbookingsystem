package dao;
import model.UserModel;

import database.Database;
>>>>>>> kshitiznew
import database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final MySqlConnection mysql;
    
    public UserDAO() {
        this.mysql = new MySqlConnection();


    Database db = new MySqlConnection();

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
    
    public UserModel login(String email, String password) throws SQLException {
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


import database.Database;
import database.MySqlConnection;
import java.sql.*;

    public boolean createHotelAdmin(int hotelId, String email, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean emailExists(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean signup(UserModel newUser) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

public class UserDAO {

    Database db = new MySqlConnection();

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
            
        } 
        
        catch (SQLException e) {
            System.out.println(e);
        } 
        
        finally {
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
            
        }
        
        catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        
        finally {
            mysql.closeConnection(conn);
        }
    }
    
    public boolean createHotelAdmin(int hotelId, String email, String password) {
        Connection conn = mysql.openConnection();
        
  
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
            
        }
        
        catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        
        finally {
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
            
        } 
        
        catch (SQLException e) {
            System.out.println(e);
        } 
        
        finally {
            mysql.closeConnection(conn);
        }
        
        return admins;
    }

    
    return hotelAdmins;
}

// Also add this method if you want to get hotel name (if you have hotels table)
public String getHotelName(int hotelId) {
    Connection conn = mysql.openConnection();
    String sql = "SELECT hotel_name FROM hotels WHERE hotel_id = ?";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, hotelId);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return rs.getString("hotel_name");
        }
    } catch (SQLException e) {
        System.out.println("Error getting hotel name: " + e.getMessage());
    } finally {
        mysql.closeConnection(conn);
    }
    return "Hotel #" + hotelId;
 }
}}

}

