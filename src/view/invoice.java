package view;

import controller.InvoiceController;
import model.Invoice;
import javax.swing.*;
import java.awt.*;


public class invoice extends javax.swing.JFrame {

    // ✅ ADD HERE (CLASS-LEVEL VARIABLES)
    private InvoiceController invoiceController;
    private String bookingId;

    public invoice(String bookingId) {
        initComponents();
        this.bookingId = bookingId;
        invoiceController = new InvoiceController();
        loadInvoice();
        setLocationRelativeTo(null);
    }
    
    
 private void loadInvoice() {
    Invoice invoice = invoiceController.getInvoiceByBookingId(bookingId);

    if (invoice == null) {
        JOptionPane.showMessageDialog(this, "Invoice not found");
        return;
    }

    Invoice.setText("Invoice ID: " + invoice.getInvoiceId());
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Invoice = new javax.swing.JLabel();
        Labelbackgroundimage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);
        getContentPane().add(jPanel1);
        jPanel1.setBounds(43, 0, 100, 100);

        Invoice.setBackground(new java.awt.Color(217, 217, 217));
        Invoice.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Invoice.setForeground(new java.awt.Color(241, 151, 174));
        Invoice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Invoice.setText("Invoice");
        getContentPane().add(Invoice);
        Invoice.setBounds(520, 100, 270, 50);

        Labelbackgroundimage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help.png"))); // NOI18N
        getContentPane().add(Labelbackgroundimage);
        Labelbackgroundimage.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

 
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Invoice;
    private javax.swing.JLabel Labelbackgroundimage;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
   
}     
    

