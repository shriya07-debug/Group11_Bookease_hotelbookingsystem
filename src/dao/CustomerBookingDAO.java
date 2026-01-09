package dao;

import database.MySqlConnection;
import model.CustomerBookingModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerBookingDAO {
    
    public List<CustomerBookingModel> getAllCustomerBookings() {
        List<CustomerBookingModel> bookings = new ArrayList<>();
        Connection conn = null;
        
        try {
          
            MySqlConnection db = new MySqlConnection();
            conn = db.openConnection();
            
            String query = "SELECT " +
                          "u.username AS name, " +
                          "b.booking_id, " +
                          "i.invoice_id, " +
                          "b.check_in_date, " +
                          "b.check_out_date, " +
                          "bn.room_type, " +
                          "b.status, " +
                          "i.total_charge " +
                          "FROM bookings b " +
                          "JOIN booknow bn ON b.booking_id = bn.booking_id "+
                          "JOIN users u ON b.user_id = u.user_id " +
                          "JOIN invoice i ON b.booking_id = i.booking_id " +
                          "ORDER BY b.booking_date DESC";
            
            ResultSet rs = db.runQuery(conn, query);
            
            while (rs != null && rs.next()) {
                CustomerBookingModel booking = new CustomerBookingModel();
                booking.setName(rs.getString("name"));
                booking.setBookingId(rs.getString("booking_id"));
                booking.setInvoiceId(rs.getString("invoice_id"));
                booking.setCheckInDate(rs.getDate("check_in_date"));
                booking.setCheckOutDate(rs.getDate("check_out_date"));
                booking.setRoomType(rs.getString("room_type"));
                booking.setStatus(rs.getString("status"));
                booking.setPrice(rs.getDouble("total_charge"));
                
                bookings.add(booking);
            }
            
        } 
        
        catch (SQLException e) {
            System.out.println(e);
        } 
        
        finally {
            if (conn != null) {
                new MySqlConnection().closeConnection(conn);
            }
        }
        
        return bookings;
    }
}