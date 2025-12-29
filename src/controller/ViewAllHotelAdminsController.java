/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDAO;
import model.UserModel;
import view.viewallhoteladmins;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ViewAllHotelAdminsController {
    private final UserDAO userDAO;
    
    public ViewAllHotelAdminsController() {
        this.userDAO = new UserDAO();
    }
    
    public void setupViewAllHotelAdmins(viewallhoteladmins view) {
        loadHotelAdmins(view);
        setupBackButton(view);
    }
    
    private void loadHotelAdmins(viewallhoteladmins view) {
        try {
            List<UserModel> hotelAdmins = userDAO.getAllHotelAdmins();
            DefaultTableModel model = (DefaultTableModel) view.getAdminsTable().getModel();
            model.setRowCount(0); // Clear existing rows
            
            for (UserModel admin : hotelAdmins) {
                Object[] rowData = {
                    admin.getHotelId() != null ? admin.getHotelId() : "N/A",
                    admin.getUsername() != null ? admin.getUsername() : "N/A",
                    admin.getEmail() != null ? admin.getEmail() : "N/A",
                    admin.getPassword() != null ? "******" : "N/A",
                    admin.getStatus() != null ? admin.getStatus() : "N/A",
                    admin.getLastLogin() != null ? admin.getLastLogin() : "N/A"
                };
                model.addRow(rowData);
            }
            
            // Show message if no hotel admins found
            if (hotelAdmins.isEmpty()) {
                JOptionPane.showMessageDialog(view, 
                    "No hotel admins found in the database.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, 
                "Error loading hotel admins: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void setupBackButton(viewallhoteladmins view) {
        view.getBackButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                goBackToSuperAdminDashboard(view);
            }
        });
    }
    
    private void goBackToSuperAdminDashboard(viewallhoteladmins view) {
        view.dispose();
        new view.superadmindashboard().setVisible(true);
    }
}
