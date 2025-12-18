/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Hotel;

/**
 *
 * @author sailenawale
 */
public class HotelDAO {
    MySqlConnection mysql = new MySqlConnection();
    
    public void signup(Hotel hotel){
        Connection conn = mysql.openConnection();
        String sql = "insert into hotels (hotelid, name, location, description, rating, image) values (?,?,?,?,?,?)";
        try(PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setInt(1, hotel.getHotelId());
            pstm.setString(2, hotel.getName());
            pstm.setString(3, hotel.getLocation());
            pstm.setString(4, hotel.getDescription());
            pstm.setDouble(5, hotel.getRating());
            pstm.setString(6, hotel.getImage());
            pstm.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            mysql.closeConnection(conn);
        }
    }


public List<Hotel> searchHotels(String keyword) {
    List<Hotel> hotels = new ArrayList<>();
    Connection conn = mysql.openConnection();
    
    String sql = "SELECT * FROM hotels WHERE name LIKE ? OR location LIKE ?";
    
    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
        pstm.setString(1, "%" + keyword + "%");  // Search name
        pstm.setString(2, "%" + keyword + "%");  // Search location
        
        ResultSet rs = pstm.executeQuery();
        
        while (rs.next()) {
            Hotel hotel = new Hotel();
            hotel.setHotelId(rs.getInt("hotel_id"));
            hotel.setName(rs.getString("name"));
            hotel.setLocation(rs.getString("location"));
            hotel.setDescription(rs.getString("description"));
            hotel.setRating(rs.getDouble("rating"));
            hotel.setImage(rs.getString("image"));
            hotels.add(hotel);
        }
        
    } catch (Exception ex) {
        System.out.println("Search error: " + ex.getMessage());
        ex.printStackTrace();
    } finally {
        mysql.closeConnection(conn);
    }
    return hotels;  // Returns list (empty if no hotels found)
}
}


