/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sailenawale
 */
public class BookingDAO {
    private Connection connection;
    
    public BookingDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/booking_db", 
                "root", 
                "shr7y42007@#"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // 1️⃣ METHOD TO SAVE NEW BOOKING (when user books)
    public boolean saveBooking(int userId, String hotelName, Date checkIn, Date checkOut, double price) {
        String sql = "INSERT INTO bookings (user_id, hotel_name, check_in_date, check_out_date, price, status) " +
                     "VALUES (?, ?, ?, ?, ?, 'confirmed')";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, hotelName);
            stmt.setDate(3, new java.sql.Date(checkIn.getTime()));
            stmt.setDate(4, new java.sql.Date(checkOut.getTime()));
            stmt.setDouble(5, price);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // 2️⃣ METHOD TO GET ALL BOOKINGS FOR HISTORY
    public List<Booking> getUserBookings(int userId) {
        List<Booking> bookings = new ArrayList<>();
        
        String sql = "SELECT booking_id, user_id,hotel_name, check_in_date, check_out_date, status, price " +
                     "FROM bookings WHERE user_id = ? ORDER BY check_in_date DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            // ⭐⭐ GET ALL ROWS, not just one!
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setUserId(rs.getInt("user_id"));
                booking.setHotelName(rs.getString("hotel_name"));      // Get hotel name!
                booking.setCheckInDate(rs.getDate("check_in_date"));
                booking.setCheckOutDate(rs.getDate("check_out_date"));
                booking.setStatus(rs.getString("status"));
                booking.setPrice(rs.getDouble("price"));
                
                bookings.add(booking); // Add to list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings; // Return LIST of bookings
    }
}