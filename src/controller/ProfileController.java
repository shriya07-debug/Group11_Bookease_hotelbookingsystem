/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProfileDAO;
import dao.NotificationDAO;
import model.NotificationModel;
import model.ProfileModel;

public class ProfileController {
    private final ProfileDAO profileDAO;
    private final NotificationDAO notificationDAO;
    
    public ProfileController() {
        profileDAO = new ProfileDAO();
        notificationDAO = new NotificationDAO();
    }
    
    public ProfileModel getProfile(int userId) {
        return profileDAO.getProfileById(userId);
    }
    
    public boolean updateProfile(ProfileModel profile) {
        // Validate first
        if (!validateProfile(profile)) {
            return false;
        }
        
        // Save profile
        boolean success = profileDAO.updateProfile(profile);
        
        // Create notification if successful
        if (success) {
            String message = "Profile updated: " + profile.getFullName() + 
                           " | Email: " + profile.getEmail() + 
                           " | Phone: " + profile.getPhone();
            // Create NotificationModel object
        NotificationModel notification = new NotificationModel();
        notification.setUserId(profile.getUserId());
        notification.setMessage(message);
        
        // Call the existing method
        notificationDAO.createNotification(notification);
    }
    
    return success;
    }
    
    // Validation method
    public boolean validateProfile(ProfileModel profile) {
        if (profile.getFullName() == null || profile.getFullName().trim().isEmpty()) {
            return false;
        }
        if (profile.getEmail() == null || !profile.getEmail().contains("@")) {
            return false;
        }
        return !(profile.getPhone() == null || profile.getPhone().trim().isEmpty());
    }
}