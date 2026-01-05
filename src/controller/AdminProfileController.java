package controller;

import dao.AdminDAO;
import dao.AdminProfileDao;
import model.Admin;
import view.adminprofile;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.AdminProfileModel;

public class AdminProfileController {


    private final AdminProfileDao adminDao;
    private final String currentHotelId;


    private final adminprofile view;
    private final AdminDAO dao = new AdminDAO();
    private final int adminId = 1;


    private final AdminProfileDao adminDao;
    private final String currentHotelId;

    private final adminprofile view;
    private final AdminDAO dao = new AdminDAO();
    private final int adminId = 1;

    
    private boolean isEditing = false;
    private Admin originalAdmin; // Store original data for cancel

    public AdminProfileController(adminprofile view) {
        this.view = view;
        loadAdminData();
        addActions();
        setFieldsEditable(false); // Initially not editable
    }


    private void loadAdminData() {
        Admin admin = dao.getAdminById(adminId);
        if (admin != null) {
            this.originalAdmin = admin; // Store original
            view.getUserIdField().setText(String.valueOf(admin.getId()));
            view.getFullNameField().setText(admin.getFullName());
            view.getEmailField().setText(admin.getEmail());
            view.getPhoneField().setText(admin.getPhone());
        }
    }


    
  
    public AdminProfileModel loadAdminData() {
        return adminDao.getAdminById(currentHotelId);
    }
    
 
    public boolean updateProfile(String fullName, String email, String phone) {
  
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
        
  
        if (adminDao.isEmailTaken(email, currentHotelId)) {
            JOptionPane.showMessageDialog(null, "Email already taken by another admin!");
            return false;
        }
        
    
        AdminProfileModel admin = new AdminProfileModel(currentHotelId, fullName, email, phone);
        boolean updated = adminDao.updateAdmin(admin);
        
        if (updated) {
            JOptionPane.showMessageDialog(null, "Profile updated successfully!");
            return true;
        } 
        
        else {
            JOptionPane.showMessageDialog(null, "Update failed!");
            return false;
        }
    }
    
    
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


    private void loadAdminData() {
        Admin admin = dao.getAdminById(adminId);
        if (admin != null) {
            this.originalAdmin = admin; // Store original
            view.getUserIdField().setText(String.valueOf(admin.getId()));
            view.getFullNameField().setText(admin.getFullName());
            view.getEmailField().setText(admin.getEmail());
            view.getPhoneField().setText(admin.getPhone());
        }
    }


    private void addActions() {
        view.getLogoutButton().addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(view, 
                "Are you sure you want to logout?", 
                "Confirm Logout", 
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(view, "Logged out successfully!");
                view.dispose();
                // You can add code here to go back to login screen

            }
        });

        view.getCancelButton().addActionListener(e -> {
            if (isEditing) {
                // Restore original values
                cancelEdit();
                JOptionPane.showMessageDialog(view, "Changes cancelled");
            } else {
                JOptionPane.showMessageDialog(view, "No changes to cancel");
            }
        });

        view.getEditButton().addActionListener(e -> {
            if (!isEditing) {
                startEditing();
            } else {
                saveChanges();
            }
        });
        
        // Add action for back button
        view.getBackButtonLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (isEditing) {
                    int confirm = JOptionPane.showConfirmDialog(view,
                        "You have unsaved changes. Go back without saving?",
                        "Unsaved Changes",
                        JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        cancelEdit();
                        JOptionPane.showMessageDialog(view, "Returning to previous screen");
                        view.dispose();
                        // Add code to go back to previous screen
                    }
                } else {
                    view.dispose();
                    // Add code to go back to previous screen
                }
            }
        });
    }
    



    public String getCurrentHotelId() {
        return currentHotelId;


    public String getCurrentHotelId() {
        return currentHotelId;
    }
    private void startEditing() {
        isEditing = true;
        setFieldsEditable(true);
        view.getEditButton().setText("Save");
        view.getEditButton().setBackground(new java.awt.Color(0, 150, 0)); // Green for save
        view.getCancelButton().setEnabled(true);
        JOptionPane.showMessageDialog(view, "You can now edit your profile. Click Save when done.");
    }
    
    private void saveChanges() {
        // Validate inputs
        if (!validateInputs()) {
            return;
        }
        
        try {
            // Create updated admin object
            Admin updatedAdmin = new Admin();
            updatedAdmin.setId(adminId);
            updatedAdmin.setFullName(view.getFullNameField().getText().trim());
            updatedAdmin.setEmail(view.getEmailField().getText().trim());
            updatedAdmin.setPhone(view.getPhoneField().getText().trim());
            
            // Update in database
            boolean success = dao.updateAdmin(updatedAdmin);
            
            if (success) {
                JOptionPane.showMessageDialog(view, "Profile updated successfully!");
                isEditing = false;
                setFieldsEditable(false);
                view.getEditButton().setText("Edit");
                view.getEditButton().setBackground(new java.awt.Color(184, 12, 47)); // Original color
                originalAdmin = updatedAdmin; // Update original data
            } else {
                JOptionPane.showMessageDialog(view, "Failed to update profile. Please try again.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
        }
    }
    
    private void cancelEdit() {
        // Restore original values
        if (originalAdmin != null) {
            view.getFullNameField().setText(originalAdmin.getFullName());
            view.getEmailField().setText(originalAdmin.getEmail());
            view.getPhoneField().setText(originalAdmin.getPhone());
        }
        
        isEditing = false;
        setFieldsEditable(false);
        view.getEditButton().setText("Edit");
        view.getEditButton().setBackground(new java.awt.Color(184, 12, 47));
    }
    
    private void setFieldsEditable(boolean editable) {
        view.getFullNameField().setEditable(editable);
        view.getEmailField().setEditable(editable);
        view.getPhoneField().setEditable(editable);
        
        // Visual feedback for editable state
        if (editable) {
            view.getFullNameField().setBackground(new java.awt.Color(255, 255, 200)); // Light yellow
            view.getEmailField().setBackground(new java.awt.Color(255, 255, 200));
            view.getPhoneField().setBackground(new java.awt.Color(255, 255, 200));
        } else {
            view.getFullNameField().setBackground(new java.awt.Color(255, 255, 255)); // White
            view.getEmailField().setBackground(new java.awt.Color(255, 255, 255));
            view.getPhoneField().setBackground(new java.awt.Color(255, 255, 255));
        }
    }
    
    private boolean validateInputs() {
        String fullName = view.getFullNameField().getText().trim();
        String email = view.getEmailField().getText().trim();
        String phone = view.getPhoneField().getText().trim();
        
        // Check for empty fields
        if (fullName.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Full name cannot be empty!");
            view.getFullNameField().requestFocus();
            return false;
        }
        
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Email cannot be empty!");
            view.getEmailField().requestFocus();
            return false;
        }
        
        // Basic email validation
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(view, "Please enter a valid email address!");
            view.getEmailField().requestFocus();
            return false;
        }
        
        // Phone validation (optional, basic check)
        if (!phone.isEmpty() && !phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(view, "Phone number should be 10 digits!");
            view.getPhoneField().requestFocus();
            return false;
        }
        
        return true;

    }
}