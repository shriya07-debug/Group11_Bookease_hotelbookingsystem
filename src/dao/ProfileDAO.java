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
                profile.setEmail(result.getString("email"));
                profile.setPhone(result.getString("phone"));
                return profile;
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
}