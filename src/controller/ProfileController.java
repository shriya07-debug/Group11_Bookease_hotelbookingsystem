package controller;

import dao.ProfileDAO;
import model.ProfileModel;
import view.profile;
import javax.swing.*;
import java.awt.Image;
import java.io.File;

public class ProfileController {
    private profile view;
    private int userId;
    private String currentPhotoPath;
    
    public ProfileController(profile view, int userId) {
        this.view = view;
        this.userId = userId;
        
        // Show window
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        
        // Load data
        loadProfileData();
        
        // Setup all button listeners
        setupButtonListeners();
    }
    
    private void loadProfileData() {
        ProfileDAO dao = new ProfileDAO();
        ProfileModel user = dao.getProfileById(userId);
        
        if (user != null) {
            view.getUserIdLabel().setText("User ID: " + user.getUserId());
            view.getFullNameLabel().setText("Full Name: " + user.getFullName());
            view.getEmailLabel().setText("Email: " + user.getEmail());
            view.getPhoneLabel().setText("Phone: " + user.getPhone());
            
            // Load photo
            loadProfilePhoto();
        }
    }
    
    private void loadProfilePhoto() {
        try {
            String photoPath = "src/images/user_" + userId + ".jpg";
            File photoFile = new File(photoPath);
            
            if (photoFile.exists()) {
                ImageIcon icon = new ImageIcon(photoPath);
                Image scaled = icon.getImage().getScaledInstance(140, 170, Image.SCALE_SMOOTH);
                view.getPhotoLabel().setIcon(new ImageIcon(scaled));
                currentPhotoPath = photoPath;
            }
        } catch (Exception e) {
            // Keep default
        }
    }
    
    private void setupButtonListeners() {
        // Edit button
        view.getEditButton().addActionListener(e -> handleEdit());
        
        // Cancel button
        view.getCancelButton().addActionListener(e -> handleCancel());
        
        // Logout button
        view.getLogoutButton().addActionListener(e -> handleLogout());
        
        // Upload button
        view.getUploadButton().addActionListener(e -> handleUpload());
        
        // Remove button
        view.getRemoveButton().addActionListener(e -> handleRemove());
        
        // Back button
        view.getBackButtonLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleBack();
            }
        });
    }
    
    private void handleEdit() {
        String newName = JOptionPane.showInputDialog(view, "Enter new name:");
        if (newName != null && !newName.trim().isEmpty()) {
            // Update database
            ProfileDAO dao = new ProfileDAO();
            ProfileModel user = new ProfileModel();
            user.setUserId(userId);
            user.setFullName(newName);
            user.setEmail(view.getEmailLabel().getText().replace("Email: ", ""));
            user.setPhone(view.getPhoneLabel().getText().replace("Phone: ", ""));
            
            dao.updateProfile(user);
            
            // Show message
            JOptionPane.showMessageDialog(view, "You changed your profile details");
            
            // Update UI
            view.getFullNameLabel().setText("Full Name: " + newName);
        }
    }
    
    private void handleCancel() {
        view.dispose();
        new view.userdashboard().setVisible(true);
    }
    
    private void handleLogout() {
        view.dispose();
        new view.logout().setVisible(true);
    }
    
    private void handleUpload() {
        JFileChooser fileChooser = new JFileChooser();
        
        if (fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            try {
                String newFileName = "user_" + userId + ".jpg";
                File destination = new File("src/images", newFileName);
                
                // Copy file
                java.io.FileInputStream fis = new java.io.FileInputStream(selectedFile);
                java.io.FileOutputStream fos = new java.io.FileOutputStream(destination);
                
                byte[] buffer = new byte[1024];
                int bytesRead;
                
                while ((bytesRead = fis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                
                fis.close();
                fos.close();
                
                // Display photo
                ImageIcon icon = new ImageIcon(destination.getAbsolutePath());
                Image scaled = icon.getImage().getScaledInstance(140, 170, Image.SCALE_SMOOTH);
                view.getPhotoLabel().setIcon(new ImageIcon(scaled));
                
                currentPhotoPath = destination.getAbsolutePath();
                JOptionPane.showMessageDialog(view, "Photo uploaded!");
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, "Error uploading photo");
            }
        }
    }
    
    private void handleRemove() {
        if (currentPhotoPath != null) {
            try {
                File photoFile = new File(currentPhotoPath);
                if (photoFile.exists()) {
                    photoFile.delete();
                }
                
                // Remove photo from UI
                view.getPhotoLabel().setIcon(null);
                currentPhotoPath = null;
                JOptionPane.showMessageDialog(view, "Photo removed!");
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, "Error removing photo");
            }
        }
    }
    
    private void handleBack() {
        view.dispose();
        new view.userdashboard().setVisible(true);
    }
}