package dao;

import database.mysqlconnection;
import model.Invoice;

import java.sql.*;

public class InvoiceDAO {

    public Invoice getInvoiceByBookingId(String bookingId) {

        Invoice invoice = null;

        try {
            Connection conn = mysqlconnection.getConnection();
            String sql = "SELECT * FROM invoice WHERE booking_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bookingId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                invoice = new Invoice();
                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setBookingId(rs.getString("booking_id"));
                invoice.setFullName(rs.getString("full_name"));
                invoice.setCheckIn(rs.getDate("check_in"));
                invoice.setCheckOut(rs.getDate("check_out"));
                invoice.setPaymentMethod(rs.getString("payment_method"));
                invoice.setPaymentStatus(rs.getString("payment_status"));
                invoice.setRoomCharge(rs.getInt("room_charge"));
                invoice.setExtraCharge(rs.getInt("extra_charge"));
                invoice.setTotalCharge(rs.getInt("total_charge"));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return invoice;
    }
}
