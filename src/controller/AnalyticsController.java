package controller;

import dao.AnalyticsDAO;
import database.MySqlConnection;
import model.AdminPerformanceModel;
import view.analytics;
import javax.swing.*;
import java.sql.Connection;
import view.superadmindashboard;
import view.viewallhoteladmins;

public class AnalyticsController {
    
    
            public void loadTable(analytics window) {
    try {
        MySqlConnection db = new MySqlConnection();
        Connection conn = db.openConnection();
        AnalyticsDAO dao = new AnalyticsDAO(conn);
        Object[][] data = dao.getHotelAdminsForTable();
        
        JTable table = window.getAnalyticstable();
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            data,
            new String[]{"hotel_id", "username", "email", "analytics"}
        );
        table.setModel(model);
        
        // Add click listener
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                
                if (col == 3 && row >= 0) {
                    Object hotelIdObj = table.getValueAt(row, 0);
                    int hotelId;
                    
                    try {
                        if (hotelIdObj instanceof String) {
                            hotelId = Integer.parseInt((String) hotelIdObj);
                        } else {
                            hotelId = (int) hotelIdObj;
                        }
                        
                        String adminName = table.getValueAt(row, 1).toString();
                        showAnalytics(hotelId, adminName);
                        
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, 
                            "Invalid hotel ID format: " + hotelIdObj, 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        setupBackButton(window);  // ADD THIS LINE
        
        // ADD THIS: Close connection
        db.closeConnection(conn);
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(window, "Error loading data: " + e.getMessage());
    }
}
    
    private void showAnalytics(int hotelId, String adminName) {
        try {
            MySqlConnection db = new MySqlConnection();
            Connection conn = db.openConnection();
            AnalyticsDAO dao = new AnalyticsDAO(conn);
            AdminPerformanceModel performance = dao.getAdminPerformance(hotelId);
            db.closeConnection(conn);
            
            if (performance != null) {
                showChartDialog(performance, adminName);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void showChartDialog(AdminPerformanceModel performance, String adminName) {
    // Option 1: Simple message (current)
    String message = "Analytics for: " + adminName + "\n\n" +
                    "Total Bookings: " + performance.getTotalBookings() + "\n" +
                    "Total Revenue: ₹" + performance.getTotalRevenue() + "\n\n" +
                    "Click OK to view charts";
    
    int choice = JOptionPane.showConfirmDialog(null, message, "Analytics", 
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
    
    if (choice == JOptionPane.OK_OPTION) {
        // Show JFreeChart
        util.chartdisplay.showCharts(performance);
    }
}
    private void setupBackButton(analytics window) {
        window.addBackButtonListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                window.dispose();
                new superadmindashboard().setVisible(true);
            }
        });
    }
}