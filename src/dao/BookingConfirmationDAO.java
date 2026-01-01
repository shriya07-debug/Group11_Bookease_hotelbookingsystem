package dao;

import model.BookingConfirmationModel;
import database.MySqlConnection;
import java.sql.ResultSet;

public class BookingConfirmationDAO {
    private final MySqlConnection db;
    
    public BookingConfirmationDAO() {
        this.db = new MySqlConnection();
    }
    
    // Get confirmation by booking ID
    public BookingConfirmationModel getConfirmationByBookingId(int bookingId) {
        String sql = "SELECT * FROM booking_confirmation WHERE booking_id = " + bookingId;
        
        var conn = db.openConnection();
        try {
            ResultSet rs = db.runQuery(conn, sql);
            
            if (rs.next()) {
                BookingConfirmationModel confirmation = new BookingConfirmationModel();
                confirmation.setConfirmationId(rs.getInt("confirmation_id"));
                confirmation.setBookingId(rs.getInt("booking_id"));
                
                // Date
                var timestamp = rs.getTimestamp("date");
                if (timestamp != null) {
                    confirmation.setDate(new java.util.Date(timestamp.getTime()));
                }
                
                confirmation.setStatus(rs.getString("status"));
                
                db.closeConnection(conn);
                return confirmation;
            }
            
            db.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
            db.closeConnection(conn);
        }
        
        return null;
    }
    
    // Create confirmation
    public boolean createConfirmation(int bookingId) {
        String sql = "INSERT INTO booking_confirmation (booking_id, status) VALUES (" + bookingId + ", 'confirmed')";
        
        var conn = db.openConnection();
        try {
            int result = db.executeUpdate(conn, sql);
            db.closeConnection(conn);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            db.closeConnection(conn);
            return false;
        }
    }
}