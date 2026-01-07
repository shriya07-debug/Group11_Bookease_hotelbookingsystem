package controller;

import dao.AdminDashboardDAO;
import model.AdminDashboardModel;
import view.admindashboard;
import javax.swing.*;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import view.adminprofile;
import view.invoice;
import view.support;

public class AdminDashboardController {
    
    public void setupAdminDashboard(admindashboard dashboard) {
        loadAndDisplayImage(dashboard);
        setupMenuIcon(dashboard);
        setupNavigation(dashboard);
        setupGenerateInvoice(dashboard);
    }
    
    private void loadAndDisplayImage(admindashboard dashboard) {
        AdminDashboardDAO dao = new AdminDashboardDAO();
        AdminDashboardModel model = dao.getData();
        
        if (model != null && model.getimage() != null) {
            JLabel imageLabel = dashboard.getImageLabel();
            if (imageLabel != null) {
                setImage(imageLabel, model.getimage());
            }
        }
    }
    
    private void setImage(JLabel label, String path) {
        
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(path));
            if (icon.getIconWidth() <= 0) {
                icon = new ImageIcon(path);
            }
            Image img = icon.getImage().getScaledInstance(
                label.getWidth(), 
                label.getHeight(), 
                Image.SCALE_SMOOTH
            );
            label.setIcon(new ImageIcon(img));
        } 
        
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void setupMenuIcon(admindashboard dashboard) {
        dashboard.getMenuIcon().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                toggleDrawer(dashboard.getDrawerPanel());
            }
        });
    }
    
    private void toggleDrawer(JPanel drawerPanel) {
        if (drawerPanel.getX() < 0) {
            drawerPanel.setBounds(0, 0, 300, 720);
        }
        
        else {
            drawerPanel.setBounds(-300, 0, 300, 720);
        }
    }
    
    private void setupNavigation(admindashboard dashboard) {
       
        dashboard.getDashboardLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                refreshDashboard(dashboard);
            }
        });
        
     
        dashboard.getRecentBookingsLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                navigateToRecentBookings(dashboard);
            }
        });
        
  
        dashboard.getProfileLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                navigateToProfile(dashboard);
            }
        });
        
   
        dashboard.getSupportLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                navigateToSupport(dashboard);
            }
        });
    }
    
    private void setupGenerateInvoice(admindashboard dashboard) {
        dashboard.addGenerateInvoiceListener(e -> {
            generateInvoice(dashboard);
        });
    }
    private void generateInvoice(admindashboard dashboard) {
   
    dashboard.dispose();
    
    java.awt.EventQueue.invokeLater(() -> {
        invoice invoiceFrame = new invoice();
        InvoiceController controller = new InvoiceController(invoiceFrame);
        
    
        controller.showInvoice(201);
        controller.setupBackButton();
        
        invoiceFrame.setVisible(true);
    });
}
    
    private void refreshDashboard(admindashboard dashboard) {
        dashboard.dispose();
        new view.admindashboard().setVisible(true);
    }
    
    private void navigateToRecentBookings(admindashboard dashboard) {
        dashboard.dispose();
        
        JOptionPane.showMessageDialog(dashboard, 
            "Recent Bookings feature coming soon!", 
            "Info", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
   private void navigateToProfile(admindashboard dashboard) {
    dashboard.dispose();
    
    java.awt.EventQueue.invokeLater(() -> {
        adminprofile profileFrame = new adminprofile();      
        AdminProfileController controller = new AdminProfileController(profileFrame);
        
        profileFrame.setVisible(true);
    });
}
    
    private void navigateToSupport(admindashboard dashboard) {
    dashboard.dispose();
    
   
    support supportWindow = new support("hotel_admin");
    SupportController.setupSupport(supportWindow, "hotel_admin");
    supportWindow.setVisible(true);
}
    
    
}