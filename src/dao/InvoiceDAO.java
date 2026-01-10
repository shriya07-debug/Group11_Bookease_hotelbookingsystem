package dao;

import model.InvoiceModel;
import database.MySqlConnection;
import java.sql.*;

public class InvoiceDAO {
    private final MySqlConnection db = new MySqlConnection();
    
    public InvoiceModel getInvoiceById(int invoiceId) {
        Connection con = db.openConnection();
        String sql = "SELECT * FROM invoice WHERE invoice_id = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, invoiceId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                
                InvoiceModel invoice = new InvoiceModel();
                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setBookingId(rs.getInt("booking_id"));
                invoice.setUserId(rs.getInt("user_id"));
                invoice.setHotelId(rs.getInt("hotel_id"));
                invoice.setFullName(rs.getString("full_name"));
                invoice.setCheckInDate(rs.getDate("check_in_date"));
                invoice.setCheckOutDate(rs.getDate("check_out_date"));
                invoice.setRoomCharge(rs.getDouble("room_charge"));
                invoice.setExtraCharge(rs.getDouble("extra_charge"));
                invoice.setPaymentMethod(rs.getString("payment_method"));
                invoice.setPaymentStatus(rs.getString("payment_status"));
                return invoice;
            }
        } 
        
        catch (SQLException e) {
            System.out.println(e);
        } 
        
        finally {
            db.closeConnection(con);
        }
        return null;
    }
    public int getInvoiceIdByBookingId(int bookingId) {
    Connection con = db.openConnection();
    String sql = "SELECT invoice_id FROM invoice WHERE booking_id = ?";
    
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, bookingId);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            return rs.getInt("invoice_id");
        }
    } 
    catch (SQLException e) {
        System.out.println(e);
    } 
    finally {
        db.closeConnection(con);
    }
    return -1; // Return -1 if no invoice found
  }
}