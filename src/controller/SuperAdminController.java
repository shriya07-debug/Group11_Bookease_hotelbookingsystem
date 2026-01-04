/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SuperAdminDAO;
import java.awt.HeadlessException;
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
        
        dashboard.getDashboardLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                refreshDashboard(dashboard);
            }
        });
        
     
        dashboard.getAllHotelAdminsLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                navigateToAllHotelAdmins(dashboard);
            }
        });
        
        
        dashboard.getViewAnalyticsLabel().addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked(MouseEvent evt) {
                navigateToAnalytics(dashboard); 
            }
        });
        
       
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
    
    
    private void navigateToAnalytics(superadmindashboard dashboard) {
    try {
        view.analytics analyticsWindow = new view.analytics();
        AnalyticsController analyticsController = new AnalyticsController();
        analyticsController.loadTable(analyticsWindow);  
        
        
        dashboard.dispose();
        analyticsWindow.setVisible(true);
        
    } 
    
    catch (Exception e) {
        System.out.println(e);
    }
}
    
    private void createHotelAdmin(superadmindashboard dashboard) {
  
    String hotelIdStr = dashboard.getHotelIdField().getText().trim();
    String hotelName = dashboard.getHotelNameField().getText().trim();
    String email = dashboard.getEmailField().getText().trim();
    String password = new String(dashboard.getPasswordField().getPassword()).trim();
    
   
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
        
        if (superAdminDAO.emailExists(email)) {
            JOptionPane.showMessageDialog(dashboard, 
                "Email '" + email + "' already exists!\n" +
                "Please use a different email address.", 
                "Duplicate Email", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
      
        SuperAdminModel admin = new SuperAdminModel(hotelId,hotelName, email, password);
        
  
        boolean success = superAdminDAO.createHotelAdmin(admin);
        
        if (success) {
            JOptionPane.showMessageDialog(dashboard, 
                """
                Hotel Admin Created Successfully!               
                Hotel ID: """ + hotelId + "\n" +
                "Hotel Name: " + hotelName + "\n" +        
                "Admin Email: " + email + "\n" +
                "Password: " + password + "\n\n" +
                "Saved to users table as 'hotel_admin'", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            
            
            dashboard.getHotelIdField().setText("");
            dashboard.getHotelNameField().setText("");
            dashboard.getEmailField().setText("");
            dashboard.getPasswordField().setText("");
            dashboard.getHotelIdField().requestFocus();
            
        } 
        
        else {
            JOptionPane.showMessageDialog(dashboard, 
                "Failed to create hotel admin!", 
                "Creation Failed", JOptionPane.ERROR_MESSAGE);
        }
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(dashboard, 
            "Hotel ID must be a number!", "Invalid Hotel ID", JOptionPane.ERROR_MESSAGE);
    } 
    
    catch (HeadlessException e) { 
        System.out.println(e);
    }
}
    private void refreshDashboard(superadmindashboard dashboard) {
        dashboard.dispose();
        new view.superadmindashboard().setVisible(true);
    }
    
 
    private void navigateToAllHotelAdmins(superadmindashboard dashboard) {
    try {
        view.viewallhoteladmins adminsWindow = new view.viewallhoteladmins();
   
        ViewAllHotelAdminsController controller = new ViewAllHotelAdminsController();
        controller.setupViewAllHotelAdmins(adminsWindow);
        
   
        dashboard.dispose();
        adminsWindow.setVisible(true);
        
    } 
    
    catch (Exception e) {
        System.out.println(e);
    }
}
    
    
    private void logout(superadmindashboard dashboard) {
        
    try {
        dashboard.dispose();
       
        LogoutController.showLogoutWindow();
    } 
    
    catch (Exception e) {
        new view.logout().setVisible(true);
    }
  }
}