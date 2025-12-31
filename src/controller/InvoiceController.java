package controller;

import dao.InvoiceDAO;
import model.Invoice;

public class InvoiceController {

    private InvoiceDAO invoiceDAO = new InvoiceDAO();

    public Invoice getInvoice(String bookingId) {
        return invoiceDAO.getInvoiceByBookingId(bookingId);
    }

    public Invoice getInvoiceByBookingId(String bookingId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
