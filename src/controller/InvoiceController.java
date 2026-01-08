package controller;

import dao.InvoiceDAO;
import model.InvoiceModel;
import view.invoice;
import javax.swing.*;
import java.text.SimpleDateFormat;

public class InvoiceController {
    private final invoice view;
    
    public InvoiceController(invoice view) {
        this.view = view;
    }
    
    public void showInvoice(int invoiceId) {
        InvoiceDAO dao = new InvoiceDAO();
        InvoiceModel invoice = dao.getInvoiceById(invoiceId);
        
        if (invoice != null) {
            
            view.getInvoiceIdLabel().setText("Invoice ID:             " + invoice.getInvoiceId());
            view.getBookingIdLabel().setText("Booking ID:           " + invoice.getBookingId());
            view.getFullNameLabel().setText("Full name:              " + invoice.getFullName());
            view.getUserIdLabel().setText("User ID:                 " + invoice.getUserId());
            view.getHotelIdLabel().setText("Hotel ID:                " + invoice.getHotelId());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            view.getCheckInDateLabel().setText("Check-in:               " + sdf.format(invoice.getCheckInDate()));
            view.getCheckOutDateLabel().setText("Check-out:             " + sdf.format(invoice.getCheckOutDate()));
            
            view.getPaymentMethodLabel().setText("Payment:                " + invoice.getPaymentMethod());
            view.getPaymentStatusLabel().setText("Status:                   " + invoice.getPaymentStatus());
            view.getRoomChargeLabel().setText("Room:                     Rs." + invoice.getRoomCharge());
            view.getExtraChargeLabel().setText("Extra:                      Rs." + invoice.getExtraCharge());
            view.getTotalChargeLabel().setText("Total:                       Rs." + invoice.getTotalCharge());
        }   
        
        else {
            JOptionPane.showMessageDialog(view, "No invoice found");
        }
    }
    
    public void setupBackButton() {
        view.getBackButtonLabel().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new view.logout().setVisible(true);
            }
        });
    }
}