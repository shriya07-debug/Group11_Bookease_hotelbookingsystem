/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Booking;
import java.sql.*;
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
    
    // Get single booking (latest one)
    public Booking getLatestBooking(int userId) {
        String sql = "SELECT booking_id, check_in_date, check_out_date, status, price " +
                     "FROM user_bookings WHERE user_id = ? ORDER BY check_in_date DESC LIMIT 1";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Booking booking = new Booking();
                booking.setBooking_ID(rs.getInt("booking_id"));
                booking.setCheck_in_date(rs.getDate("check_in_date"));
                booking.setCheck_out_date(rs.getDate("check_out_date"));
                booking.setStatus(rs.getString("status"));
                booking.setPrice(rs.getDouble("price"));
                return booking;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

