/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import model.NotificationModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {
    MySqlConnection mysql = new MySqlConnection();
    
    // Get all notifications for a user
    public List<NotificationModel> getUserNotifications(int userId) {
        List<NotificationModel> notifications = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM notifications WHERE user_id = ? ORDER BY created_at DESC";
        
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            ResultSet result = pstm.executeQuery();
            
            while (result.next()) {
                NotificationModel notification = new NotificationModel();
                notification.setNotificationId(result.getInt("notification_id"));
                notification.setUserId(result.getInt("user_id"));
                notification.setMessage(result.getString("message"));
                notification.setCreatedAt(result.getTimestamp("created_at"));
                
                notifications.add(notification);
            }
        } catch (Exception ex) {
            System.out.println("Error in getUserNotifications: " + ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return notifications;
    }
    
    // Create a new notification
    public boolean createNotification(NotificationModel notification) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO notifications (user_id, message) VALUES (?, ?)";
        
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, notification.getUserId());
            pstm.setString(2, notification.getMessage());
            
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
            
        } catch (Exception ex) {
            System.out.println("Error in createNotification: " + ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
}