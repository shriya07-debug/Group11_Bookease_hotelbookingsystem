package dao;

import model.BooknowModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooknowDAO {
    private final Connection connection;

    
    public BooknowDAO(Connection connection) {
        this.connection = connection;
    }

    
public boolean saveBooking(BooknowModel booking) {
    String sql = "INSERT INTO booknow " +
                 "(booking_id, room_type, number_of_people, check_in_date, check_out_date) " +
                 "VALUES (?,?, ?, ?, ?)";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, 1);
        ps.setString(2, booking.getRoomType());
        ps.setInt(3, booking.getNumberOfPeople());
        ps.setDate(4, booking.getCheckInDate());
        ps.setDate(5, booking.getCheckOutDate());

        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;

    } 
    
    catch (SQLException e) {
        System.out.println(e);
    }
    
    return false;
    
}

    
    public List<BooknowModel> getAllBookings() {
        List<BooknowModel> bookings = new ArrayList<>();
        String sql = "SELECT * FROM booknow ORDER BY booking_date DESC";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                BooknowModel booking = new BooknowModel();
                booking.setId(rs.getInt("id"));
                booking.setRoomType(rs.getString("room_type"));
                booking.setNumberOfPeople(rs.getInt("no_of_people"));
                booking.setCheckInDate(rs.getDate("check_in_date"));
                booking.setCheckOutDate(rs.getDate("check_out_date"));
               
                bookings.add(booking);
            }
        } 
        
        catch (SQLException e) {
            System.out.println(e);
        }
        return bookings;
    }

    
    public boolean isRoomAvailable(String roomType, Date checkIn, Date checkOut) {
        String sql = "SELECT COUNT(*) FROM booknow " +
                     "WHERE room_type = ? " +
                     "AND ((check_in_date <= ? AND check_out_date >= ?) " +
                     "OR (check_in_date <= ? AND check_out_date >= ?))";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, roomType);
            ps.setDate(2, checkOut);
            ps.setDate(3, checkIn);
            ps.setDate(4, checkOut);
            ps.setDate(5, checkIn);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0; 
            }
        } 
        
        catch (SQLException e) {
            System.out.println(e);
        }
        return false; 
    }
}
