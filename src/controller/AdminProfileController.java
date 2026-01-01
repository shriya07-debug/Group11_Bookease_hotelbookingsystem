package controller;

import model.AdminProfileModel;
import dao.AdminProfileDao;
import javax.swing.JOptionPane;

public class AdminProfileController {
    private final AdminProfileDao adminDao;
    private final String currentHotelId;
    
    public AdminProfileController(String hotelId) {
        this.adminDao = new AdminProfileDao();
        this.currentHotelId = hotelId;
    }
    
    // Load admin data
    public AdminProfileModel loadAdminData() {
        return adminDao.getAdminById(currentHotelId);
    }
    
    // Update profile
    public boolean updateProfile(String fullName, String email, String phone) {
        // Validate
        if (fullName == null || fullName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Full Name is required!");
            return false;
        }
        
        if (email == null || !email.contains("@")) {
            JOptionPane.showMessageDialog(null, "Valid Email is required!");
            return false;
        }
        
        if (phone == null || phone.length() < 10) {
            JOptionPane.showMessageDialog(null, "Phone must be at least 10 digits!");
            return false;
        }
        
        // Check email
        if (adminDao.isEmailTaken(email, currentHotelId)) {
            JOptionPane.showMessageDialog(null, "Email already taken by another admin!");
            return false;
        }
        
        // Update
        AdminProfileModel admin = new AdminProfileModel(currentHotelId, fullName, email, phone);
        boolean updated = adminDao.updateAdmin(admin);
        
        if (updated) {
            JOptionPane.showMessageDialog(null, "Profile updated successfully!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Update failed!");
            return false;
        }
    }
    
    // Delete profile
    public boolean deleteProfile() {
        int confirm = JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to delete your profile?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean deleted = adminDao.deleteAdmin(currentHotelId);
            if (deleted) {
                JOptionPane.showMessageDialog(null, "Profile deleted successfully!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Delete failed!");
                return false;
            }
        }
        return false;
    }
    
    // Getters
    public String getCurrentHotelId() {
        return currentHotelId;
    }
}