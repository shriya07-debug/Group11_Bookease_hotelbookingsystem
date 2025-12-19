/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.BookingDAO;
import model.BookingModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;
/**
 *
 * @author sailenawale
 */
public class BookingController {
   
    private final BookingDAO bookingDAO;
    
    public BookingController() {
        this.bookingDAO = new BookingDAO();
    }
    
    // Load user bookings into table
    public void loadUserBookings(JTable table, int userId) {
        try {
            List<BookingModel> bookings = bookingDAO.getUserBookings(userId);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing rows
            
            boolean hasData = false;
            for (BookingModel booking : bookings) {
                hasData = true;
                Object[] row = {
                    booking.getBookingId(),
                    booking.getHotelName(),
                    booking.getCheckInDate(),
                    booking.getCheckOutDate(),
                    "Rs " + booking.getPrice(),
                    booking.getStatus()
                };
                model.addRow(row);
            }
            
            if (!hasData) {
                JOptionPane.showMessageDialog(null,
                    "No bookings found.\nBook hotels from dashboard first!",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Error loading bookings: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Get user bookings count
    public int getUserBookingCount(int userId) {
        List<BookingModel> bookings = bookingDAO.getUserBookings(userId);
        return bookings.size();
    }
    
    // Check if user has bookings
    public boolean hasBookings(int userId) {
        List<BookingModel> bookings = bookingDAO.getUserBookings(userId);
        return !bookings.isEmpty();
    }
    
    // Get total amount spent
    public double getTotalSpent(int userId) {
        List<BookingModel> bookings = bookingDAO.getUserBookings(userId);
        double total = 0;
        for (BookingModel booking : bookings) {
            if ("confirmed".equalsIgnoreCase(booking.getStatus()) || 
                "completed".equalsIgnoreCase(booking.getStatus())) {
                total += booking.getPrice();
            }
        }
        return total;
    }
}

