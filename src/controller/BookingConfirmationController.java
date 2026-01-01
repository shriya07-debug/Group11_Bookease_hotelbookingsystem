package controller;

import view.confirmation;
import view.userdashboard; // Add this import
import dao.BookingConfirmationDAO;
import model.BookingConfirmationModel;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class BookingConfirmationController {
    
    private confirmation view;
    
    // Add a non-static method to handle setup with back button
    public BookingConfirmationController(confirmation view, int bookingId) {
        this.view = view;
        setupBackButton();
        loadBookingData(bookingId);
        setupPayNowButton();
    }
    
    private void setupBackButton() {
        view.getBackButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                handleBackNavigation();
            }
        });
    }
    
    private void setupPayNowButton() {
        view.getPayNowButton().addActionListener(e -> handlePayNow());
    }
    
    private void handleBackNavigation() {
        // Dispose current confirmation window
        view.dispose();
        
        // Navigate back to userdashboard
        SwingUtilities.invokeLater(() -> {
            new userdashboard().setVisible(true);
        });
    }
    
    private void handlePayNow() {
        // Handle payment logic
        JOptionPane.showMessageDialog(view, "Payment feature coming soon!");
    }
    
    private void loadBookingData(int bookingId) {
        BookingConfirmationDAO dao = new BookingConfirmationDAO();
        BookingConfirmationModel data = dao.getConfirmationByBookingId(bookingId);
        
        if (data == null) {
            JOptionPane.showMessageDialog(view, 
                "No confirmation found in database for booking " + bookingId + "\n" +
                "Please check if the booking exists.",
                "Data Not Found",
                JOptionPane.WARNING_MESSAGE);
            
            view.getBookingIdField().setText("#" + bookingId + " (Not Found)");
            view.getDateField().setText("No data in database");
            view.getStatusField().setText("Not confirmed");
            
        } else {
            view.getBookingIdField().setText("#" + data.getBookingId());
            view.getDateField().setText(data.getFormattedDate());
            view.getStatusField().setText(data.getStatus());
            
            if ("confirmed".equalsIgnoreCase(data.getStatus())) {
                view.getStatusField().setForeground(new java.awt.Color(0, 180, 0));
            }
        }
    }
    
    // Keep the static show method for backward compatibility
    public static void show(int bookingId) {
        confirmation view = new confirmation();
        new BookingConfirmationController(view, bookingId);
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }
}