package controller;

import dao.AnalyticsDAO;
import database.MySqlConnection;
import model.AdminPerformanceModel;
import view.analytics;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import view.superadmindashboard;

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
        
       
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                
                if (col == 3 && row >= 0) {
                    Object hotelIdObj = table.getValueAt(row, 0);
                    int hotelId;
                    
                    try {
                        if (hotelIdObj instanceof String string) {
                            hotelId = Integer.parseInt(string);
                        } else {
                            hotelId = (int) hotelIdObj;
                        }
                        
                        String adminName = table.getValueAt(row, 1).toString();
                        showAnalytics(hotelId, adminName);
                        
                    } 
                    
                    catch (NumberFormatException e) {
                        System.out.println(e);
                        
                    }
                }
            }
        });
        
        setupBackButton(window); 
       
        db.closeConnection(conn);
        
    } 
    
    catch (SQLException e) {
       System.out.println(e);
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
        } 
        
        catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    
    private void showChartDialog(AdminPerformanceModel performance, String adminName) {
  
    String message = "Analytics for: " + adminName + "\n\n" +
                    "Total Bookings: " + performance.getTotalBookings() + "\n" +
                    "Total Revenue: ₹" + performance.getTotalRevenue() + "\n\n" +
                    "Click OK to view charts";
    
    int choice = JOptionPane.showConfirmDialog(null, message, "Analytics", 
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
    
    if (choice == JOptionPane.OK_OPTION) {
        
        util.chartdisplay.showCharts(performance);
    }
}
    
    private void setupBackButton(analytics window) {
        window.addBackButtonListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                window.dispose();
                new superadmindashboard().setVisible(true);
            }
        });
    }
}