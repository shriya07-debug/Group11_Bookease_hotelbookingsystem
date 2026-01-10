package controller;

import view.confirmation;
import view.userdashboard;
import view.invoice; 
import dao.BookingConfirmationDAO;
import dao.InvoiceDAO;
import java.awt.HeadlessException;
import model.BookingConfirmationModel;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class BookingConfirmationController {
    
    private final confirmation view;
    private final int bookingId;
    private final int userId = 1; 
    
    public BookingConfirmationController(confirmation view, int bookingId) {
        this.view = view;
        this.bookingId = bookingId;
        setupBackButton();
        loadBookingData(bookingId);
        setupPayNowButton();
    }
    
    private void setupPayNowButton() {
        view.getPayNowButton().addActionListener(e -> handlePayment());
    }
    
   private void handlePayment() {
    try {
      
        OnlinePaymentController controller = new OnlinePaymentController();
        controller.processPayment(1000);
        
   
        BookingConfirmationDAO dao = new BookingConfirmationDAO();
        boolean statusUpdated = updateBookingStatusToPaid(dao);
        
        if (statusUpdated) {
            
            view.getStatusField().setText("Paid");
            view.getPayNowButton().setEnabled(false);
            view.getPayNowButton().setText("Paid ");
            
        
            navigateToInvoice();
            
        } else {
            JOptionPane.showMessageDialog(view,
                "Payment processed but failed to update status.",
                "Status Update Error",
                JOptionPane.WARNING_MESSAGE);
        }
        
    } catch (HeadlessException e) {
           System.out.println(e);
    }
}
    
    private boolean updateBookingStatusToPaid(BookingConfirmationDAO dao) {
    
        System.out.println("Updating booking " + bookingId + " status to 'paid'");
        return true;
        
       
    }
private void navigateToInvoice() {
    view.dispose();
    
    java.awt.EventQueue.invokeLater(() -> {
        try {
            // First, find the invoice ID for this booking
            InvoiceDAO invoiceDAO = new InvoiceDAO();
            int invoiceId = invoiceDAO.getInvoiceIdByBookingId(bookingId);
            
            if (invoiceId > 0) {
                // Create invoice window
                invoice invoiceFrame = new invoice(userId);
                
                // Create and setup controller WITH the invoice ID
                InvoiceController controller = new InvoiceController(invoiceFrame);
                controller.showInvoice(invoiceId); // ← THIS WAS MISSING!
                controller.setupBackButton();
                
                invoiceFrame.setVisible(true);
                invoiceFrame.setLocationRelativeTo(null);
                
                System.out.println("Opened invoice ID: " + invoiceId + " for booking: " + bookingId);
            } else {
                System.out.println("No invoice found for booking: " + bookingId);
                JOptionPane.showMessageDialog(null, 
                    "No invoice found for this booking. Please contact support.", 
                    "Invoice Not Found", 
                    JOptionPane.WARNING_MESSAGE);
                new userdashboard().setVisible(true);
            }
            
        } catch (Exception e) {
            System.err.println("Error opening invoice: " + e.getMessage());
            e.printStackTrace();
            new userdashboard().setVisible(true);
        }
    });
}
    private void setupBackButton() {
        view.getBackButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                handleBackNavigation();
            }
        });
    }
    
    private void handleBackNavigation() {
        view.dispose();
        SwingUtilities.invokeLater(() -> {
            new userdashboard().setVisible(true);
        });
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
            
        } else {
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