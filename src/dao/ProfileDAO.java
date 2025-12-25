/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import model.ProfileModel;
import java.sql.*;

public class ProfileDAO {
    MySqlConnection mysql = new MySqlConnection();
 public ProfileModel getProfileById(int userId) {
    Connection conn = mysql.openConnection();
    String sql = "SELECT * FROM profiles WHERE user_id = ?";
    
    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
        pstm.setInt(1, userId);
        ResultSet result = pstm.executeQuery();
        
        if (result.next()) {
            ProfileModel profile = new ProfileModel();
            profile.setUserId(result.getInt("user_id"));
            profile.setFullName(result.getString("full_name"));
            profile.setEmail(result.getString("email")); // Now this should have value
            profile.setPhone(result.getString("phone"));
            profile.setPhotoPath(result.getString("photo_path"));
            return profile;
        } else {
            // If no profile exists, check if user exists and create a basic profile
            // This handles cases where user exists but profile wasn't created
            String userSql = "SELECT username, email FROM users WHERE user_id = ?";
            try (PreparedStatement userStmt = conn.prepareStatement(userSql)) {
                userStmt.setInt(1, userId);
                ResultSet userResult = userStmt.executeQuery();
                
                if (userResult.next()) {
                    // Create a basic profile object with user data
                    ProfileModel profile = new ProfileModel();
                    profile.setUserId(userId);
                    profile.setFullName(userResult.getString("username"));
                    profile.setEmail(userResult.getString("email"));
                    profile.setPhone(""); // Empty phone initially
                    profile.setPhotoPath(null);
                    return profile;
                }
            }
        }
    } catch (Exception ex) {
        System.out.println("Error in getProfileById: " + ex);
    } finally {
        mysql.closeConnection(conn);
    }
    return null;
    
}
    
    public boolean updateProfile(ProfileModel profile) {
        Connection conn = mysql.openConnection();
        String sql = "UPDATE profiles SET full_name = ?, email = ?, phone = ? WHERE user_id = ?";
        
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, profile.getFullName());
            pstm.setString(2, profile.getEmail());
            pstm.setString(3, profile.getPhone());
            pstm.setInt(4, profile.getUserId());
            
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
            
        } catch (Exception ex) {
            System.out.println("Error in updateProfile: " + ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    public boolean updateProfileWithPhoto(ProfileModel profile) {
    Connection conn = mysql.openConnection();
    String sql = "UPDATE profiles SET full_name = ?, email = ?, phone = ?, photo_path = ? WHERE user_id = ?";
    
    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
        pstm.setString(1, profile.getFullName());
        pstm.setString(2, profile.getEmail());
        pstm.setString(3, profile.getPhone());
        pstm.setString(4, profile.getPhotoPath());
        pstm.setInt(5, profile.getUserId());
        
        return pstm.executeUpdate() > 0;
    } catch (Exception ex) {
        System.out.println("Error: " + ex);
        return false;
    } finally {
        mysql.closeConnection(conn);
    }
  }
}