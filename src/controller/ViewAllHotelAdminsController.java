package controller;

import dao.UserDAO;
import model.UserModel;
import view.viewallhoteladmins;
import view.superadmindashboard;
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
    view.addBackButtonListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            System.out.println("Back button clicked - navigating to superadmin dashboard");
            view.dispose();
            new superadmindashboard().setVisible(true);
        }
    });
  }
}