package controller;

import view.*;
import model.HotelModel;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardController {
    private final int currentUserId = 1;
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
    
    // ADD THIS - Override view details button to close dashboard
    card.getViewDetailsButton().addActionListener(e -> {
        dashboard.dispose(); // Close dashboard
        new view.viewdetails().setVisible(true); // Open viewdetails
    });
    
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
    System.out.println("DEBUG setupMenuBar called");
    System.out.println("dashboard: " + dashboard);
    System.out.println("dashboard.getMenuIcon(): " + dashboard.getMenuIcon());
    
    if (dashboard.getMenuIcon() == null) {
        System.out.println("ERROR: menuIcon is null!");
        return;
    }
    
    dashboard.getMenuIcon().addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent evt) {
            System.out.println("DEBUG: Menu icon clicked!");
            System.out.println("drawerPanel: " + dashboard.getDrawerPanel());
            toggleDrawer(dashboard.getDrawerPanel());
        }
    });
    
    System.out.println("DEBUG: Mouse listener added successfully");
}
    
    private void toggleDrawer(javax.swing.JPanel drawerPanel) {
        if (drawerPanel.getX() < 0) {
            drawerPanel.setBounds(0, 0, 300, 720);
        } else {
            drawerPanel.setBounds(-300, 0, 300, 720);
        }
    }
    
    private void setupNavigation(userdashboard dashboard) {
        
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
        dashboard.getProfileLabel().addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                navigateToProfile(dashboard);
            }
        });
        
    }
    
    
    
    private void navigateToProfile(userdashboard dashboard) {
    try {
        // Get current user ID
        int userId = currentUserId; // Or get from session
        
        // Create profile window
        profile profileWindow = new profile();
        
        // Create and setup ProfileController
        ProfileController profileController = new ProfileController();
        profileController.setupProfile(profileWindow, userId);
        
        // Close dashboard and show profile
        dashboard.dispose();
        profileWindow.setVisible(true);
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(dashboard, 
            "Error opening profile: " + e.getMessage(), 
            "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    private void refreshDashboard(userdashboard dashboard) {
        dashboard.dispose();
        new userdashboard().setVisible(true);
    }
    
    private void navigateToBookingHistory(userdashboard dashboard) {
    try {
        bookinghistory historyWindow = new bookinghistory();
        BookingController controller = new BookingController();
        controller.setupBookingHistory(historyWindow, currentUserId);
        
        dashboard.dispose();
        historyWindow.setVisible(true);
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    private void navigateToNotifications(userdashboard dashboard) {
        dashboard.dispose();
        new notifications(currentUserId).setVisible(true);
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