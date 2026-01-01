package controller;

import dao.UserDAO;
import model.UserModel;
import view.viewallhoteladmins;
import view.superadmindashboard;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewAllHotelAdminsController {
    private final UserDAO userDAO;
    
    public ViewAllHotelAdminsController() {
        this.userDAO = new UserDAO();
    }
    
    public void setupViewAllHotelAdmins(viewallhoteladmins view) {
        loadHotelAdmins(view);
        setupBackButton(view);
        view.setVisible(true);
    }
    
    private void loadHotelAdmins(viewallhoteladmins view) {
        List<UserModel> admins = userDAO.getAllHotelAdmins();
        
        String[] columns = {"Hotel_id", "Name", "Email", "Password", "Status", "Last_login"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        for (UserModel admin : admins) {
            model.addRow(new Object[]{
                admin.getHotelId(),
                admin.getUsername(),
                admin.getEmail(),
                "******",
                admin.getStatus(),
                admin.getLastLogin()
            });
        }
        
        view.getAdminsTable().setModel(model);
    }
    
    private void setupBackButton(viewallhoteladmins view) {
        view.getBackButton().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view.dispose();
                new superadmindashboard().setVisible(true);
            }
        });
    }
}