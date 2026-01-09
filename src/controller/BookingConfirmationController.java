package controller;

import view.confirmation;
import view.userdashboard; 
import dao.BookingConfirmationDAO;
import model.BookingConfirmationModel;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class BookingConfirmationController {
    
    private final confirmation view;
    
    
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
        
        view.dispose();
        
        SwingUtilities.invokeLater(() -> {
            new userdashboard().setVisible(true);
        });
    }
    
    private void handlePayNow() {
      
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
            
            view.getBookingIdField().setText(bookingId + " (Not Found)");
            view.getDateField().setText("No data in database");
            view.getStatusField().setText("Not confirmed");
            
        } 
        else {
            view.getBookingIdField().setText("#" + data.getBookingId());
            view.getDateField().setText(data.getFormattedDate());
            view.getStatusField().setText(data.getStatus());
            

        }
    }
    
    
    public static void show(int bookingId) {
        confirmation view = new confirmation();
        new BookingConfirmationController(view, bookingId);
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }
}