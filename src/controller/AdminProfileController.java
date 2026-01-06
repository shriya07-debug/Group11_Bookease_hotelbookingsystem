package controller;

import dao.AdminDAO;
import model.Admin;
import view.adminprofile;
import view.admindashboard;
import javax.swing.*;

public class AdminProfileController {
    private adminprofile view;
    private AdminDAO dao = new AdminDAO();
    private int hotelId = 101; 
    
    public AdminProfileController(adminprofile view) {
        this.view = view;
        loadData();
        setupButtons();
    }
    
    private void loadData() {
        Admin admin = dao.getAdminById(hotelId);
        if (admin != null) {
            view.getHotelIdField().setText(String.valueOf(admin.getId()));
            view.getFullNameField().setText(admin.getFullName());
            view.getEmailField().setText(admin.getEmail());
            view.getPhoneField().setText(admin.getPhone());
        }
    }
    
    private void setupButtons() {
 
        view.getBackButtonLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view.dispose();
                new admindashboard().setVisible(true);
            }
        });
        
    
        view.getEditButton().addActionListener(e -> {
            String name = view.getFullNameField().getText();
            String email = view.getEmailField().getText();
            String phone = view.getPhoneField().getText();
            
            if (name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Fill all fields");
                return;
            }
            
            Admin admin = new Admin();
            admin.setId(hotelId);
            admin.setFullName(name);
            admin.setEmail(email);
            admin.setPhone(phone);
            
            if (dao.updateAdmin(admin)) {
                JOptionPane.showMessageDialog(view, "Updated");
            } 
            else {
                JOptionPane.showMessageDialog(view, "Failed");
            }
        });
        
       
        view.getCancelButton().addActionListener(e -> {
            view.dispose();
            new admindashboard().setVisible(true);
        });
        
     
        view.getLogoutButton().addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(view, "Logout?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                view.dispose();
                JOptionPane.showMessageDialog(view, "Logged out");
            }
        });
    }
}