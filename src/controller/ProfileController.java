package controller;

import dao.ProfileDAO;
import dao.NotificationDAO;
import model.NotificationModel;
import model.ProfileModel;

public class ProfileController {
    private final ProfileDAO profileDAO;
    private NotificationDAO notificationDAO;
    
    public ProfileController() {
        profileDAO = new ProfileDAO();
        try {
            notificationDAO = new NotificationDAO();
        } catch (Exception e) {
            System.out.println("NotificationDAO initialization failed: " + e.getMessage());
            notificationDAO = null; // Set to null if fails
        }
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
        
        // Create notification if successful AND notificationDAO is available
        if (success && notificationDAO != null) {
            try {
                String message = "Profile updated: " + profile.getFullName() + 
                               " | Email: " + profile.getEmail() + 
                               " | Phone: " + profile.getPhone();
                NotificationModel notification = new NotificationModel();
                notification.setUserId(profile.getUserId());
                notification.setMessage(message);
                notificationDAO.createNotification(notification);
            } catch (Exception e) {
                System.out.println("Failed to create notification: " + e.getMessage());
                // Don't return false, profile update was successful
            }
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