package controller;

import dao.AdminDAO;
import model.Admin;
import view.adminprofile;

import javax.swing.*;

public class AdminProfileController {

    private final adminprofile view;
    private final AdminDAO dao = new AdminDAO();
    private final int adminId = 1; // change if needed

    public AdminProfileController(adminprofile view) {
        this.view = view;
        loadAdminData();
        addActions();
    }

    private void loadAdminData() {
        Admin admin = dao.getAdminById(adminId);
        if (admin != null) {
            view.getUserIdField().setText(String.valueOf(admin.getId()));
            view.getFullNameField().setText(admin.getFullName());
            view.getEmailField().setText(admin.getEmail());
            view.getPhoneField().setText(admin.getPhone());

            
        }
    }

    private void addActions() {
        view.getLogoutButton().addActionListener(e -> {
            JOptionPane.showMessageDialog(view, "Logged out");
            view.dispose();
        });

        view.getCancelButton().addActionListener(e ->
                JOptionPane.showMessageDialog(view, "Cancelled")
        );

        view.getEditButton().addActionListener(e ->
                JOptionPane.showMessageDialog(view, "Edit clicked")
        );
    }
}
