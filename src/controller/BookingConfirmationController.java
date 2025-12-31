package controller;

import view.confirmation;
import dao.BookingConfirmationDAO;
import model.BookingConfirmationModel;
import javax.swing.*;

public class BookingConfirmationController {
    
    public static void show(int bookingId) {
        // Create view
        confirmation view = new confirmation();
        
        // Create DAO
        BookingConfirmationDAO dao = new BookingConfirmationDAO();
        
        // Try to get data from database
        BookingConfirmationModel data = dao.getConfirmationByBookingId(bookingId);
        
        if (data == null) {
            // No data found in database
            JOptionPane.showMessageDialog(view, 
                "No confirmation found in database for booking #" + bookingId + "\n" +
                "Please check if the booking exists.",
                "Data Not Found",
                JOptionPane.WARNING_MESSAGE);
            
            // Show empty fields with message
            view.getBookingIdField().setText("#" + bookingId + " (Not Found)");
            view.getDateField().setText("No data in database");
            view.getStatusField().setText("Not confirmed");
            
        } else {
            // Data found - display it
            view.getBookingIdField().setText("#" + data.getBookingId());
            view.getDateField().setText(data.getFormattedDate());
            view.getStatusField().setText(data.getStatus());
            
            // Color code the status
            if ("confirmed".equalsIgnoreCase(data.getStatus())) {
                view.getStatusField().setForeground(new java.awt.Color(0, 180, 0));
            }
        }
        
        // Show the window
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }
}