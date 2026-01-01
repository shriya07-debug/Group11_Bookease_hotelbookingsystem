package dao;

import model.ViewdetailsModel;
import database.MySqlConnection;
import java.sql.*;

public class ViewdetailsDAO {
    
    public ViewdetailsModel getHotelById(int hotelId) {
        ViewdetailsModel hotel = new ViewdetailsModel();
        Connection conn = null;
        
        try {
            MySqlConnection db = new MySqlConnection();
            conn = db.openConnection();
            
            // FIXED: Using viewdetails table (not hotels)
            String query = "SELECT * FROM viewdetails WHERE hotel_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, hotelId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                hotel.setHotelId(rs.getInt("hotel_id"));
                hotel.setHotelName(rs.getString("hotel_name"));
                hotel.setLocation(rs.getString("location"));
                hotel.setGoogleMapsUrl(rs.getString("google_maps_url"));
                hotel.setContact(rs.getString("contact"));
                hotel.setPrice(rs.getDouble("price"));
                hotel.setRatings(rs.getDouble("ratings"));
                hotel.setImage(rs.getString("image"));
            }
            
            db.closeConnection(conn);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return hotel;
    }
}