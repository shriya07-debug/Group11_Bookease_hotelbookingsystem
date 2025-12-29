/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SuperAdminDAO;
import model.SuperAdminModel;
import view.superadmindashboard;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SuperAdminController {
    private final SuperAdminDAO superAdminDAO;
    
    public SuperAdminController() {
        this.superAdminDAO = new SuperAdminDAO();
    }
    
    public void setupSuperAdminDashboard(superadmindashboard dashboard) {
        setupMenuBar(dashboard);
        setupNavigation(dashboard);
        setupSaveButton(dashboard);
    }
    
    private void setupMenuBar(superadmindashboard dashboard) {
        dashboard.getMenuIcon().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                toggleDrawer(dashboard.getDrawerPanel());
            }
        });
    }
    
    private void toggleDrawer(javax.swing.JPanel drawerPanel) {
        if (drawerPanel.getX() < 0) {
            drawerPanel.setBounds(0, 0, 300, 720);
        } else {
            drawerPanel.setBounds(-300, 0, 300, 720);
        }
    }
    
    private void setupNavigation(superadmindashboard dashboard) {
        // Dashboard (refresh)
        dashboard.getDashboardLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                refreshDashboard(dashboard);
            }
        });
        
        // View all hotel admins
        dashboard.getAllHotelAdminsLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                navigateToAllHotelAdmins(dashboard);
            }
        });
        
        // Logout
        dashboard.getLogoutLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                logout(dashboard);
            }
        });
    }
    
    private void setupSaveButton(superadmindashboard dashboard) {
        dashboard.addSaveButtonListener(e -> {
            createHotelAdmin(dashboard);
        });
    }
    
    private void createHotelAdmin(superadmindashboard dashboard) {
        // Get values from form
        String hotelIdStr = dashboard.getHotelIdField().getText().trim();
        String email = dashboard.getEmailField().getText().trim();
        String password = new String(dashboard.getPasswordField().getPassword()).trim();
        
        // Validate
        if (hotelIdStr.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(dashboard, 
                "All fields are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(dashboard, 
                "Invalid email address!", "Invalid Email", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (password.length() < 4) {
            JOptionPane.showMessageDialog(dashboard, 
                "Password must be at least 4 characters!", "Weak Password", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            int hotelId = Integer.parseInt(hotelIdStr);
            
            // Check if hotel exists
            if (!superAdminDAO.hotelExists(hotelId)) {
                JOptionPane.showMessageDialog(dashboard, 
                    "Hotel ID " + hotelId + " does not exist!", "Invalid Hotel", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Create model
            SuperAdminModel admin = new SuperAdminModel(hotelId, email, password);
            
            // Save to database
            boolean success = superAdminDAO.createHotelAdmin(admin);
            
            if (success) {
                JOptionPane.showMessageDialog(dashboard, 
                    "Hotel Admin Created Successfully!\n\n" +
                    "Hotel ID: " + hotelId + "\n" +
                    "Email: " + email, "Success", JOptionPane.INFORMATION_MESSAGE);
                
                // Clear fields
                dashboard.getHotelIdField().setText("");
                dashboard.getEmailField().setText("");
                dashboard.getPasswordField().setText("");
                dashboard.getHotelIdField().requestFocus();
                
            } else {
                JOptionPane.showMessageDialog(dashboard, 
                    "Failed to create hotel admin!\nEmail might already exist.", 
                    "Creation Failed", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(dashboard, 
                "Hotel ID must be a number!", "Invalid Hotel ID", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(dashboard, 
                "Error: " + e.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void refreshDashboard(superadmindashboard dashboard) {
        dashboard.dispose();
        new view.superadmindashboard().setVisible(true);
    }
    
    private void navigateToAllHotelAdmins(superadmindashboard dashboard) {
        dashboard.dispose();
        new view.viewallhoteladmins().setVisible(true);
    }
    
    private void logout(superadmindashboard dashboard) {
        dashboard.dispose();
        new view.logout().setVisible(true);
    }
}