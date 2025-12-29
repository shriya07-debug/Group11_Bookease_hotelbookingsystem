package controller;

import dao.BookingDAO;
import model.BookingModel;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BookingController {
    private final BookingDAO bookingDAO;
    
    public BookingController() {
        this.bookingDAO = new BookingDAO();
    }
    
    // Get user bookings (for controller use)
    public List<BookingModel> getUserBookings(int userId) {
        return bookingDAO.getUserBookings(userId);
    }
    
    // Create table model with booking data
    public DefaultTableModel createBookingsTableModel(int userId) {
        List<BookingModel> bookings = getUserBookings(userId);
        
        String[] columns = {"User_id", "Booking_id", "Hotel_name", "Check_in_date", 
                          "Check_out_date", "Price", "Status"};
        
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        for (BookingModel booking : bookings) {
            model.addRow(new Object[]{
                userId,
                booking.getBookingId(),
                booking.getHotelName(),
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                "Rs " + booking.getPrice(),
                booking.getStatus()
            });
        }
        
        return model;
    }
    
    // Get user bookings count
    public int getUserBookingCount(int userId) {
        return getUserBookings(userId).size();
    }
    
    // Check if user has bookings
    public boolean hasBookings(int userId) {
        return !getUserBookings(userId).isEmpty();
    }
    
    // Get total amount spent
    public double getTotalSpent(int userId) {
        List<BookingModel> bookings = getUserBookings(userId);
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