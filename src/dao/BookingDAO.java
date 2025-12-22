package dao;

import database.MySqlConnection;
import model.BookingModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    MySqlConnection mysql = new MySqlConnection();
    
    public List<BookingModel> getUserBookings(int userId) {
        List<BookingModel> bookings = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM bookings WHERE User_id = ? ORDER BY Booking_id";
        
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, userId);
            ResultSet result = pstm.executeQuery();
            
            while (result.next()) {
                BookingModel booking = new BookingModel();
                booking.setBookingId(result.getInt("Booking_id"));
                booking.setUserId(result.getInt("User_id"));
                booking.setHotelName(result.getString("Hotel_name"));
                booking.setCheckInDate(result.getDate("Check_in_date"));
                booking.setCheckOutDate(result.getDate("Check_out_date"));
                booking.setStatus(result.getString("Status"));
                booking.setPrice(result.getDouble("Price"));
                
                bookings.add(booking);
            }
        } catch (Exception ex) {
            System.out.println("Error in getUserBookings: " + ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return bookings;
    }
}