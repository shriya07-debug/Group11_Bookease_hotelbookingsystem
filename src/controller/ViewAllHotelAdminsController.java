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
    
  
    public void setupViewAllHotelAdmins(viewallhoteladmins window) {
        loadHotelAdmins(window);
        setupBackButton(window);
        window.setVisible(true);
    }
    
    private void loadHotelAdmins(viewallhoteladmins window) {
        try {
            List<UserModel> admins = userDAO.getAllHotelAdmins();
            
            if (admins != null && !admins.isEmpty()) {
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
                
                window.getAdminsTable().setModel(model);
            } 
            else {
                System.out.println("No hotel admins found");
            }
        }
        
        catch (Exception e) {
            System.err.println(e);
        }
    }
    
    private void setupBackButton(viewallhoteladmins window) {
       
        for (java.awt.event.MouseListener ml : window.getBackbutton().getMouseListeners()) {
            window.getBackbutton().removeMouseListener(ml);
        }
        
      
        window.getBackbutton().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               
                window.dispose();
               
                new superadmindashboard().setVisible(true);
            }
           
        });
    }
}