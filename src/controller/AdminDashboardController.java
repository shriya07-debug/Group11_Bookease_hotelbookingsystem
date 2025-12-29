package controller;

import dao.AdminDashboardDAO;
import model.AdminDashboardModel;
import view.admindashboard;
import javax.swing.*;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        } catch (Exception e) {
            e.printStackTrace();
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
        } else {
            drawerPanel.setBounds(-300, 0, 300, 720);
        }
    }
    
    private void setupNavigation(admindashboard dashboard) {
        // Dashboard (refresh)
        dashboard.getDashboardLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                refreshDashboard(dashboard);
            }
        });
        
        // Recent Bookings
        dashboard.getRecentBookingsLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                navigateToRecentBookings(dashboard);
            }
        });
        
        // Profile
        dashboard.getProfileLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                navigateToProfile(dashboard);
            }
        });
        
        // Support
        dashboard.getSupportLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                navigateToSupport(dashboard);
            }
        });
    }
    
    private void setupGenerateInvoice(admindashboard dashboard) {
        dashboard.addGenerateInvoiceListener(e -> {
            generateInvoice();
        });
    }
    
    private void refreshDashboard(admindashboard dashboard) {
        dashboard.dispose();
        new view.admindashboard().setVisible(true);
    }
    
    private void navigateToRecentBookings(admindashboard dashboard) {
        dashboard.dispose();
        // TODO: Create admin booking history view
        JOptionPane.showMessageDialog(dashboard, 
            "Recent Bookings feature coming soon!", 
            "Info", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void navigateToProfile(admindashboard dashboard) {
        dashboard.dispose();
        // TODO: Create admin profile view
        JOptionPane.showMessageDialog(dashboard, 
            "Admin Profile feature coming soon!", 
            "Info", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void navigateToSupport(admindashboard dashboard) {
        dashboard.dispose();
        // TODO: Create admin support view or use existing support
        new view.support().setVisible(true);
    }
    
    private void generateInvoice() {
        JOptionPane.showMessageDialog(null, 
            "Invoice generation feature coming soon!", 
            "Info", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}