package controller;

import dao.BookingDAO;
import model.BookingModel;
import view.bookinghistory;
import view.userdashboard;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BookingController {
    private BookingDAO bookingDAO;
    
    public BookingController() {
        this.bookingDAO = new BookingDAO();
    }
    
    public void setupBookingHistory(bookinghistory window, int userId) {
        loadBookingData(window, userId);
        setupBackButton(window);
    }
    
    private void loadBookingData(bookinghistory window, int userId) {
        try {
            List<BookingModel> bookings = bookingDAO.getUserBookings(userId);
            
            // Create table model with EXACT column names
            String[] columns = {"user_id", "booking_id", "hotel_name", "check_in_date", 
                              "check_out_date", "total_price", "status"};
            
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            
            for (BookingModel booking : bookings) {
                model.addRow(new Object[]{
                    booking.getUserId(),
                    booking.getBookingId(),
                    booking.getHotelName(),
                    booking.getCheckInDate(),
                    booking.getCheckOutDate(),
                    "Rs " + booking.getPrice(),
                    booking.getStatus()
                });
            }
            
            window.getBookingsTable().setModel(model);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(window, "Error loading bookings!");
        }
    }
    
    private void setupBackButton(bookinghistory window) {
        window.getBackButton().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                window.dispose();
                new userdashboard().setVisible(true);
            }
        });
    }
}