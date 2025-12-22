/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class NotificationModel {
    private int notificationId;
    private int userId;
    private String message;
    private Date createdAt;
    
    // Constructors
    public NotificationModel() {}
    
    public NotificationModel(int notificationId, int userId, String message, Date createdAt) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public int getNotificationId() { 
        return notificationId; 
    }
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId; 
    }
    
    public int getUserId() { 
        return userId; 
    }
    public void setUserId(int userId) {
        this.userId = userId; 
    }
    
    public String getMessage() {
        return message; 
    }
    public void setMessage(String message) {
        this.message = message; 
    }
    
    public Date getCreatedAt() {
        return createdAt; 
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt; 
    }
}