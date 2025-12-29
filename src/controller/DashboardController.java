package controller;

import view.*;
import model.HotelModel;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardController {
    
    public void setupUserDashboard(userdashboard dashboard) {
        setupSearch(dashboard);
        setupNavigation(dashboard);
        setupMenuBar(dashboard);
    }
    
    private void setupSearch(userdashboard dashboard) {
        dashboard.getSearchButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleSearch(dashboard);
            }
        });
        
        dashboard.getSearchBar().addActionListener(e -> {
            handleSearch(dashboard);
        });
    }
    
    private void handleSearch(userdashboard dashboard) {
        String hotelName = dashboard.getSearchBar().getText().trim();
        
        if (hotelName.isEmpty() || hotelName.equalsIgnoreCase("Search here")) {
            JOptionPane.showMessageDialog(dashboard, "Please enter hotel name!");
            return;
        }
        
        HotelController hotelController = new HotelController();
        HotelModel hotel = hotelController.searchHotel(hotelName);
        
        if (hotel == null) {
            JOptionPane.showMessageDialog(dashboard, "Hotel '" + hotelName + "' not found!");
            return;
        }
        
        showHotelOnDashboard(dashboard, hotel);
        dashboard.getSearchBar().setText("");
    }
    
    private void showHotelOnDashboard(userdashboard dashboard, HotelModel hotel) {
        JLabel welcomeLabel = dashboard.getWelcomeLabel();
        if (welcomeLabel != null && welcomeLabel.isVisible()) {
            welcomeLabel.setVisible(false);
        }
        
        // Use HotelController to create and setup the hotel card
        HotelController hotelController = new HotelController();
        hotelcard card = hotelController.createHotelCard(hotel);
        
        int hotelCardCount = countHotelCards(dashboard);
        int cardWidth = 300;
        int cardHeight = 450;
        int gap = 55;
        int xPosition = 400 + (hotelCardCount * (cardWidth + gap));
        
        card.setBounds(xPosition, 200, cardWidth, cardHeight);
        dashboard.getMainPanel().add(card);
        dashboard.getMainPanel().setComponentZOrder(card, 0);
        
        dashboard.getMainPanel().revalidate();
        dashboard.getMainPanel().repaint();
        
        JOptionPane.showMessageDialog(dashboard, 
            "✓ " + hotel.getHotelName() + " added to dashboard!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private int countHotelCards(userdashboard dashboard) {
        int count = 0;
        for (java.awt.Component comp : dashboard.getMainPanel().getComponents()) {
            if (comp instanceof hotelcard) {
                count++;
            }
        }
        return count;
    }
    
    private void setupMenuBar(userdashboard dashboard) {
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
    
    private void setupNavigation(userdashboard dashboard) {
        dashboard.getProfileLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navigateToProfile(dashboard);
            }
        });
        
        dashboard.getDashboardLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshDashboard(dashboard);
            }
        });
        
        dashboard.getBookingHistoryLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navigateToBookingHistory(dashboard);
            }
        });
        
        dashboard.getNotificationsLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navigateToNotifications(dashboard);
            }
        });
        
        dashboard.getSupportLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navigateToSupport(dashboard);
            }
        });
    }
    
    private void navigateToProfile(userdashboard dashboard) {
        dashboard.dispose();
        new profile().setVisible(true);
    }
    
    private void refreshDashboard(userdashboard dashboard) {
        dashboard.dispose();
        new userdashboard().setVisible(true);
    }
    
    private void navigateToBookingHistory(userdashboard dashboard) {
        dashboard.dispose();
        new bookinghistory().setVisible(true);
    }
    
    private void navigateToNotifications(userdashboard dashboard) {
        dashboard.dispose();
        int userId = getCurrentUserId(); // Get from session
        new notifications(userId).setVisible(true);
    }
    
    private void navigateToSupport(userdashboard dashboard) {
        dashboard.dispose();
        new support().setVisible(true);
    }
    
    private int getCurrentUserId() {
        // Get from session
        return 1;
    }
    
    private String getCurrentUserRole() {
        // Get from session
        return "user";
    }
}