/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.NotificationDAO;
import model.NotificationModel;
import java.util.List;

public class NotificationController {
    private NotificationDAO notificationDAO;
    
    public NotificationController() {
        notificationDAO = new NotificationDAO();
    }
    
    public List<NotificationModel> getUserNotifications(int userId) {
        return notificationDAO.getUserNotifications(userId);
    }
    
    public boolean createNotification(int userId, String message) {
        NotificationModel notification = new NotificationModel();
        notification.setUserId(userId);
        notification.setMessage(message);
        
        return notificationDAO.createNotification(notification);
    }
}