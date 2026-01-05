/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import database.MySqlConnection;
import model.HotelModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    MySqlConnection mysql = new MySqlConnection();
    
    public HotelModel getHotelById(int hotelId) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM hotels WHERE Hotel_id = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, hotelId);
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                HotelModel hotel = new HotelModel();
                hotel.setHotelId(result.getInt("Hotel_id"));
                hotel.setHotelName(result.getString("Hotel_name"));
                hotel.setLocation(result.getString("Location"));
                hotel.setRoomStatus(result.getString("Room_status"));
                hotel.setRating(result.getDouble("Rating"));
                hotel.setImagePath(result.getString("image_path"));
                return hotel;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }
    
    public HotelModel searchHotelByName(String name) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM hotels WHERE Hotel_name LIKE ? LIMIT 1";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, "%" + name + "%");
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                HotelModel hotel = new HotelModel();
                hotel.setHotelId(result.getInt("Hotel_id"));
                hotel.setHotelName(result.getString("Hotel_name"));
                hotel.setLocation(result.getString("Location"));
                hotel.setRoomStatus(result.getString("Room_status"));
                hotel.setRating(result.getDouble("Rating"));
                hotel.setImagePath(result.getString("image_path"));
                return hotel;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }
    
    public List<HotelModel> getAllHotels() {
        List<HotelModel> hotels = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM hotels";
        try (PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet result = pstm.executeQuery()) {
            while (result.next()) {
                HotelModel hotel = new HotelModel();
                hotel.setHotelId(result.getInt("Hotel_id"));
                hotel.setHotelName(result.getString("Hotel_name"));
                hotel.setLocation(result.getString("Location"));
                hotel.setRoomStatus(result.getString("Room_status"));
                hotel.setRating(result.getDouble("Rating"));
                hotel.setImagePath(result.getString("image_path"));
                hotels.add(hotel);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return hotels;
    }
    
    public boolean addHotel(HotelModel hotel) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO hotels (Hotel_name, Location, Room_status, Rating, image_path) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, hotel.getHotelName());
            pstm.setString(2, hotel.getLocation());
            pstm.setString(3, hotel.getRoomStatus());
            pstm.setDouble(4, hotel.getRating());
            pstm.setString(5, hotel.getImagePath());
            pstm.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    
    public List<HotelModel> getAvailableHotels() {
        List<HotelModel> hotels = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM hotels WHERE Room_status = 'Available'";
        try (PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet result = pstm.executeQuery()) {
            while (result.next()) {
                HotelModel hotel = new HotelModel();
                hotel.setHotelId(result.getInt("Hotel_id"));
                hotel.setHotelName(result.getString("Hotel_name"));
                hotel.setLocation(result.getString("Location"));
                hotel.setRoomStatus(result.getString("Room_status"));
                hotel.setRating(result.getDouble("Rating"));
                hotel.setImagePath(result.getString("image_path"));
                hotels.add(hotel);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return hotels;
    }
}
