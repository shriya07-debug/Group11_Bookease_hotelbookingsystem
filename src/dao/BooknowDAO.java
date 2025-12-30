package dao;

import model.BooknowModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class BooknowDAO {
    private final Connection connection;

    // Constructor - receives the connection from your JFrame
    public BooknowDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Save a new booking to the database
     * @param booking
     * @return true if successful, false otherwise
     */
public boolean saveBooking(BooknowModel booking) {
    String sql = "INSERT INTO bookings " +
                 "(room_type, number_of_people, check_in_date, check_out_date) " +
                 "VALUES (?, ?, ?, ?)";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, booking.getRoomType());
        ps.setInt(2, booking.getNumberOfPeople());
        ps.setDate(3, booking.getCheckInDate());
        ps.setDate(4, booking.getCheckOutDate());

        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null,
            "Error saving booking: " + e.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();  // Remove later, helpful for debugging now
        return false;
    }
}

    /**
     * Get all bookings (useful for future admin view)
     * @return 
     */
    public List<BooknowModel> getAllBookings() {
        List<BooknowModel> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings ORDER BY booking_date DESC";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                BooknowModel booking = new BooknowModel();
                booking.setId(rs.getInt("id"));
                booking.setRoomType(rs.getString("room_type"));
                booking.setNumberOfPeople(rs.getInt("no_of_people"));
                booking.setCheckInDate(rs.getDate("check_in_date"));
                booking.setCheckOutDate(rs.getDate("check_out_date"));
               
                bookings.add(booking);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching bookings: " + e.getMessage());
        }
        return bookings;
    }

    /**
     * Optional: Check if a room is already booked for given dates
     * (You can expand this later for real availability check)
     * @param roomType
     * @param checkIn
     * @param checkOut
     * @return 
     */
    public boolean isRoomAvailable(String roomType, Date checkIn, Date checkOut) {
        String sql = "SELECT COUNT(*) FROM bookings " +
                     "WHERE room_type = ? " +
                     "AND ((check_in_date <= ? AND check_out_date >= ?) " +
                     "OR (check_in_date <= ? AND check_out_date >= ?))";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, roomType);
            ps.setDate(2, checkOut);
            ps.setDate(3, checkIn);
            ps.setDate(4, checkOut);
            ps.setDate(5, checkIn);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0; // true if no overlapping bookings
            }
        } catch (SQLException e) {
        }
        return false; // assume not available if error
    }

    // You can add more methods later: updateBooking, deleteBooking, getBookingById, etc.

    }
