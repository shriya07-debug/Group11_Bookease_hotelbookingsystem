package controller;

import dao.ProfileDAO;
import model.ProfileModel;
import view.profile;
import view.userdashboard;
import javax.swing.*;

public class ProfileController {
    private ProfileDAO profileDAO;
    private int currentUserId;
    private profile profileWindow;
    
    public ProfileController() {
        this.profileDAO = new ProfileDAO();
    }
    
    public void setupProfile(profile window, int userId) {
        this.profileWindow = window;
        this.currentUserId = userId;
        
        loadProfileData();
        setupButtonActions();
    }
    
    private void loadProfileData() {
        try {
            ProfileModel userProfile = profileDAO.getProfileById(currentUserId);
            
            if (userProfile != null) {
                profileWindow.getUserIdField().setText(String.valueOf(userProfile.getUserId()));
                profileWindow.getUserIdField().setEditable(false);
                profileWindow.getFullNameField().setText(userProfile.getFullName());
                profileWindow.getEmailField().setText(userProfile.getEmail());
                profileWindow.getPhoneField().setText(userProfile.getPhone());
                
                if (userProfile.getPhotoPath() != null && !userProfile.getPhotoPath().isEmpty()) {
                    profileWindow.getPhotoLabel().setIcon(new ImageIcon(userProfile.getPhotoPath()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void setupButtonActions() {
        // Back button - go to dashboard
        profileWindow.getBackButtonLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileWindow.dispose();
                new userdashboard().setVisible(true);
            }
        });
        
        // Edit button - save profile
        profileWindow.getEditButton().addActionListener(e -> saveProfile());
        
        // Cancel button - go to dashboard
        profileWindow.getCancelButton().addActionListener(e -> {
            profileWindow.dispose();
            new userdashboard().setVisible(true);
        });
        
        // Logout button - logout
        profileWindow.getLogoutButton().addActionListener(e -> logout());
        
        // Upload photo button
        profileWindow.getUploadButton().addActionListener(e -> uploadPhoto());
        
        // Remove photo button
        profileWindow.getRemoveButton().addActionListener(e -> removePhoto());
    }
    
    private void saveProfile() {
        String fullName = profileWindow.getFullNameField().getText().trim();
        String email = profileWindow.getEmailField().getText().trim();
        String phone = profileWindow.getPhoneField().getText().trim();
        
        if (fullName.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(profileWindow, "Name and Email are required!");
            return;
        }
        
        boolean success = profileDAO.updateProfile(
            new ProfileModel(currentUserId, fullName, email, phone, null)
        );
        
        if (success) {
            JOptionPane.showMessageDialog(profileWindow, "Profile updated!");
        } else {
            JOptionPane.showMessageDialog(profileWindow, "Update failed!");
        }
    }
    
    // ONLY THIS METHOD IS CHANGED - logout navigation
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(profileWindow, 
            "Logout?", "Confirm", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            profileWindow.dispose();
            
            // Navigate to logout page
            try {
                // Check if LogoutController exists
                Class.forName("controller.LogoutController");
                // Call LogoutController
                controller.LogoutController.showLogoutWindow();
            } catch (ClassNotFoundException e) {
                // If LogoutController doesn't exist, open logout view directly
                new view.logout().setVisible(true);
            }
        }
    }
    
    private void uploadPhoto() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Images", "jpg", "jpeg", "png", "gif"));
        
        if (chooser.showOpenDialog(profileWindow) == JFileChooser.APPROVE_OPTION) {
            String photoPath = chooser.getSelectedFile().getAbsolutePath();
            
            ProfileModel profile = new ProfileModel(
                currentUserId,
                profileWindow.getFullNameField().getText(),
                profileWindow.getEmailField().getText(),
                profileWindow.getPhoneField().getText(),
                photoPath
            );
            
            if (profileDAO.updateProfileWithPhoto(profile)) {
                profileWindow.getPhotoLabel().setIcon(new ImageIcon(photoPath));
                JOptionPane.showMessageDialog(profileWindow, "Photo uploaded!");
            }
        }
    }
    
    private void removePhoto() {
        int confirm = JOptionPane.showConfirmDialog(profileWindow, 
            "Remove photo?", "Confirm", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            ProfileModel profile = new ProfileModel(
                currentUserId,
                profileWindow.getFullNameField().getText(),
                profileWindow.getEmailField().getText(),
                profileWindow.getPhoneField().getText(),
                null
            );
            
            if (profileDAO.updateProfileWithPhoto(profile)) {
                profileWindow.getPhotoLabel().setIcon(null);
                JOptionPane.showMessageDialog(profileWindow, "Photo removed!");
            }
        }
    }
}